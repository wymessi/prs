package com.wymessi.dao;

import java.util.List;

import com.wymessi.po.Allocate;

public interface AllocateDao {

	int insert(Allocate allocate);

	List<Allocate> listByExpertId(Long id);
}
