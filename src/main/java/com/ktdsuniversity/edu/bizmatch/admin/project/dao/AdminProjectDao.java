package com.ktdsuniversity.edu.bizmatch.admin.project.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;

/*
 * 1. 모든 게시물을 볼 수 있는 기능
 * 2. 게시물을 삭제할 수 있는 기능
 */
public interface AdminProjectDao {
	public final static String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.admin.project.dao.AdminProjectDao";
	
	public List<ProjectVO> selectAllProject();
	
	public ProjectVO selectOneProject(String pjId);
	
	public int deleteOneProject(String pjId);
	
	public int deleteProject(List<String> pjIdList);
	
	

}
