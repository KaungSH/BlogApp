package ai.blogapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import ai.blogapp.model.CategoryModel;
import ai.blogapp.service.CategoryService;

@Controller
public class CategoryController {
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("/categories")
	public String categoryList(Model model) {
		model.addAttribute("categories",categoryService.findAll());
		return "categories/category-list";
	}
	
	@GetMapping("/categories/add")
	public String addCategory(Model model) {
		model.addAttribute("category", new CategoryModel());
		return "categories/category-add";
	}
	
	@PostMapping("/categories/add")
	public String addCategory(@ModelAttribute CategoryModel category, Model model) {
		categoryService.add(category);
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/edit/{id}")
	public String editCategory(@PathVariable int id, Model model) {
			CategoryModel ogCategory = categoryService.findById(id);
			if (ogCategory != null) {
				model.addAttribute("category", ogCategory);
				return "categories/category-edit";
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category");
			}
	}
	
	@PostMapping("/categories/edit")
	public String editCategory(@ModelAttribute CategoryModel category, Model model) {
		categoryService.edit(category.getId(), category);
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id, Model model) {
		CategoryModel ogCategory = categoryService.findById(id);
		if (ogCategory != null) {
			model.addAttribute("category", ogCategory);
			return "categories/category-delete";
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category");
		}
	}
	
	@PostMapping("/categories/delete")
	public String deleteCategory(@ModelAttribute CategoryModel category, Model model) {
		categoryService.delete(category.getId());
		return "redirect:/categories";
	}
}
