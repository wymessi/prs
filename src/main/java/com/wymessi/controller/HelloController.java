package com.wymessi.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	@Profile("dev")
    public String hello(Model model) throws Exception{
    	model.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }  
}
