package com.wymessi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wymessi.dao.FieldDao;
import com.wymessi.exception.CustomException;
import com.wymessi.param.FieldsListParam;
import com.wymessi.po.Field;
import com.wymessi.service.FieldService;
import com.wymessi.utils.ListUtils;

/**
 * 领域标签业务层接口实现
 * 
 * @author 王冶
 *
 */
@Service
public class FieldServiceImpl implements FieldService {

	@Autowired
	private FieldDao fieldDao;

	/**
	 * 添加领域
	 * 
	 * @param field
	 */
	@Override
	public void insert(Field field) {
		if (field != null) {
			fieldDao.insert(field);
		}
	}

	/**
	 * 批量添加领域
	 * 
	 * @param fields
	 */
	@Override
	public void insertBatch(List<Field> fields) {
		if (!CollectionUtils.isEmpty(fields)) {
			fieldDao.insertBatch(fields);
		}
	}

	/**
	 * 生成有效领域标签、插入记录同时关联申请记录
	 * @param tagsinput
	 * @param createUserId
	 */
	@Override
	public void getValidFieldAndInsertBatch(List<String> inputFields, long createUserId) {
		if (CollectionUtils.isEmpty(inputFields)) {
			throw new CustomException("领域标签不能为空", "/prs/project/uploadPage");
		}
		List<Field> validFields = new ArrayList<Field>(); // 最后真正进行插入的有效领域
		for (String string : inputFields) {
			Field field = new Field();
			field.setFieldName(string);
			field.setCreateTime(new Date());
			field.setCreateUserId(createUserId);
			validFields.add(field);
		}
		List<Field> existFields = fieldDao.listFieldsByNames(inputFields);
		// 如果数据库中已存在相同标签
		if (!CollectionUtils.isEmpty(existFields)) 
			validFields = ListUtils.removeAll(validFields, existFields);
		if (!CollectionUtils.isEmpty(validFields))
			insertBatch(validFields);
	}

	/**
	 * 得到总记录数
	 * @param param
	 * @return
	 */
	@Override
	public int getTotalCount(FieldsListParam param) {
		if (param != null){
			return fieldDao.getTotalCount(param);
		}
		return 0;
	}

	/**
	 * 查询标签
	 * @param param
	 * @return
	 */
	@Override
	public List<Field> listField(FieldsListParam param) {
		if (param != null){
			return fieldDao.listField(param);
		}
		return null;
	}

}
