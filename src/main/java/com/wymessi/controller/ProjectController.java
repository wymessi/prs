package com.wymessi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.wymessi.exception.CustomException;
import com.wymessi.param.GenerateApplyParam;
import com.wymessi.po.Project;
import com.wymessi.po.SysUser;
import com.wymessi.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	/**
	 * 项目申请页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploadPage")
	public String uploadPage(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		
		return "applicant/upload";
	}
	
	/**
	 * 处理上传请求 
	 * @param model
	 * @param file
	 * @param session
	 * @param project 
	 * @param tagsinput 领域标签用逗号隔开
	 * @return
	 */
	@RequestMapping("/upload")
	public String upload(Model model, MultipartFile file, HttpSession session, Project project, String tagsinput){
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		//生成申请记录
		GenerateApplyParam param = new GenerateApplyParam();
		param.setModel(model);
		param.setFile(file);
		param.setProject(project);
		param.setSysUser(user);
		param.setTagsinput(tagsinput);
		projectService.generateApply(param);
		
		return "applicant/upload";
	}

}
