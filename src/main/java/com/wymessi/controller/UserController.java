package com.wymessi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wymessi.exception.CustomException;
import com.wymessi.param.UserListParam;
import com.wymessi.po.SysUser;
import com.wymessi.service.UserService;
import com.wymessi.utils.Md5Utils;
import com.wymessi.utils.Result;

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
	public String register(HttpServletRequest request, Model model, SysUser user) throws Exception {
		String md5Password = Md5Utils.md5(user.getPassword()); // 密码采用MD5加密
		user.setPassword(md5Password);
		userService.registerApplicant(user);
		model.addAttribute("message", "注册成功");
		model.addAttribute("href", "/prs/");
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
		return "forward:/user/" + user.getRoleId() + "/afterLoginPage";

	}

	/**
	 * 用户退出登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();;
		return "login";

	}
	
	/**
	 * 登陆后跳转的页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{role}/afterLoginPage")
	public String afterLoginPage(HttpServletRequest request, HttpSession session, @PathVariable String role) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		String path = null;
		switch (role) {
		case "1":
			path = "applicant/upload";
			break;
		case "2":
			path = "expert/baseinfo";
			break;
		case "3":
			path = "system/allocate";
			break;
		default:
			break;
		}

		return path;
	}

	/**
	 * 基本信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/baseinfoPage")
	public String baseInfoPage(HttpServletRequest request, HttpSession session, String role) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		String path = null;
		switch (role) {
		case "1":
			path = "applicant/baseinfo";
			break;
		case "2":
			path = "expert/baseinfo";
			break;
		default:
			break;
		}
		return path;
	}
	
	/**
	 * 项目申请信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/applyInfoPage")
	public String applyInfoPage(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		
		return "applicant/applyinfo";
	}
	
	/**
	 * 修改个人信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Result<String> update(HttpSession session, SysUser user) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		Result<String> result = new Result<String>();
		int rows = userService.update(user);
		if (rows > 0) {
			result.setData("修改成功");
		} else {
			result.setData("修改失败");
		}
		
		//由于个人信息都是从session当中取，故更改信息后需重新设置session,否则页面更改完刷新页面后，信息又恢复原样，只有重新登陆才能正确显示
		SysUser newUser = userService.getUserById(user.getId());
		session.setAttribute("user", newUser);
		return result;
	}
	
	/**
	 * 将用户基本信息以json的形式返回
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/userBaseInfo.json")
	public Map<String,Object> getBaseinfoJson(HttpSession session, HttpServletRequest request) throws Exception {
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		String username = request.getParameter("username");
		String roleStr = request.getParameter("role");
		Map<String,Object> map = new HashMap<String, Object>();
		int limit = Integer.valueOf(request.getParameter("limit"));
		int offset = (Integer.valueOf(request.getParameter("page")) - 1) * limit;
		
		List<SysUser> users = null;
		int totalCount = 0;
		if (user.getRoleId() != 3){
			users =  new ArrayList<SysUser>();
			users.add(user);
		} else {
			UserListParam param = new UserListParam();
			param.setUsername(username);
			Long roleId = Long.valueOf(roleStr);
			param.setRoleId(roleId);
			param.setLimit(limit);
			param.setOffset(offset);
			users = userService.listUsersByNameAndRole(param);
			totalCount = userService.getTotalCount(param);
		}
	
		map.put("code", 0);
		map.put("count", totalCount);

		map.put("msg", "");
		map.put("data", users);
		
		return map;
	}
}
