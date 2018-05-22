package com.wymessi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wymessi.dao.ReviewDao;
import com.wymessi.exception.CustomException;
import com.wymessi.po.Project;
import com.wymessi.po.Review;
import com.wymessi.po.SysUser;
import com.wymessi.service.ProjectService;
import com.wymessi.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private ProjectService projectService;

	@Override
	public void insert(Review review) {
		if (review == null)
			return;
		reviewDao.insert(review);
	}

	@Transactional
	@Override
	public void review(Review review, SysUser user) {
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
		p.setStatus(Project.PROJECT_REVIEW_STATUS_REVIEW_DONE);
		p.setLastUpdateTime(new Date());
		projectService.update(p);
	}

	@Override
	public List<Review> listByProjectId(Long id) {
		if (id ==null)
			return null;
		return reviewDao.listByProjectId(id);
	}
	
}
