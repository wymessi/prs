package com.wymessi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wymessi.po.Review;
import com.wymessi.po.SysUser;

@Service
public interface ReviewService {

	/**
	 * 插入
	 * @param group
	 */
	void insert(Review review);

	/**
	 * 打分
	 * @param review
	 * @param attribute
	 */
	void review(Review review, SysUser user);

	List<Review> listByProjectId(Long id);
}
