package com.wymessi.dao;

import com.wymessi.po.EntityField;

public interface EntityFieldMappingDao {
	/**
	 * 插入关联记录
	 * @param entityField
	 */
	public void insert(EntityField entityField);
}
