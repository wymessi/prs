package com.wymessi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	/**
	 * 查找分组下的所有项目
	 * @param groupId
	 * @return
	 */
	List<Project> listByGroupId(Long groupId);

	/**
	 * 根据列表id更新状态，避免循环单次更新以提高效率
	 * @param projectIds
	 */
	void updateStatusByIds(@Param("list")List<Long> projectIds, @Param("status")String status);

	/**
	 * 某专家已评审项目总记录数
	 * @param param
	 * @return
	 */
	int getReivewdTotalCount(ProjectListParam param);

	/**
	 * 某专家已评审项目
	 * @param param
	 * @return
	 */
	List<Project> listReivewdtProject(ProjectListParam param);

	void updateReviewCountById(Long id);
}
