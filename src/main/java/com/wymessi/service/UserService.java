package com.wymessi.service;

import org.springframework.stereotype.Service;

import com.wymessi.po.user.User;

@Service
public interface UserService {

	public User findById(int id);

	public int add(User user);
}
