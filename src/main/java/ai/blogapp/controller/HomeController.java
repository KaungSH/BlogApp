package ai.blogapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ai.blogapp.model.Role;
import ai.blogapp.model.UserModel;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		UserModel user = (UserModel) session.getAttribute("loggedInUser");
		if(user != null &&  (user.getRole() == Role.SUPERADMIN || user.getRole() == Role.ADMIN)) {
			return ("admin_home");
		}
		
		return "redirect:/posts";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("contentPage", "common/dashboard");
		return "home";
	}
	
	@GetMapping("/notfound")
	public String notfound() {
		return "common/notfound";
	}
}
