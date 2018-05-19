package com.wymessi.service.impl;

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
	public void insert(Allocate allocate) {
		if(allocate == null){
			return;
		}
		allocateDao.insert(allocate);
	}

}
