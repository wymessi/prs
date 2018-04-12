package com.wymessi.param;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.wymessi.po.Project;
import com.wymessi.po.SysUser;
/**
 * 项目申请人创建申请时所需参数，为了能够实现事务，即文件的上传、记录的生成、领域的标签保持在同一个事务里
 * @author 王冶
 *
 */
public class GenerateApplyParam {

	private Model model; 
	private MultipartFile file;
	private Project project;
	private String tagsinput;
	private SysUser sysUser;

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getTagsinput() {
		return tagsinput;
	}

	public void setTagsinput(String tagsinput) {
		this.tagsinput = tagsinput;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
}
