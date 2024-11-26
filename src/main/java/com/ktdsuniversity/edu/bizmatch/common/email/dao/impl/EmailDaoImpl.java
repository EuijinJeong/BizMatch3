package com.ktdsuniversity.edu.bizmatch.common.email.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.bizmatch.common.email.dao.EmailDao;
import com.ktdsuniversity.edu.bizmatch.common.email.vo.UserEmailAuthNumVO;

@Repository
public class EmailDaoImpl extends SqlSessionDaoSupport implements EmailDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int insertTempEmailAuthNum(UserEmailAuthNumVO userEmailAuthNumVO) {
		return getSqlSession().insert(NAMESPACE + ".insertTempEmailAuthNum", userEmailAuthNumVO);
	}
	@Override
	public int selectMemberForSignUp(String email) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectMemberForSignUp", email);
	}
	@Override
	public int selectTempEmailAuthNum(UserEmailAuthNumVO userEmailAuthNumVO) {
		return this.getSqlSession().selectOne(NAMESPACE+".selectTempEmailAuthNum",userEmailAuthNumVO);

	}
	@Override
	public int deleteTempEmailAuthNum(String authNum) {
		return this.getSqlSession().delete(NAMESPACE + ".deleteTempEmailAuthNum", authNum);
	}
}
