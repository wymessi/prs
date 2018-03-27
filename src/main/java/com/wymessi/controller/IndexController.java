package com.wymessi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wymessi.vo.user.LoginVo;

/**
 * 首页访问 控制器
 * 
 * @author 王冶
 *
 */
@Controller
public class IndexController {

	/**
	 * 跳转首页
	 * 
	 * @return
	 */
	@RequestMapping({ "/", "/index" })
	public String index() {
		return "login";
	}

	/**
	 * 登录过滤，判断角色转发对应请求
	 * 
	 * @return
	 */
	@RequestMapping({ "/", "/index" })
	public String login(LoginVo vo) {
		String url = null;
		switch (vo.getRole()) {
		case 0:
			url = "forward:/sysUser/login";
			break;
		case 1:
			url = "forward:/expert/login";
			break;
		case 2:
			url = "forward:/applicant/login";
			break;
		default:
			break;
		}
		
		return url;
	}
}
