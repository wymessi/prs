package com.wymessi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.wymessi.exception.CustomException;
import com.wymessi.param.FieldsListParam;
import com.wymessi.po.Field;
import com.wymessi.service.FieldService;

@Controller
@RequestMapping("/field")
public class FieldController {

	@Autowired
	private FieldService fieldService;

	/**
	 * 领域标签管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fieldPage")
	public String generate(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		
		return "system/fieldManage";
	}
	
	/**
	 * 查询领域标签记录接口，以json的形式返回
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/fields.json")
	public Map<String, Object> getFieldsJson(HttpSession session, HttpServletRequest request) {
		String fieldName = request.getParameter("fieldName");
		int limit = Integer.valueOf(request.getParameter("limit"));
		int offset = (Integer.valueOf(request.getParameter("page")) - 1) * limit;
		// 生成查询参数
		FieldsListParam param = new FieldsListParam();
		if (!StringUtils.isEmpty(fieldName))
			param.setFieldName(fieldName.trim());
		// 设置正确的日期格式
		param.setOffset(offset);
		param.setLimit(limit);

		int totalCount = fieldService.getTotalCount(param);
		List<Field> fields = fieldService.listField(param);
		// 因为layui数据表格的限制，故将数据转成表格需要的格式
		formatFiled(fields);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("count", totalCount);
		map.put("msg", "");
		map.put("data", fields);
		return map;
	}

	/**
	 * 因为layui数据表格的限制，故将数据转成表格需要的格式
	 * @param fields
	 */
	private void formatFiled(List<Field> fields) {
		for (Field field : fields) {
			field.setCreateUserName(field.getUser().getUsername());
		}
	}

	
}
