package com.wymessi.param;

public class GroupListParam {

	private String GroupName;
	
	//分页
	private int offset;
	private int limit;
	public String getGroupName() {
		return GroupName;
	}
	public int getOffset() {
		return offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
