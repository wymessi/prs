package com.wymessi.dao;

import org.apache.ibatis.annotations.Param;

import com.wymessi.po.user.Applicant;
import com.wymessi.vo.user.LoginVo;

public interface UserDao {

	/**
	 * 项目申请者注册
	 * @param applicant
	 */
	void registerApplicant(Applicant applicant);

	/**
	 * 项目申请者登陆
	 * @param param
	 */
	Applicant applicantLogin(@Param("param")LoginVo param);

}
