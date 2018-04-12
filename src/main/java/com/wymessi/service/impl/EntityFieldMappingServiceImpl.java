package com.wymessi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wymessi.dao.EntityFieldMappingDao;
import com.wymessi.po.EntityField;
import com.wymessi.service.EntityFieldMappingService;
/**
 * 用于项目申请、专家与领域相关联的业务层接口
 * @author 王冶
 *
 */

@Service
public class EntityFieldMappingServiceImpl implements EntityFieldMappingService {

	@Autowired
	private EntityFieldMappingDao entityFieldMappingDao;
	
	@Override
	public void insert(EntityField entityField) {
		if (entityField != null) {
			entityFieldMappingDao.insert(entityField);
		}
	}

}
