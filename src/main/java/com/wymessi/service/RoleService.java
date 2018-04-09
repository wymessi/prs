package com.wymessi.service;

import org.springframework.stereotype.Service;

import com.wymessi.po.SysRole;
/**
 * 系统角色业务层接口
 * @author 王冶
 *
 */
@Service
public interface RoleService {

	/**
	 * 添加角色
	 * @param sysRole
	 */
	void insert(SysRole sysRole);

	
}
