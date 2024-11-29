package com.ktdsuniversity.edu.bizmatch.admin.project.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.bizmatch.admin.project.dao.AdminProjectDao;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;

@Repository
public class AdminProjectDaoImpl extends SqlSessionDaoSupport implements AdminProjectDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	/**
	 * 모든 프로젝트 불러오기
	 */
	@Override
	public List<ProjectVO> selectAllProject() {
		return this.getSqlSession().selectList(NAMESPACE+".selectAllProject");
	}
	
	/**
	 * 한 개 프로젝트 불러오기
	 */
	@Override
	public ProjectVO selectOneProject(String pjId) {
		return this.getSqlSession().selectOne(NAMESPACE+".selectOneProject", pjId);
	}

	/**
	 * 한개의 프로젝트 삭제하기
	 */
	@Override
	public int deleteOneProject(String pjId) {
		return this.getSqlSession().delete(NAMESPACE+".deleteOneProject", pjId);
	}

	/**
	 * 여러개의 프로젝트 한번에 삭제하기~
	 */
	@Override
	public int deleteProject(List<String> pjIdList) {
		return this.getSqlSession().delete(NAMESPACE+".deleteProject", pjIdList);
	}

	
	
}
