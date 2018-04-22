package com.wymessi.param;

/**
 * 查询申请项目时参数封装类
 * @author 王冶
 *
 */
public class UserListParam {

	private String username;
	private Long roleId;

	//分页
	private int offset;
	private int limit;


	public String getUsername() {
		return username;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
