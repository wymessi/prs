package com.wymessi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wymessi.param.FieldsListParam;
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

	/**
	 * 得到总记录数
	 * @param param
	 * @return
	 */
	int getTotalCount(FieldsListParam param);
	
	/**
	 * 查询标签
	 * @param param
	 * @return
	 */
	List<Field> listField(FieldsListParam param);
	
}
