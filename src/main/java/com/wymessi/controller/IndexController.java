package com.wymessi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wymessi.po.user.SysUser;
import com.wymessi.utils.UUIDUtils;

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
	public String index(HttpSession session) {
		session.setAttribute("token", UUIDUtils.generateUUIDString());
		return "login";
	}

	/**
	 * 将用户基本信息以json的形式返回
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/userBaseInfo.json")
	public JSONObject baseinfoPage(Model model,HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		if (session == null){
			model.addAttribute("message", "未登录，请先登录");
			model.addAttribute("href", "/prs/");
		}
		SysUser sysUser = (SysUser) session.getAttribute("userSession");
		String json = "{code:0,msg:'',count:1000,data:["+JSON.toJSONString(sysUser)+"]}";
		JSONObject jsonObject = JSONObject.parseObject(json);
		return jsonObject;
	}
}
