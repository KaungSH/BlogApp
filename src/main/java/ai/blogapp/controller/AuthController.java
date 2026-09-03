package ai.blogapp.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ai.blogapp.model.UserModel;
import ai.blogapp.model.UserStatus;
import ai.blogapp.model.usermanagement.LoginModel;
import ai.blogapp.model.usermanagement.SignupModel;
import ai.blogapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	private final UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("user", new LoginModel());
		return "user1/login";
	}
	
	@PostMapping("/login")
	public String loginPage(@ModelAttribute("user") LoginModel loginuser, HttpSession oldsession, HttpServletRequest request, Model model) {
		UserModel user = userService.login(loginuser);
		
		if (user == null) {
			model.addAttribute("error", "Invalid Email or Password.");
			return "user1/login";
		}
		
		if (!user.getStatus().equals(UserStatus.NORMAL)) {
			model.addAttribute("error", "The Account is Banned or Deleted.");
			return "user1/login";
		}
		
		oldsession.invalidate();
		HttpSession session = request.getSession(true);
		session.setAttribute("loggedInUser", user);
		System.out.println(user.getUsername());
		System.out.println("SESSION ID: " + session.getId());
		System.out.println("USER: " + session.getAttribute("loggedInUser"));
		return "redirect:/";
	}
	
	@GetMapping("/signup")
	public String signinPage(Model model, HttpSession session) {
		model.addAttribute("user", new SignupModel());
		System.out.println("SESSION ID: " + session.getId());
		System.out.println("USER: " + session.getAttribute("loggedInUser"));
		return "user1/signup";
	}
	
	@PostMapping("/signup")
	public String signinPage(@Valid @ModelAttribute("user") SignupModel signinuser, BindingResult bindingResult, @RequestParam(value ="coverImgPart", required = false) Part imgPart, Model model) {
		if (bindingResult.hasErrors()) {
			return "user1/signup";
		} if (!signinuser.getPassword().equals(signinuser.getPassword2())) {
			model.addAttribute("error1", "Both Passwords must be the same.");
			return "user1/signup";
		} if (userService.findByEmail(signinuser.getEmail()) != null) {
			model.addAttribute("error2", "User with that Email already exist.");
			return "user1/signup";
		}
		
		try {
			signinuser.setPhoto(imgPart.getInputStream().readAllBytes());
		} catch (IOException e) {
			System.out.println("Photo Error - " + e);
		}
		userService.signup(signinuser);
		model.addAttribute("user", new LoginModel(signinuser.getEmail(), signinuser.getPassword()));
		return "user1/login";
	}
	
	@GetMapping("/logout")
	public String logoutPage(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
