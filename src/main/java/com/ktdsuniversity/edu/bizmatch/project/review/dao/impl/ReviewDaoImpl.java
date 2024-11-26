package com.ktdsuniversity.edu.bizmatch.project.review.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.bizmatch.project.review.dao.ReviewDao;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.DeleteReviewVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.WriteReviewVO;

@Repository
public class ReviewDaoImpl extends SqlSessionDaoSupport implements ReviewDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<ReviewVO> selectAllReviews(String pjId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAllReviews", pjId);
	}
	
	@Override
	public ReviewVO selectOneReview(String rvwId) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectOneReview", rvwId);
	}
	
	@Override
	public int insertOneReview(WriteReviewVO writeReviewVO) {
		return this.getSqlSession().insert(NAMESPACE + ".insertOneReview", writeReviewVO);
	}
	
	@Override
	public int updateReviewReportCount(String rvwId) {
		return this.getSqlSession().update(NAMESPACE + ".updateReviewReportCount", rvwId);
	}
	
	@Override
	public int deleteOneReview(DeleteReviewVO deleteReviewVO) {
		return this.getSqlSession().delete(NAMESPACE + ".deleteOneReview", deleteReviewVO);
	}
}
