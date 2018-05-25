package com.wymessi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wymessi.dao.ReviewDao;
import com.wymessi.exception.CustomException;
import com.wymessi.po.Allocate;
import com.wymessi.po.Project;
import com.wymessi.po.Review;
import com.wymessi.po.SysUser;
import com.wymessi.service.AllocateService;
import com.wymessi.service.ProjectService;
import com.wymessi.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private AllocateService allocateService;

	@Override
	public void insert(Review review) {
		if (review == null)
			return;
		reviewDao.insert(review);
	}

	@Transactional
	@Override
	public void review(Review review, SysUser user, Long groupId) {
		if (review == null){
			throw new CustomException("参数错误，刷新后重试", "/prs/review/reviewInfoPage");
		}
		review.setExpertId(user.getId());
		review.setReviewTime(new Date());
		insert(review);
		Project p = projectService.getProjectById(review.getProjectId());
		if (p == null) {
			throw new CustomException("该申请记录不存在", "/prs/review/reviewInfoPage");
		}
		projectService.updateReviewCountById(p.getId());
		p.setStatus(Project.PROJECT_REVIEW_STATUS_IN_REVIEW);
		p.setLastUpdateTime(new Date());
		projectService.update(p);
		
		
		//查出分组被分配给了哪些专家
		List<Allocate> allocates = allocateService.listByGroupId(groupId);
		Project project = projectService.getProjectById(review.getProjectId());
		
		//如果所有专家都评审完成，则项目才算评审完成
		if (project.getReviewCount() == allocates.size()) {
			project.setStatus(Project.PROJECT_REVIEW_STATUS_REVIEW_DONE);
			project.setLastUpdateTime(new Date());
			projectService.update(project);
		}
	}

	@Override
	public List<Review> listByProjectId(Long id) {
		if (id == null)
			return null;
		return reviewDao.listByProjectId(id);
	}

	@Override
	public Review getByExpertIdAndProjectId(Long expertId, Long projectId) {
		if (projectId == null)
			return null;
		return reviewDao.getByExpertIdAndProjectId(expertId,projectId);
	}
	
}
