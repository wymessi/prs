package com.wymessi.param;

import java.util.Date;
import java.util.List;
/**
 * 查询申请项目时参数封装类
 * @author 王冶
 *
 */
public class ProjectListParam {

	private String projectName;
	private String status;
	private List<Long> createUserIds;
	private Date startTime;
	private Date endTime;
	
	//分页
	private int offset;
	private int limit;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Long> getCreateUserIds() {
		return createUserIds;
	}

	public void setCreateUserIds(List<Long> createUserIds) {
		this.createUserIds = createUserIds;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
