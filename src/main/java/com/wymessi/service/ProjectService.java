package com.wymessi.service;

import java.util.List;

import com.wymessi.param.GenerateApplyParam;
import com.wymessi.param.ProjectListParam;
import com.wymessi.po.Project;

/**
 * 项目相关业务层接口
 * @author 王冶
 *
 */
public interface ProjectService {

	/**
	 * 检查文件是否为系统支持的类型
	 * @param fileName 上传文件名
	 * @return
	 */
//	boolean checkFileSuffix(String fileName);

	/**
	 * 插入项目申请记录
	 * @param project
	 */
	void insert(Project project);

	/**
	 * 生成申请记录
	 * @param GenerateApplyParam param 记录生成参数
	 */
	void generateApply(GenerateApplyParam param);

	/**
	 * 查询projects
	 * @param param
	 * @return
	 */
	List<Project> listProject(ProjectListParam param);

	/**
	 * 查询总记录数
	 * @param param
	 * @return
	 */
	int getTotalCount(ProjectListParam param);
	
}
