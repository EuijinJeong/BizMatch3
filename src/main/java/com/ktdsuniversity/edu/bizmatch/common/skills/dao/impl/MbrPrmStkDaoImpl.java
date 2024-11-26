package com.ktdsuniversity.edu.bizmatch.common.skills.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.bizmatch.common.skills.dao.MbrPrmStkDao;
import com.ktdsuniversity.edu.bizmatch.common.skills.vo.MbrPrmStkVO;

@Repository
public class MbrPrmStkDaoImpl extends SqlSessionDaoSupport implements MbrPrmStkDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertMbrSkill(MbrPrmStkVO mbrPrmStkVO) {
		return this.getSqlSession().insert(NAMESPACE + ".insertMbrSkill", mbrPrmStkVO);
	}

	@Override
	public List<MbrPrmStkVO> selectMbrSkill(String email) {
		return this.getSqlSession().selectList(NAMESPACE+".selectMbrSkill",email);
	}

	@Override
	public int deleteMbrSkill(String emilAddr) {
		return this.getSqlSession().delete(NAMESPACE + ".deleteMbrSkill", emilAddr);
	}

	@Override
	public List<MbrPrmStkVO> selectMbrCmpnySkill(String cmpId) {
		return this.getSqlSession().selectList(NAMESPACE+".selectMbrCmpnySkill", cmpId);
	}

}