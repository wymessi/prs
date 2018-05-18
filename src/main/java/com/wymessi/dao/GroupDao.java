package com.wymessi.dao;

import java.util.List;

import com.wymessi.param.GroupListParam;
import com.wymessi.po.Group;

public interface GroupDao {

	/**
	 * 添加项目分组
	 * @param group
	 */
	void insert(Group group);
	
	/**
	 * 得到总记录数
	 * @param param
	 * @return
	 */
	int getTotalCount(GroupListParam param);
	
	/**
	 * 查询分组
	 * @param param
	 * @return
	 */
	List<Group> listGroup(GroupListParam param);
	
	/**
	 * 修改领域标签信息
	 * 
	 */
	int update(Group group);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	Group getById(Long id);
	
}
