package com.wymessi.dao;

import org.apache.ibatis.annotations.Param;

import com.wymessi.po.user.SysUser;
import com.wymessi.vo.user.LoginVo;

public interface UserDao {

	/**
	 * 项目申请者注册
	 * @param applicant
	 */
	void registerApplicant(SysUser sysUser);

	/**
	 * 用户登陆
	 * @param param
	 */
	SysUser login(@Param("param")LoginVo param);

}
