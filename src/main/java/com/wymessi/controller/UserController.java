package com.wymessi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
   
	@RequestMapping({"/","/index"})
	public String test(){
		return "login";
	}

	@RequestMapping("/register")
	public String register(){
		return "register";
	}
}
