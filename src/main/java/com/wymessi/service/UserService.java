package com.wymessi.service;

import org.springframework.stereotype.Service;

import com.wymessi.po.SysUser;
/**
 * 系统用户业务层接口
 * @author 王冶
 *
 */
@Service
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
}
