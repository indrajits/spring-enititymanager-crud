package com.springmvcenititymanagercrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
	
	@GetMapping("/")
	public String serviceStart() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}	
	
	@GetMapping("/angular_client")
	public String angularClient() {
		return "view/angular_client";
		
	}
}
