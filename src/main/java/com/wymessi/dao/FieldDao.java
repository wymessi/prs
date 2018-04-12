package com.wymessi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wymessi.po.Field;

public interface FieldDao {

	/**
	 * 插入领域标签
	 * @param field
	 */
	void insert(Field field);
	
	/**
	 * 批量插入领域标签
	 * @param field
	 */
	void insertBatch(@Param("fields")List<Field> fields);

	/**
	 * 根据领域名查出
	 * @param inputFields
	 */
	List<Field> listFieldsByNames(@Param("list")List<String> inputFields);

}
