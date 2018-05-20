package com.wymessi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wymessi.exception.CustomException;
import com.wymessi.po.Project;
import com.wymessi.service.ProjectService;
import com.wymessi.utils.FileUtils;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ProjectService projectService;

	/**
	 * 项目评审页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reviewPage")
	public String reviewPage(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		
		return "expert/review";
	}
	
	/**
	 * 我（专家）的评审页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reviewInfoPage")
	public String reviewInfoPage(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		
		return "expert/reviewinfo";
	}
	
	/**
	 * 
	 * 下载项目文件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/download")
	public String download(Model model, HttpSession session, HttpServletResponse res, HttpServletRequest req, Long id) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		Project p = projectService.getProjectById(id);
		if (p == null) {
			model.addAttribute("message", "该项目申请已不存在，请刷新后重试");
		}
		String messgage = FileUtils.download(p.getFileSavePath(), res, req);
		model.addAttribute("message", messgage);
		return "expert/review";
	}
}
