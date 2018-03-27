package com.wymessi.po.user;
/**
 * 系统工作人员 持久对象
 * @author 王冶
 * 
 */
public class SysUser {

	private int id;
	private String sysUserName;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
