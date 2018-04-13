package com.wymessi.service;

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
	void insert(EntityField entityField);

	
}
