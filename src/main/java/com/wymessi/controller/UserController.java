package com.wymessi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@RequestMapping({ "/", "/index" })
	public String login() {
		return "login";
	}

	@RequestMapping("/applicant/register")
	public String register() {
		return "applicant/register";
	}

	@RequestMapping("/applicant/upload")
	public String upload() {
		return "applicant/upload";
	}
}
