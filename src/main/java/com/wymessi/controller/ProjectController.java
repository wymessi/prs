package com.wymessi.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.wymessi.exception.CustomException;
import com.wymessi.po.Project;
import com.wymessi.po.SysUser;
import com.wymessi.service.ProjectService;
import com.wymessi.utils.FileUtils;

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
	public String uploadPage(HttpServletRequest request, HttpSession session, String role) throws Exception {
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
		//检查文件类型是否合法
		boolean isSuffixValid = projectService.checkFileSuffix(file.getOriginalFilename());
		if (!isSuffixValid) {
			model.addAttribute("message", "不支持的文件类型");
		}
		//将文件保存
		String filePath = null;
		try {
			filePath = FileUtils.upload(file);
			model.addAttribute("message", "申请成功");
		} catch (IOException e) {
			model.addAttribute("message", "上传失败，请重新上传！");
			e.printStackTrace();
		}
		//生成项目申请记录
		project.setStatus(Project.PROJECT_REVIEW_STATUS_WAIT_ALLOCATE);
		project.setFileSavePath(filePath);
		project.setCreateUserId(user.getId());
		project.setCreateTime(new Date());
		project.setLastUpdateTime(new Date());
		projectService.insert(project);
		return "applicant/upload";
	}

}
