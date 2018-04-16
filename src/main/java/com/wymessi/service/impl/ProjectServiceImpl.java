package com.wymessi.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wymessi.dao.FieldDao;
import com.wymessi.dao.ProjectDao;
import com.wymessi.param.GenerateApplyParam;
import com.wymessi.param.ProjectListParam;
import com.wymessi.po.EntityField;
import com.wymessi.po.Field;
import com.wymessi.po.Project;
import com.wymessi.service.EntityFieldMappingService;
import com.wymessi.service.FieldService;
import com.wymessi.service.ProjectService;
import com.wymessi.utils.FileUtils;

@Service
public class ProjectServiceImpl implements ProjectService {

	private static List<String> validSuffixs = null; // 有效文件类型集合

	static {
		validSuffixs = new ArrayList<String>();
		validSuffixs.add(".pdf");
		validSuffixs.add(".doc");
		validSuffixs.add(".docx");
	}
	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private FieldDao fieldDao;
	
	@Autowired
	private FieldService fieldService;

	@Autowired
	private EntityFieldMappingService entityFieldMappingService;

	/**
	 * 生成申请记录
	 * 
	 * @param GenerateApplyParam
	 *            param 记录生成参数
	 */
	@Transactional
	@Override
	public void generateApply(GenerateApplyParam param) {
		// 检查文件类型是否合法
		boolean isSuffixValid = checkFileSuffix(param.getFile().getOriginalFilename());
		if (!isSuffixValid) {
			param.getModel().addAttribute("message", "不支持的文件类型");
		}
		// 将文件保存
		String filePath = null;
		try {
			filePath = FileUtils.upload(param.getFile());
			param.getModel().addAttribute("message", "申请成功，请移至 个人中心->我的申请 查看");
		} catch (IOException e) {
			param.getModel().addAttribute("message", "上传失败，请重新上传！");
			e.printStackTrace();
		}
		// 生成项目申请记录
		param.getProject().setStatus(Project.PROJECT_REVIEW_STATUS_WAIT_ALLOCATE);
		param.getProject().setFileSavePath(filePath);
		param.getProject().setCreateUserId(param.getSysUser().getId());
		param.getProject().setCreateTime(new Date());
		param.getProject().setLastUpdateTime(new Date());
		insert(param.getProject());

		// 生成有效领域标签、插入记录同时关联申请记录
		List<String> inputFields = Arrays.asList(param.getTagsinput().split("\\,")); // 用户输入的标签
		fieldService.getValidFieldAndInsertBatch(inputFields, param.getSysUser().getId());

		// 将领域标签与项目申请记录关联起来
		List<Field> Fields = fieldDao.listFieldsByNames(inputFields);  //该记录所有相关标签
		for (Field field : Fields) {
			EntityField entityField = new EntityField();
			entityField.setEntityId(param.getProject().getId());
			entityField.setFieldId(field.getId());
			entityField.setEntityType(EntityField.ENTITY_TYPE_PROJECT);
			entityFieldMappingService.insert(entityField);
		}
	}
	
	/**
	 * 检查文件是否为系统支持的类型
	 * 
	 * @param fileName
	 *            上传文件名
	 * @return
	 */
	private boolean checkFileSuffix(String fileName) {
		String suffix = fileName.substring(fileName.indexOf("."));
		if (!validSuffixs.contains(suffix))
			return false;
		return true;
	}

	/**
	 * 插入项目申请记录
	 * 
	 * @param project
	 */
	public void insert(Project project) {
		if (project != null) {
			projectDao.insert(project);
		}
	}

	/**
	 * 查询projects
	 * @param param
	 * @return
	 */
	@Override
	public List<Project> listProject(ProjectListParam param) {
		if (param != null){
			return projectDao.listProject(param);
		}
		return null;
	}

	/**
	 * 查询总记录数
	 * @param param
	 * @return
	 */
	@Override
	public int getTotalCount(ProjectListParam param) {
		if (param != null){
			return projectDao.getTotalCount(param);
		}
		return 0;
	}

	/**
	 * 更新申请记录
	 * @param project
	 * @return
	 */
	@Override
	public int update(Project project) {
		if (project != null){
			return projectDao.update(project);
		}
		return 0;
	}

	/**
	 * 通过id查询项目申请记录
	 * @param id
	 * @return
	 */
	@Override
	public Project getProjectById(Long id) {
		if (id != null ){
			return projectDao.getProjectById(id);
		}
		return null;
	}

	/**
	 * 通过id删除项目申请记录
	 * @param id
	 */
	@Override
	public void deleteById(Long id) {
		if (id != null ){
			projectDao.deleteById(id);
		}
	}
}
