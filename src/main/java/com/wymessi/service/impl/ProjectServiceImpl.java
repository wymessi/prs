package com.wymessi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wymessi.dao.ProjectDao;
import com.wymessi.po.Project;
import com.wymessi.service.ProjectService;

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

	/**
	 * 检查文件是否为系统支持的类型
	 * 
	 * @param fileName
	 *            上传文件名
	 * @return
	 */
	@Override
	public boolean checkFileSuffix(String fileName) {
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
	@Override
	public void insert(Project project) {
		if (project != null) {
			projectDao.insert(project);
		}
	}

}
