package com.wymessi.po;

import java.util.Date;
/***
 * 项目分组持久类
 * @author 王冶
 *
 */
public class Group {
	
	public static final String PROJECT_GROUP_STATUS_WAIT_ALLOCATE = "WAIT_ALLOCATE";
	public static final String PROJECT_GROUP_STATUS_WAIT_ADD_PROJECT = "WAIT_ADD_PROJECT";
	public static final String PROJECT_GROUP_STATUS_ALLOCATE_DONE = "ALLOCATE_DONE";
	
	private long id;
	private String groupName; // 分组名
	private String status; // 分组名
	private long createUserId; // 创建人id
	private String createUserName; //因layui的限制所创建，用于显示layui表格的限制
	private Date createTime; // 创建时间
	private Date lastUpdateTime; // 创建时间
	private SysUser user; // 项目申请者持久类
	
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public long getId() {
		return id;
	}
	public String getGroupName() {
		return groupName;
	}
	public long getCreateUserId() {
		return createUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
