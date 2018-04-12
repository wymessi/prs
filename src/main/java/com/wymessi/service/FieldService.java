package com.wymessi.service;

import java.util.List;

import com.wymessi.po.Field;
/**
 * 领域标签业务层接口
 * @author 王冶
 *
 */
public interface FieldService {

	/**
	 * 添加领域
	 * @param field
	 */
	void insert(Field field);

	/**
	 * 批量添加领域
	 * @param field
	 */
	void insertBatch(List<Field> fields);

	/**
	 * 生成有效领域标签、插入记录同时关联申请记录
	 * 
	 */
	void getValidFieldAndInsertBatch(List<String> inputFields, long createUserId);
	
}
