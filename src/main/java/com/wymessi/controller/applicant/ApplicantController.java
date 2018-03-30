package com.wymessi.controller.applicant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wymessi.po.user.Applicant;
import com.wymessi.service.UserService;
import com.wymessi.utils.Md5Utils;
import com.wymessi.vo.user.LoginVo;

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
	public String login(LoginVo vo,HttpSession session, Model model) throws Exception {
		String md5Password = null;
		md5Password = Md5Utils.md5(vo.getPassword());
		vo.setPassword(md5Password);
		Applicant applicant = userService.applicantLogin(vo);
		if (applicant == null) {
			model.addAttribute("message", "请正确填写角色、用户名与密码");
			return "login";
		}
		session.setAttribute("userSession", applicant);
		model.addAttribute("applicant", applicant);
		return "applicant/upload";
	}
	
	/**
	 * 项目申请者基本信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/baseinfoPage")
	public String baseinfoPage(Model model,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		if (session == null){
			model.addAttribute("message", "未登录，请先登录");
			model.addAttribute("href", "/prs/");
			return "message";
		}
		Applicant applicant = (Applicant) session.getAttribute("userSession");
		model.addAttribute("applicant", applicant);
		return "applicant/baseinfo";
	}
	
}
