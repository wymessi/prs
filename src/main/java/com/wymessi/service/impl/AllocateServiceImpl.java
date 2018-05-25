package com.wymessi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wymessi.dao.AllocateDao;
import com.wymessi.po.Allocate;
import com.wymessi.service.AllocateService;

@Service
public class AllocateServiceImpl implements AllocateService {

	@Autowired
	private AllocateDao allocateDao;
	
	@Override
	public int insert(Allocate allocate) {
		if(allocate == null){
			return 0;
		}
		return allocateDao.insert(allocate);
	}

	@Override
	public List<Allocate> listByExpertId(Long id) {
		if (id != null) {
			List<Allocate> allocates = allocateDao.listByExpertId(id);
			return allocates;
		}
		return null;
	}

	@Override
	public List<Allocate> listByGroupId(Long groupId) {
		if (groupId != null) {
			List<Allocate> allocates = allocateDao.listByGroupId(groupId);
			return allocates;
		}
		return null;
	}

}
