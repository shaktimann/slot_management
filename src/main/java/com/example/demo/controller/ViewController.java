package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	@RequestMapping("/index")
	public String getPage() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String getlogin() {
		return "login";
	}

}
