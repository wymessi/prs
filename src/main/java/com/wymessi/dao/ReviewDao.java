package com.wymessi.dao;

import java.util.List;

import com.wymessi.po.Review;

public interface ReviewDao {

	void insert(Review review);

	List<Review> listByProjectId(Long id);

}
