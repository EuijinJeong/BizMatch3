package com.ktdsuniversity.edu.bizmatch.admin.member.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.bizmatch.admin.member.dao.AdminMemberDao;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

@Repository
public class AdminMemberDaoImpl extends SqlSessionDaoSupport implements AdminMemberDao{
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int updateOneMemberStt(String email) {
		return this.getSqlSession().update(NAMESPACE+".updateOneMemberStt", email);
	}
	
	@Override
	public MemberVO selectOneMember(String email) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectOneMember", email);
	}
}
