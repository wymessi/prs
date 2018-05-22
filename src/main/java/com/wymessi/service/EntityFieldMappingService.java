package com.wymessi.service;

import java.util.List;

import com.wymessi.po.EntityField;

/**
 * 用于项目申请、专家与领域相关联的业务层接口
 * @author 王冶
 *
 */
public interface EntityFieldMappingService {

	/**
	 * 添加关联
	 * @param entityField
	 */
	int insert(EntityField entityField);

	/**
	 * 根据projectId查询
	 * @param id
	 * @return
	 */
	List<EntityField> listByEntityId(Long id,String entityType);

	
}
