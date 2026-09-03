package ai.blogapp.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import ai.blogapp.model.UserStatus;
import ai.blogapp.model.usermanagement.ChangeProfileModel;
import ai.blogapp.model.Role;
import ai.blogapp.model.UserModel;
import ai.blogapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@Controller
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@GetMapping("/users")
	public String userList(Model model) {
		model.addAttribute("users", userService.findAll());
		return "user2/user-list";
	}
	
	@GetMapping("/users/deleted")
	public String userDeletedList(Model model) {
		model.addAttribute("users", userService.findDeletedAll());
		return "user2/user-deleted-list";
	}
	
	@GetMapping("/users/status/{id}")
	public String changeUserStatus(@PathVariable String id, Model model) {
		UserModel ogUser = userService.findById(id);
		if (ogUser != null) {
			model.addAttribute("availableStatus", UserStatus.values());
			model.addAttribute("user", ogUser);
			return "user2/user-status";
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User");
		}
	}
	
	@PostMapping("/users/status")
	public String changeUserStatus(@ModelAttribute UserModel user, Model model) {
		userService.changeUserStatus(user);
		return "redirect:/users";
	}
	
	@GetMapping("/users/role/{id}")
	public String changeUserRole(@PathVariable String id, Model model) {
		UserModel ogUser = userService.findById(id);
		if (ogUser != null) {
			model.addAttribute("availableRoles", Role.values());
			model.addAttribute("user", ogUser);
			return "user2/user-role";
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User");
		}
	}
	
	@PostMapping("/users/role")
	public String changeUserRole(@ModelAttribute UserModel user, Model model) {
		userService.changeUserRole(user);
		return "redirect:/users";
	}
	
	@GetMapping("/pedit")
	public String editProfile(Model model, HttpSession session) {
		String id = ((UserModel) session.getAttribute("loggedInUser")).getId();
		UserModel ogUser = userService.findById(id);
		if (ogUser != null) {
			model.addAttribute("user", new ChangeProfileModel(id, ogUser.getUsername(), ogUser.getPhone(), ogUser.getPhoto()));
			return "user2/user-profile";
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User");
		}
	}
	
	@PostMapping("/pedit")
	public String editProfile(@ModelAttribute("user") ChangeProfileModel pmuser, Model model, HttpSession oldsession, HttpServletRequest request, @RequestParam(value ="coverImgPart", required = false) Part imgPart) {
		
		if(imgPart.getSize() > 0) {
			try {
				pmuser.setPhoto(imgPart.getInputStream().readAllBytes());
			} catch (IOException e) {
				System.out.println("Photo Error - " + e);
			}
		}
		
		
		int user = userService.changeProfile(pmuser);
		
		System.out.println(pmuser.getPassword());
		if (pmuser.getPhoto() == null) {
			System.out.println("this!!!");
		}
		if (user == 0) {
			model.addAttribute("user", pmuser);
			model.addAttribute("error", "Wrong Password.");
			return "user2/user-profile";
		}
		
		oldsession.invalidate();
		HttpSession session = request.getSession(true);
		session.setAttribute("loggedInUser", userService.findById(pmuser.getId()));
		System.out.println("SESSION ID: " + session.getId());
		System.out.println("USER: " + session.getAttribute("loggedInUser"));
		return "redirect:/";
		
	}
}
