package com.wymessi.dao;

import java.util.List;

import com.wymessi.param.ProjectListParam;
import com.wymessi.po.Project;

public interface ProjectDao {

	/**
	 * 插入项目申请记录
	 * @param project
	 */
	void insert(Project project);

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
