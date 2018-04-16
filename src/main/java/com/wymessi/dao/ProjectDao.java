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

	/**
	 * 更新申请记录
	 * @param project
	 * @return
	 */
	int update(Project project);

	/**
	 * 通过id查询项目申请记录
	 * @param id
	 * @return
	 */
	Project getProjectById(Long id);

	/**
	 * 通过id删除项目申请记录
	 * @param id
	 */
	void deleteById(Long id);
}
