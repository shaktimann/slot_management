package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	@RequestMapping("/index")
	public String getPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		System.out.println(currentPrincipalName);
		return "index";
	}
	
	@RequestMapping("/login")
	public String getlogin() {
		return "login";
	}

		
	@RequestMapping("/bookSlot")
	public String bookSlot() {
		return "bookSlot";
	}
	
	
	@RequestMapping("/loginStore")
	public String registerStore() {
		return "loginStore";
	}
	
	@RequestMapping("/configureStore")
	public String configureStore() {
		return "configureStore";
	}
	/*
	@RequestMapping("/login")
	public String getlogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String getlogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String getlogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String getlogin() {
		return "login";
	}
	*/

}
