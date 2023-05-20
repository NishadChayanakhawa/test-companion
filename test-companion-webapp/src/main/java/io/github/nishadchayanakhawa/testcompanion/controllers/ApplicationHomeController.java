package io.github.nishadchayanakhawa.testcompanion.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationHomeController {
	@GetMapping("/home")
	public String getHomePage() {
		return "HomePage";
	}
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	
	@GetMapping("/setting/usermanagement")
	public String getUserManagementPage() {
		return "Setting/UserManagement";
	}
}
