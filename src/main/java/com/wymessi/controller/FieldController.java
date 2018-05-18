package com.wymessi.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.wymessi.exception.CustomException;
import com.wymessi.param.FieldsListParam;
import com.wymessi.po.Field;
import com.wymessi.po.SysUser;
import com.wymessi.service.FieldService;
import com.wymessi.utils.Result;
import com.wymessi.utils.UUIDUtils;

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
	public String fieldPage(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		
		return "system/fieldManage/fieldManage";
	}
	
	/**
	 * 添加领域标签管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addFieldPage")
	public String addFieldPage(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		session.setAttribute("token", UUIDUtils.generateUUIDString());
		return "system/fieldManage/addField";
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

	/**
	 * 修改领域标签信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Result<String> update(HttpSession session, Field f) throws Exception {
		SysUser userSession = (SysUser) session.getAttribute("user");
		if (userSession == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		Result<String> result = new Result<String>();
		Field field = fieldService.getById(f.getId());
		if (field == null) {
			result.setData("该领域标签不存在，请刷新页面重新查看");
			return result;
		}
		field.setFieldName(f.getFieldName());
		field.setLastUpdateTime(new Date());
		int rows = fieldService.update(field);
		if (rows > 0) { 
			result.setData("修改成功");
		} else {
			result.setData("修改失败");
		}
		return result;
	}
	
	/**
	 * 删除领域信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public void delete(HttpSession session, Long id) {
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		// 删除领域
		fieldService.deleteById(id);
		
	}	
	
	/**
	 * 添加领域
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addField")
	public String addField(HttpSession session, Model model, Field field, String token) throws Exception {
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		if (session.getAttribute("token") == null) {
			model.addAttribute("message", "请勿重复提交表单");
			return "system/fieldManage/fieldManage";
		}
		if (session.getAttribute("token").equals(token)) {
			fieldService.insert(field,user);
			model.addAttribute("message", "添加成功");
			session.removeAttribute("token");
		}
		return "system/fieldManage/fieldManage";
	}
	
}
