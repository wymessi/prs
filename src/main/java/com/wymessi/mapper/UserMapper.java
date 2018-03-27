package com.wymessi.mapper;

import com.wymessi.po.user.Applicant;

public interface UserMapper {

	/**
	 * 项目申请者注册
	 * @param applicant
	 */
	void registerApplicant(Applicant applicant);

}
