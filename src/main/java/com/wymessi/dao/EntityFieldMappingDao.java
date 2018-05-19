package com.wymessi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wymessi.po.EntityField;

public interface EntityFieldMappingDao {
	/**
	 * 插入关联记录
	 * @param entityField
	 */
	public void insert(EntityField entityField);

	/**
	 * 根据projectId查询
	 * @param id
	 * @return
	 */
	public List<EntityField> listByEntityId(@Param("id")Long id, @Param("entityType")String entityType);
}
