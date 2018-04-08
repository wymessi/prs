package com.wymessi.controller.applicant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wymessi.po.user.SysUser;
import com.wymessi.service.UserService;
import com.wymessi.utils.Md5Utils;
import com.wymessi.vo.user.LoginVo;

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
			String md5Password = Md5Utils.md5(sysUser.getPassword());  //密码采用MD5加密
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
	public String login(LoginVo vo,HttpSession session, Model model) throws Exception {
		if (vo.getToken().equals(session.getAttribute("token"))) {
			String md5Password = null;
			md5Password = Md5Utils.md5(vo.getPassword());
			vo.setPassword(md5Password);
			SysUser sysUser = userService.applicantLogin(vo);
			if (sysUser == null) {
				model.addAttribute("message", "请正确填写角色、用户名与密码");
				return "login";
			}
			session.setAttribute("userSession", sysUser);
			model.addAttribute("sysUser", sysUser);
			session.removeAttribute("token"); //
		}else{
			
		}
		return "applicant/upload";
	}
	
	/**
	 * 项目申请者基本信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{role}/baseinfoPage")
	public String baseinfoPage(Model model,HttpServletRequest request,@PathVariable String role) throws Exception {
		HttpSession session = request.getSession();
		if (session == null){
			model.addAttribute("message", "未登录，请先登录");
			model.addAttribute("href", "/prs/");
			return "message";
		}
		SysUser sysUser = (SysUser) session.getAttribute("userSession");
		model.addAttribute("sysUser", sysUser);
		return role+"/baseinfo";
	}
	
}
