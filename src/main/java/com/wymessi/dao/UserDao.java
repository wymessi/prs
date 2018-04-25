package com.wymessi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wymessi.param.UserListParam;
import com.wymessi.po.SysUser;

public interface UserDao {

	/**
	 * 项目申请者注册
	 * 
	 * @param applicant
	 */
	void registerApplicant(SysUser sysUser);

	/**
	 * 用户登陆
	 * 
	 * @param param
	 */
	SysUser login(SysUser sysUser);

	/**
	 * 修改个人信息
	 * @param user
	 */
	int update(SysUser user);

	/**
	 * 根据id查找用户
	 * @param user
	 * @return
	 */
	SysUser getUserById(Long id);
	
	/**
	 * 通过name模糊搜索用户
	 * @param createUserName
	 * @return
	 */
	List<SysUser> getUserByUserName(String username);

	/**
	 * 通过name和角色搜索用户
	 * @param username
	 * @param roleId
	 * @return
	 */
	List<SysUser> listUsersByNameAndRole(UserListParam param);

	/**
	 * 得到总记录数
	 * @param username
	 * @param roleId
	 */
	int getTotalCount(UserListParam param);
	
	/**
	 * 根据id查询专家
	 * @return
	 */
	List<SysUser> listExpertByIds(@Param("list")List<Long> idList);
}
