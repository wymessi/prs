package com.wymessi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wymessi.mapper.UserMapper;
import com.wymessi.po.user.Applicant;
import com.wymessi.service.UserService;
/**
 * 系统角色业务层接口实现
 * @author 王冶
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	/**
	 * 项目申请者注册
	 * @param applicant
	 */
	@Override
	public void registerApplicant(Applicant applicant) {
		if (applicant != null)
			userMapper.registerApplicant(applicant);
	}

}
