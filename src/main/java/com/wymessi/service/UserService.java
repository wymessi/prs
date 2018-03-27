package com.wymessi.service;

import org.springframework.stereotype.Service;

import com.wymessi.po.user.Applicant;
/**
 * 系统角色业务层接口
 * @author 王冶
 *
 */
@Service
public interface UserService {

	/**
	 * 项目申请者注册
	 * @param applicant
	 */
	void registerApplicant(Applicant applicant);
}
