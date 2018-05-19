package com.wymessi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wymessi.exception.CustomException;
import com.wymessi.po.EntityField;
import com.wymessi.po.Field;
import com.wymessi.po.SysUser;
import com.wymessi.service.EntityFieldMappingService;
import com.wymessi.service.FieldService;
import com.wymessi.utils.Result;

@Controller
@RequestMapping("/entityField")
public class EntityFieldMappingController {

	@Autowired
	private FieldService fieldService;
	
/*	@Autowired
	private ProjectService projectService;*/

	@Autowired
	private EntityFieldMappingService entityFieldMappingService;
	
	/**
	 * 查找相关领域
	 * @param session
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/relateField")
	public Result<String> getRelateFields(HttpSession session,Long id,String entityType){
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		Result<String> result = new Result<String>();
		List<EntityField> entityFields = entityFieldMappingService.listByEntityId(id,entityType);
		List<Long> fieldIds = new ArrayList<Long>();
		if(CollectionUtils.isEmpty(entityFields)){
			result.setData("查询不到相关领域");
			return result;
		}
		for (EntityField entityField : entityFields) {
			fieldIds.add(entityField.getFieldId());
		}
		List<Field> fields = null;
		if(!CollectionUtils.isEmpty(fieldIds)){
			fields = fieldService.listByIds(fieldIds);
		}
		StringBuffer buffer = new StringBuffer();
		for (Field field : fields) {
			buffer.append(field.getFieldName()).append(",");
		}
		String data = buffer.toString().substring(0,buffer.length()-1);
		result.setData(data);
		return result;
	}
}
