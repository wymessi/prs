package com.wymessi.service;

import java.util.List;

import com.wymessi.po.Allocate;

/**
 * 分配处理接口
 * @author wangye
 *
 */
public interface AllocateService {

	void insert(Allocate allocate);

	List<Allocate> listByExpertId(Long id);
}
