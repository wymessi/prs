package com.wymessi.po;

/**
 * 项目评审持久类
 * 
 * @author 王冶
 *
 */
public class Review {

	private long id;
	private long applicantId; //项目申请人
	private long projectId; // 
	private long expertId;
	private String opinion; // 专家意见
	private String grade; // 评审成绩

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getApplicantId() {
		return applicantId;
	}

	public long getProjectId() {
		return projectId;
	}

	public long getExpertId() {
		return expertId;
	}

	public String getOpinion() {
		return opinion;
	}

	public String getGrade() {
		return grade;
	}

	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public void setExpertId(long expertId) {
		this.expertId = expertId;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
