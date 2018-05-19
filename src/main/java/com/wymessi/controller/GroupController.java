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
import com.wymessi.param.GroupAllocateParam;
import com.wymessi.param.GroupListParam;
import com.wymessi.po.Group;
import com.wymessi.po.Project;
import com.wymessi.po.SysUser;
import com.wymessi.service.GroupService;
import com.wymessi.service.ProjectService;
import com.wymessi.utils.CustomDateUtils;
import com.wymessi.utils.Result;
import com.wymessi.utils.UUIDUtils;

@Controller
@RequestMapping("/group")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private ProjectService projectService;

	/**
	 * 分组管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/groupManagePage")
	public String fieldPage(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		
		return "system/groupManage/groupManage";
	}
	
	/**
	 * 创建项目分组页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addGroupPage")
	public String addGroupPage(HttpSession session) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		session.setAttribute("token", UUIDUtils.generateUUIDString());
		return "system/groupManage/addGroup";
	}
	
	/**
	 * 为分组添加项目页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addProjectPage")
	public String addProjectPage(HttpSession session,String groupId,Model model) throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
//		session.setAttribute("token", UUIDUtils.generateUUIDString());
		if(StringUtils.isEmpty(groupId)){
			throw new CustomException("分组信息异常，请刷新后重试", "/prs/group/addProjectPage");
		}
		model.addAttribute("groupId", groupId);
		return "system/groupManage/addProject";
	}
	
	/**
	 * 为分组添加项目
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addProject")
	public String addProject(HttpSession session,Long groupId,Model model, Long projectId)  throws Exception {
		if (session.getAttribute("user") == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		Project project = projectService.getProjectById(projectId);
		if (groupId == null || projectId == null) {
			throw new CustomException("分组信息异常，请刷新后重试", "/prs/group/addProjectPage");
		}
		project.setGroupId(groupId);
		project.setStatus(Project.PROJECT_REVIEW_STATUS_WAIT_ALLOCATE);
		project.setLastUpdateTime(new Date());
		projectService.update(project);
		
		Group group = groupService.getById(groupId);
		group.setStatus(Group.PROJECT_GROUP_STATUS_WAIT_ALLOCATE);
		groupService.update(group);
		
		model.addAttribute("message", "添加至分组成功");
		model.addAttribute("groupId", groupId);
		return "system/groupManage/addProject";
	}
	
	/**
	 * 查询分组接口，以json的形式返回
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/groups.json")
	public Map<String, Object> getGroupsJson(HttpSession session, HttpServletRequest request) {
		String groupName = request.getParameter("groupName");
		String status = request.getParameter("status");
		String createTime = request.getParameter("createTime");
		int limit = Integer.valueOf(request.getParameter("limit"));
		int offset = (Integer.valueOf(request.getParameter("page")) - 1) * limit;
		// 生成查询参数
		GroupListParam param = new GroupListParam();
		if (!StringUtils.isEmpty(groupName))
			param.setGroupName(groupName.trim());
		if (!StringUtils.isEmpty(status))
			param.setStatus(status.trim());
		// 设置正确的日期格式
		if (!StringUtils.isEmpty(createTime))
			CustomDateUtils.setTimeRange(param, createTime);
		param.setOffset(offset);
		param.setLimit(limit);

		int totalCount = groupService.getTotalCount(param);
		List<Group> groups = groupService.listGroup(param);
		// 因为layui数据表格的限制，故将数据转成表格需要的格式
		formatGroup(groups);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("count", totalCount);
		map.put("msg", "");
		map.put("data", groups);
		return map;
	}

	/**
	 * 因为layui数据表格的限制，故将数据转成表格需要的格式
	 * @param fields
	 */
	private void formatGroup(List<Group> groups) {
		for (Group group : groups) {
			group.setCreateUserName(group.getUser().getUsername());
			if (Group.PROJECT_GROUP_STATUS_WAIT_ADD_PROJECT.equals(group.getStatus())) {
				group.setStatus("待添加项目");
			}
			if (Group.PROJECT_GROUP_STATUS_WAIT_ALLOCATE.equals(group.getStatus())) {
				group.setStatus("待分配专家");
			}
			if (Group.PROJECT_GROUP_STATUS_ALLOCATE_DONE.equals(group.getStatus())) {
				group.setStatus("已分配专家");
			}
		}
	}

	/**
	 * 修改項目分組信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/update")
	public Result<String> update(HttpSession session, Group g) throws Exception {
		SysUser userSession = (SysUser) session.getAttribute("user");
		if (userSession == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		Result<String> result = new Result<String>();
		Group group = groupService.getById(g.getId());
		if (group == null) {
			result.setData("该分组不存在，请刷新页面重新查看");
			return result;
		}
		group.setGroupName(g.getGroupName());
		group.setLastUpdateTime(new Date());
		int rows = groupService.update(group);
		if (rows > 0) { 
			result.setData("修改成功");
		} else {
			result.setData("修改失败");
		}
		return result;
	}
	
	/**
	 * 删除分組信息
	 * 
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping("/delete")
	public void delete(HttpSession session, Long id) {
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		// 删除领域
		groupService.deleteById(id);
		
	}	*/
	
	/**
	 * 添加项目分组
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addGroup")
	public String addGroup(HttpSession session, Model model, Group group, String token) throws Exception {
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		if (session.getAttribute("token") == null) {
			model.addAttribute("message", "请勿重复提交表单");
			return "system/groupManage/groupManage";
		}
		if (session.getAttribute("token").equals(token)) {
			groupService.insert(group,user);
			model.addAttribute("message", "添加成功");
			session.removeAttribute("token");
		}
		return "system/groupManage/groupManage";
	}
	
	/**
	 * 为项目分组分配专家
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/allocate")
	public String allocate(HttpSession session, Model model, Long groupId, Long expertId) throws Exception {
		SysUser user = (SysUser) session.getAttribute("user");
		if (user == null) {
			throw new CustomException("未登录，请先登录", "/prs/");
		}
		GroupAllocateParam param = new GroupAllocateParam();
		param.setExpertId(expertId);
		param.setGroupId(groupId);
		param.setUser(user);
		groupService.allocate(param);
		model.addAttribute("message", "分配成功");
		return "system/allocateManage/allocate";
	}
	
	
}
