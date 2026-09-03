package ai.blogapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import ai.blogapp.model.TagModel;
import ai.blogapp.service.TagService;

@Controller
public class TagController {
	private final TagService tagService;
	
	public TagController(TagService tagService) {
		this.tagService=tagService;
	}
	
	@GetMapping("/tags")
	public String tagList(Model model) {
		model.addAttribute("tags", tagService.findAll());
		return "tags/tag-list";
	}
	
	@GetMapping("/tags/add")
	public String addTag(Model model) {
		model.addAttribute("tag", new TagModel());
		return "tags/tag-add";
	}
	
	@PostMapping("/tags/add")
	public String addTag(@ModelAttribute TagModel tag, Model model) {
		tagService.add(tag);
		return "redirect:/tags";
	}
	
	@GetMapping("/tags/edit/{id}")
	public String editTag(@PathVariable int id, Model model) {
		TagModel ogTag = tagService.findById(id);
		if (ogTag != null) {
			model.addAttribute("tag", ogTag);
			return "tags/tag-edit";
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag");
		}
	}
	
	@PostMapping("/tags/edit")
	public String editTag(@ModelAttribute TagModel tag, Model model) {
		tagService.edit(tag.getId(), tag);
		return "redirect:/tags";
	}
	
	@GetMapping("/tags/delete/{id}")
	public String deleteTag(@PathVariable int id, Model model) {
		TagModel ogTag = tagService.findById(id);
		if (ogTag != null) {
			model.addAttribute("tag", ogTag);
			return "tags/tag-delete";
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag");
		}
	}
	
	@PostMapping("/tags/delete")
	public String deleteTag(@ModelAttribute TagModel tag, Model model) {
		tagService.delete(tag.getId());
		return "redirect:/tags";
	}
}
