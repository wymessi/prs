package com.wymessi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wymessi.dao.UserDao;
import com.wymessi.po.SysUser;
import com.wymessi.service.UserService;

/**
 * 系统用户业务层接口实现
 * 
 * @author 王冶
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 项目申请者注册
	 * 
	 * @param applicant
	 */
	@Override
	public void registerApplicant(SysUser sysUser) {
		if (sysUser != null)
			userDao.registerApplicant(sysUser);
	}

	/**
	 * 项目申请者登陆
	 * 
	 * @param param
	 * @return
	 */
	@Override
	public SysUser login(SysUser sysUser) {
		if (sysUser != null) {
			SysUser user = userDao.login(sysUser);
			return user;
		}
		return null;
	}

	/**
	 * 修改个人信息
	 * 
	 * @param user
	 */
	@Override
	public int update(SysUser user) {
		if (user != null) {
			return userDao.update(user);
		}
		return 0;
	}

	/**
	 * 根据id查找用户
	 * @param user
	 * @return
	 */
	@Override
	public SysUser getUserById(Long id) {
		if (id != null) {
			SysUser user = userDao.getUserById(id);
			if (user != null)
				return user;
		}
		return null;
	}

	/**
	 * 通过name模糊搜索用户
	 * @param createUserName
	 * @return
	 */
	@Override
	public List<Long> getUserByUserName(String createUserName) {
		if (createUserName == null){
			return null;
		}
		List<SysUser> users = userDao.getUserByUserName(createUserName);
		List<Long> userIds = new ArrayList<Long>();
		for (SysUser user : users) {
			userIds.add(user.getId());
		}
		return userIds;
	}
}
