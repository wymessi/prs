package com.wymessi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
