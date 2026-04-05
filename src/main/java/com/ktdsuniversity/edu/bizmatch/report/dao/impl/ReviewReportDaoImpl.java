package com.ktdsuniversity.edu.bizmatch.report.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.bizmatch.report.dao.ReviewReportDao;
import com.ktdsuniversity.edu.bizmatch.report.vo.ReviewReportVO;
import com.ktdsuniversity.edu.bizmatch.report.vo.WriteReviewReportVO;

@Repository
public class ReviewReportDaoImpl extends SqlSessionDaoSupport implements ReviewReportDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public List<ReviewReportVO> selectAllReviewReports(String cmmntId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAllReviewReports", cmmntId);
	}
	
	@Override
	public ReviewReportVO selectOneReviewReport(String rprtId) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectOneReviewReport", rprtId)  ;
	}
	
	@Override
	public int insertOneReviewReport(WriteReviewReportVO writeReviewReportVO) {
		return this.getSqlSession().insert(NAMESPACE + ".insertOneReviewReport", writeReviewReportVO);
	}
	
	@Override
	public int countReviewReport(ReviewReportVO reviewReportVO) {
		return this.getSqlSession().selectOne(NAMESPACE + ".countReviewReport", reviewReportVO);
	}
}
