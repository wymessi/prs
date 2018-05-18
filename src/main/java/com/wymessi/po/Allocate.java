package com.wymessi.po;

import java.util.Date;

public class Allocate {

	private long id;
	private long groupId;
	private long expertId;
	private long allocateUserId;
	private Date allocateTime;
	public long getId() {
		return id;
	}
	public long getGroupId() {
		return groupId;
	}
	public long getExpertId() {
		return expertId;
	}
	public long getAllocateUserId() {
		return allocateUserId;
	}
	public Date getAllocateTime() {
		return allocateTime;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public void setExpertId(long expertId) {
		this.expertId = expertId;
	}
	public void setAllocateUserId(long allocateUserId) {
		this.allocateUserId = allocateUserId;
	}
	public void setAllocateTime(Date allocateTime) {
		this.allocateTime = allocateTime;
	}
	
	
}
