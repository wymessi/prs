package com.wymessi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wymessi.exception.CustomException;
import com.wymessi.service.FieldService;

@Controller
@RequestMapping("/field")
public class FieldController {

	@Autowired
	private FieldService fieldService;

	/**
	 * 项目申请页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/generate")
	public String generate(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		
		return "applicant/upload";
	}
	
	

}
