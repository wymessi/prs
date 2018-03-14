package com.wymessi.mapper;

import com.wymessi.po.user.User;

public interface UserMapper {

	public User findById(int id);
	
	public int add(User user);
}
