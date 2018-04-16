package com.wymessi.dao;

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

}
