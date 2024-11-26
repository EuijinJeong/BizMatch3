 package com.ktdsuniversity.edu.bizmatch.file.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.bizmatch.file.dao.FileDao;
import com.ktdsuniversity.edu.bizmatch.file.vo.FileVO;
import com.ktdsuniversity.edu.bizmatch.file.vo.ProjectApplyFileVO;
import com.ktdsuniversity.edu.bizmatch.file.vo.ProjectFileVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberPortfolioAttVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectApplyAttVO;

@Repository
public class FileDaoImpl extends SqlSessionDaoSupport implements FileDao{
	
	public String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.file.dao.FileDao";
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int insertFile(FileVO fileVO) {
		return getSqlSession().insert(NAMESPACE + ".insertFile", fileVO);
	}
	
	@Override
	public int insertPortfolioFile(MemberPortfolioAttVO memberPortfolioAttVO) {
		return getSqlSession().insert(NAMESPACE + ".insertPortfolioFile", memberPortfolioAttVO);
	}
	
	@Override
	public List<MemberPortfolioAttVO> selectPortfolioFileList(String mbrPrtflId) {
		return getSqlSession().selectList(NAMESPACE + ".selectPortfolioFileList", mbrPrtflId);
	}

	@Override
	public int deletePortfolioAllAtt(String mbrPrtflId) {
		return getSqlSession().delete(NAMESPACE + ".deletePortfolioAllAtt", mbrPrtflId);
	}

	@Override
	public int selectPortfolioAllAttCnt(String mbrPrtflId) {
		return getSqlSession().selectOne(NAMESPACE + ".selectPortfolioAllAttCnt", mbrPrtflId);
	}

	@Override
	public int insertProjectFile(ProjectFileVO projectFileVO) {
		
		return this.getSqlSession().insert(NAMESPACE + ".insertProjectFile" ,projectFileVO);
	}

	@Override
	public int insertApplyProjectFile(ProjectApplyFileVO projectApplyFileVO) {
		
		return this.getSqlSession().insert(NAMESPACE + ".insertApplyProjectFile",projectApplyFileVO);
	}
	
	@Override
	public List<ProjectApplyAttVO> selectAllProjectApplyAtt(String pjApplyId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAllProjectApplyAtt", pjApplyId);
	}
}
