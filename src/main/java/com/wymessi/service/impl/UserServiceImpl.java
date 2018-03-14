package com.wymessi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wymessi.mapper.UserMapper;
import com.wymessi.po.user.User;
import com.wymessi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findById(int id) {
		
		return userMapper.findById(id);
	}

	@Transactional
	@Override
	public int add(User user) {
		
		return userMapper.add(user);
	}

}
