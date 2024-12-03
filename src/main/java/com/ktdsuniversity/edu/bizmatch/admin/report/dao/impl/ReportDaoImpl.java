package com.ktdsuniversity.edu.bizmatch.admin.report.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.bizmatch.admin.report.dao.ReportDao;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;
import com.ktdsuniversity.edu.bizmatch.report.vo.ReviewReportVO;

@Repository
public class ReportDaoImpl extends SqlSessionDaoSupport implements ReportDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<ReviewVO> selectAllReportReview() {
		return this.getSqlSession().selectList(NAMESPACE+".selectAllReportReview");
	}

	@Override
	public int deleteReportReview(List<String> rprtId) {
		return this.getSqlSession().delete(NAMESPACE+".deleteReportReview", rprtId);
	}

	@Override
	public int deleteReview(List<String> rvwId) {
		return this.getSqlSession().update(NAMESPACE+".deleteReview", rvwId);
	}

	@Override
	public int updateIsRprt(List<String> rprtId) {
		return this.getSqlSession().update(NAMESPACE+".updateIsRprt", rprtId);
	}

	@Override
	public List<ReviewReportVO> selectOneReviewReport(List<String> rprtId) {
		return this.getSqlSession().selectList(NAMESPACE+".selectOneReviewReport", rprtId);
	}

	@Override
	public int updateReviewReportCnt(String rvwId) {
		return this.getSqlSession().update(NAMESPACE+".updateReviewReportCnt", rvwId);
	}
	
	
}
