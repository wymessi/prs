package com.wymessi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wymessi.dao.UserDao;
import com.wymessi.po.user.Applicant;
import com.wymessi.service.UserService;
import com.wymessi.vo.user.LoginVo;
/**
 * 系统角色业务层接口实现
 * @author 王冶
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	/**
	 * 项目申请者注册
	 * @param applicant
	 */
	@Override
	public void registerApplicant(Applicant applicant) {
		if (applicant != null)
			userDao.registerApplicant(applicant);
	}
	
	/**
	 * 项目申请者登陆
	 * @param param
	 * @return
	 */
	@Override
	public Applicant applicantLogin(LoginVo param) {
		if (param != null){
			Applicant applicant = userDao.applicantLogin(param);
			return applicant;
		}
		return null;
	}

}
