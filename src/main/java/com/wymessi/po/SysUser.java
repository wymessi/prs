package com.wymessi.po;

/**
 * 系统用户
 * 
 * @author 王冶
 * 
 */
public class SysUser {

	private long id;
	private String username; // 姓名
	private String password; // 密码
	private String sex; // 男，女
	private String mail; // 邮箱
	private String phone; // 手机号
	private String title; // 若用户为专家，则表示职称，默认为空
	private long roleId; //用户角色id
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	
}
