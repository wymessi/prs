package com.wymessi.controller.applicant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wymessi.po.user.Applicant;
import com.wymessi.service.UserService;
import com.wymessi.utils.Md5Utils;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {

	@Autowired
	private UserService userService;

	/**
	 * 跳转到注册页面
	 * 
	 * @return
	 */
	@RequestMapping("/registerPage")
	public String registerPage() {
		return "applicant/register";
	}

	/**
	 * 项目申请者注册
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/register")
	public String register(HttpServletRequest request, Model model, Applicant applicant) throws Exception {
		String message = null;
		String href = null;
		if (applicant != null) {
			String md5Password = Md5Utils.md5(applicant.getPassword());  //密码采用MD5加密
			applicant.setPassword(md5Password);
			userService.registerApplicant(applicant);
			message = "注册成功";
			href = "/prs/";
			model.addAttribute("message", message);
			model.addAttribute("href", href);
		}
		
		return "applicant/register";
	}

	/**
	 * 项目申请者登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model, Applicant applicant) throws Exception {
		String message = null;
		String href = null;
		if (applicant != null) {
			String md5Password = Md5Utils.md5(applicant.getPassword());  //密码采用MD5加密
			applicant.setPassword(md5Password);
			userService.registerApplicant(applicant);
			message = "注册成功";
			href = "/prs/";
			model.addAttribute("message", message);
			model.addAttribute("href", href);
		}
		
		return "applicant/register";
	}
	
	@RequestMapping("/uploadPage")
	public String upload() {
		return "applicant/upload";
	}
}
