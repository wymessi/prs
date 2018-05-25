package com.wymessi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wymessi.po.Review;

public interface ReviewDao {

	void insert(Review review);

	List<Review> listByProjectId(Long id);

	Review getByExpertIdAndProjectId(@Param("expertId")Long expertId, @Param("projectId")Long projectId);

}
