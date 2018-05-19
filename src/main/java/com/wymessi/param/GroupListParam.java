package com.wymessi.param;

public class GroupListParam extends CustomParam{

	
	private String GroupName;
	private String status;
	
	
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	public String getGroupName() {
		return GroupName;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
