package ai.blogapp.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import ai.blogapp.RandomString;
import ai.blogapp.model.PostEntryModel;
import ai.blogapp.model.PostListModel;
import ai.blogapp.model.Role;
import ai.blogapp.model.Status;
import ai.blogapp.model.UserModel;
import ai.blogapp.service.CategoryService;
import ai.blogapp.service.PostService;
import ai.blogapp.service.TagService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;

@Controller
public class PostController {

private final PostService postService;
private final CategoryService crepo;
private final TagService trepo;
	
	public PostController(PostService postService, CategoryService crepo, TagService trepo) {
		this.postService =postService;
		this.trepo = trepo;
		this.crepo = crepo;
	}
	
	@GetMapping("/posts")
	public String postList(Model model, HttpSession session) {
		UserModel user = (UserModel) session.getAttribute("loggedInUser");
		
		if (user.getRole() == Role.USER) {
			model.addAttribute("posts",postService.findPublishedAll());
			return "posts/post-card-list";
		}
		if(user.getRole() == Role.SUPERADMIN || user.getRole() == Role.ADMIN) {
			model.addAttribute("posts",postService.findAll());
			return ("posts/post-list");
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("/posts/detail/{id}")
	public String detailPost(@PathVariable String id, Model model, HttpSession session) {
		UserModel user = (UserModel) session.getAttribute("loggedInUser");
		
		if (user == null) {
			return "redirect:/login";
		}
		
		if (postService.findById(id) != null) {
			
			model.addAttribute("post", postService.findDetailById(id));
			return "posts/post-detail";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post");
		}
		
		
	}
	
	@GetMapping("/posts/deleted-list")
	public String postDeletedList(Model model, HttpSession session) {
		UserModel user = (UserModel) session.getAttribute("loggedInUser");
		
		if (user.getRole() == Role.USER  || user.getRole() == Role.ADMIN) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "NOT ENOUGH PERMISSION!!!");
		}
		if(user.getRole() == Role.SUPERADMIN) {
			model.addAttribute("posts",postService.findDeletedAll());
			return ("posts/post-deleted-list");
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("/posts/recover/{id}")
	public String editRecoverPost(@PathVariable String id, Model model, HttpSession session) {
		UserModel user = (UserModel) session.getAttribute("loggedInUser");
		
		if (user.getRole() == Role.USER || user.getRole() == Role.ADMIN) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "NOT ENOUGH PERMISSION!!!");
		}
		
		if (postService.findDeletedById(id) != null) {
			
			if(user.getRole() == Role.SUPERADMIN ) {
				postService.recover(id);
			}
			return "redirect:/posts";
		}
		
		if (postService.findDeletedById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post");
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("/posts/add")
	public String addPost(Model model, HttpSession session) {
		UserModel user = (UserModel) session.getAttribute("loggedInUser");
		
		if (user.getRole() == Role.USER) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "NOT ENOUGH PERMISSION!!!");
		}
		if(user.getRole() == Role.SUPERADMIN || user.getRole() == Role.ADMIN) {
			model.addAttribute("post", new PostEntryModel("DRAFT"));
			bindAvialableData(model);
			return "posts/post-add";
		}
		
		return "redirect:/login";
	}
	
	@PostMapping("/posts/add")
	public String addPostEntry(@Valid @ModelAttribute("post") PostEntryModel mentry, HttpSession session, BindingResult bindingResult, @RequestParam(value ="coverImgPart", required = false) Part imgPart, Model model) {
		if (bindingResult.hasErrors()) {
			bindAvialableData(model);
			return "posts/post-add";
		}
		mentry.setCover_img_path(saveImgFile(imgPart));
		
		mentry.setCreateduser_id(((UserModel) session.getAttribute("loggedInUser")).getId());
		postService.add(mentry);
		return "redirect:/posts";
	}
	
	@GetMapping("/posts/edit/{id}")
	public String editPost(@PathVariable String id, Model model, HttpSession session) {
		UserModel user = (UserModel) session.getAttribute("loggedInUser");
		
		if (user.getRole() == Role.USER) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "NOT ENOUGH PERMISSION!!!");
		}
		
		if (postService.findById(id) != null) {
			
			if(user.getRole() == Role.SUPERADMIN || user.getRole() == Role.ADMIN) {
				if(!user.getId().equals(postService.findById(id).getCreateduser_id())) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "NOT YOUR POST!!! YOU HACKER!!!");
				model.addAttribute("post", postService.findById(id));
				bindAvialableData(model);
				return "posts/post-edit";
			}
			return "redirect:/login";
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post");
		}
	}
	
	@PostMapping("/posts/edit")
	public String editPostEntry(@Valid @ModelAttribute("post") PostEntryModel mentry, BindingResult bindingResult, @RequestParam(value ="coverImgPart", required = false) Part imgPart, Model model) {
		if (bindingResult.hasErrors()) {
			bindAvialableData(model);
			return "posts/post-edit";
		}
		String file = saveImgFile(imgPart);
		if (file == null || file.isEmpty()) {
			postService.edit(mentry);
			return "redirect:/posts";
		}
		mentry.setCover_img_path(file);
		postService.edit(mentry);
		return "redirect:/posts";
	}
	
	@GetMapping("/posts/delete/{id}")
	public String deletePostEntry(@PathVariable String id, Model model, HttpSession session) {
		UserModel user = (UserModel) session.getAttribute("loggedInUser");
		
		if (user.getRole() == Role.USER) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "NOT ENOUGH PERMISSION!!!");
		}
		
		if (postService.findById(id) != null) {
			
			if(user.getRole() == Role.ADMIN) {
				if(!user.getId().equals(postService.findById(id).getCreateduser_id())) return "error/nopermission-edit";
				model.addAttribute("post", postService.findDetailById(id));
				return "posts/post-delete";
			}
			if(user.getRole() == Role.SUPERADMIN) {
				model.addAttribute("post", postService.findDetailById(id));
				return "posts/post-delete";
			}
			return "redirect:/login";
		}else {
			model.addAttribute("item", "Post");
			return "/common/notfound";
		}
	}
	
	@PostMapping("/posts/delete")
	public String deletePostEntry(@Valid @ModelAttribute PostListModel post, Model model) {
		postService.delete(post.getId());
		return "redirect:/posts";
	}
	
	private void bindAvialableData(Model model) {
		model.addAttribute("availableStatus", Status.values());
		model.addAttribute("availableCategories", crepo.findAll());
		model.addAttribute("availableTags", trepo.findAll());
	}
	
	private String saveImgFile(Part imgPart) {
		try {
			if (imgPart != null && imgPart.getSize() > 0) {
			                   
				File uploadDir = new File("D:/JWD (Java Web Development)/Dev/sevletprojects/posts_img");
				if(!uploadDir.exists()) {
				   uploadDir.mkdir();
				}
				
				String fileName = RandomString.generate()+ " - " + imgPart.getSubmittedFileName();
				File fileToSave = new File(uploadDir, fileName);
				String actualPath = fileToSave.getAbsolutePath();
				imgPart.write(actualPath);
				return actualPath;
				}
			return null;
			
		} catch (IOException e) {
			System.out.println("Saving Img Failed - " + e);
		}
		return null;
	}
	
	
}
