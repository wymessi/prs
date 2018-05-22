package com.wymessi.service.impl;

import java.util.List;

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
	
	/**
	 * 添加关联
	 * @param entityField
	 */
	@Override
	public int insert(EntityField entityField) {
		if (entityField != null) {
			return entityFieldMappingDao.insert(entityField);
		}
		return 0;
	}

	/**
	 * 根据projectId查询
	 * @param id
	 * @return
	 */
	@Override
	public List<EntityField> listByEntityId(Long id, String entityType) {
		if (id != null) {
			List<EntityField> entityFields = entityFieldMappingDao.listByEntityId(id,entityType);
			return entityFields;
		}
		return null;
	}

}
