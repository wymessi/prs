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
	
	/**
	 * 修改领域标签信息
	 * 
	 */
	int update(Field field);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	Field getById(Long id);
	
	/**
	 * 根据id删除领域
	 * @param id
	 */
	public void deleteById(Long id);

	/**
	 * 根据ID列表查询
	 * @param fieldIds
	 * @return
	 */
	List<Field> listByIds(@Param("list")List<Long> fieldIds);
}
