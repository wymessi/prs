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
	void updateStatusByIds(List<Long> projectIds, String status);

}
