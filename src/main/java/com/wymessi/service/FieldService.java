package com.wymessi.service;

import java.util.List;

import com.wymessi.param.FieldsListParam;
import com.wymessi.po.Field;
import com.wymessi.po.SysUser;
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
	void insert(Field field, SysUser user);

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
	
}
