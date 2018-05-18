package com.wymessi.param;

import com.wymessi.po.SysUser;

public class GroupAllocateParam {

	private long groupId;
	private SysUser user;
	private long expertId;
	public long getGroupId() {
		return groupId;
	}
	public SysUser getUser() {
		return user;
	}
	public long getExpertId() {
		return expertId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public void setExpertId(long expertId) {
		this.expertId = expertId;
	}
	
	
}
