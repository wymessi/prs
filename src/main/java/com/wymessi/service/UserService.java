package com.wymessi.service;

import java.util.List;

import com.wymessi.po.SysUser;
/**
 * 系统用户业务层接口
 * @author 王冶
 *
 */
public interface UserService {

	/**
	 * 项目申请者注册
	 * @param applicant
	 */
	void registerApplicant(SysUser sysUser);

	/**
	 * 用户登陆
	 * @param vo
	 * @return
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
	List<Long> getUserByUserName(String createUserName);
}
