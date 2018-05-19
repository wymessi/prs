package com.wymessi.param;

import java.util.Date;

public class CustomParam {

	private Date startTime;
	private Date endTime;
	
	//分页
	private int offset;
	private int limit;
	public Date getStartTime() {
		return startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public int getOffset() {
		return offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
}
