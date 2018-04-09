package com.wymessi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wymessi.exception.UnloginException;
import com.wymessi.po.SysUser;
import com.wymessi.service.UserService;
import com.wymessi.utils.Md5Utils;

@Controller
@RequestMapping("/user")
public class UserController {

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
	public String register(HttpServletRequest request, Model model, SysUser sysUser) throws Exception {
		String message = null;
		String href = null;
		if (sysUser != null) {
			String md5Password = Md5Utils.md5(sysUser.getPassword()); // 密码采用MD5加密
			sysUser.setPassword(md5Password);
			userService.registerApplicant(sysUser);
			message = "注册成功";
			href = "/prs/";
			model.addAttribute("message", message);
			model.addAttribute("href", href);
		}

		return "applicant/register";
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(SysUser sysUser, HttpSession session, Model model) throws Exception {
		sysUser.setPassword(Md5Utils.md5(sysUser.getPassword()));
		SysUser user = userService.login(sysUser);
		if (user == null) {
			model.addAttribute("message", "请正确填写角色、用户名与密码");
			return "login";
		}
		session.setAttribute("user", user);
		return "forward:/user/"+user.getRoleId()+"/baseinfoPage";

	}

	/**
	 * 登陆后跳转到基本信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{role}/baseinfoPage")
	public String baseinfoPage(Model model, HttpServletRequest request, @PathVariable String role) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null){
			throw new UnloginException("未登录，请先登录", "/prs/");
		}
		SysUser sysUser = (SysUser) session.getAttribute("userSession");
		model.addAttribute("sysUser", sysUser);
		return role + "/baseinfo";
	}

}
