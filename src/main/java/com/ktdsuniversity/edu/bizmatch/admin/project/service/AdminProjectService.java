package com.ktdsuniversity.edu.bizmatch.admin.project.service;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;

public interface AdminProjectService {
	
	public List<ProjectVO> readAllProject();
	
	public ProjectVO readOneProject(String pjId);
	
	public boolean deleteProject(List<String> pjIdList);
	
	
	

}
