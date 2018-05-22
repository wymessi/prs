package com.wymessi.service;

import java.util.List;

import com.wymessi.param.GroupAllocateParam;
import com.wymessi.param.GroupListParam;
import com.wymessi.po.Group;
import com.wymessi.po.SysUser;
/**
 * 项目分组业务层接口
 * @author 王冶
 *
 */
public interface GroupService {

	/**
	 * 添加分组
	 * @param group
	 */
	void insert(Group group, SysUser user);

	
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
	 * 修改项目分组信息
	 * 
	 */
	int update(Group group);

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	Group getById(Long id);


	/**
	 * 分配专家
	 * @param param
	 */
	int allocate(GroupAllocateParam param);
	
	/**
	 * 根据id删除
	 * @param id
	 */
//	public void deleteById(Long id);
	
}
