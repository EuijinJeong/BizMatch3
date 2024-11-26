package com.ktdsuniversity.edu.bizmatch.common.category.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.bizmatch.common.category.dao.CategoryDao;
import com.ktdsuniversity.edu.bizmatch.common.vo.IndstrInfoVO;

@Repository
public class CategoryDaoImpl extends SqlSessionDaoSupport implements CategoryDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<IndstrInfoVO> selectSubIndstr(String indstrId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectSubIndstr", indstrId);
	}
}
