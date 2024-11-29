package com.ktdsuniversity.edu.bizmatch.admin.project.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.bizmatch.admin.project.dao.AdminProjectDao;
import com.ktdsuniversity.edu.bizmatch.admin.project.service.AdminProjectService;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;

@Service
public class AdminProjectServiceImpl implements AdminProjectService{
	
	@Autowired
	private AdminProjectDao adminProjectDao;

	@Override
	public List<ProjectVO> readAllProject() {
		return this.adminProjectDao.selectAllProject();
	}

	@Override
	public ProjectVO readOneProject(String pjId) {
		return this.adminProjectDao.selectOneProject(pjId);
	}

	@Override
	public boolean deleteProject(List<String> pjIdList) {
		boolean isSuccess = this.adminProjectDao.deleteProject(pjIdList) > 0;
		if (!isSuccess) {
			throw new IllegalArgumentException("프로젝트 삭제 중 에러가 발생했습니다.");
		}
		return isSuccess;
	}


}
