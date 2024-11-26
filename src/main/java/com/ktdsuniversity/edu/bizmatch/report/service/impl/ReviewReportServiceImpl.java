package com.ktdsuniversity.edu.bizmatch.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.bizmatch.common.exceptions.comment.ReviewReportFailException;
import com.ktdsuniversity.edu.bizmatch.project.review.service.ReviewService;
import com.ktdsuniversity.edu.bizmatch.report.dao.ReviewReportDao;
import com.ktdsuniversity.edu.bizmatch.report.service.ReviewReportService;
import com.ktdsuniversity.edu.bizmatch.report.vo.ReviewReportVO;
import com.ktdsuniversity.edu.bizmatch.report.vo.WriteReviewReportVO;

@Service
public class ReviewReportServiceImpl implements ReviewReportService{

	@Autowired
	private ReviewReportDao reviewReportDao;
	
	@Autowired
	private ReviewService reviewService;
	
	@Override
	public List<ReviewReportVO> selectAllReviewReports(String cmmntId) {
		return this.reviewReportDao.selectAllReviewReports(cmmntId);
	}
	
	@Override
	public ReviewReportVO selectOneReviewReport(String rprtId) {
		return this.reviewReportDao.selectOneReviewReport(rprtId);
	}
	
	@Transactional
	@Override
	public boolean insertOneReviewReport(WriteReviewReportVO writeReviewReportVO) {
		ReviewReportVO reviewReportVO = new ReviewReportVO();
		reviewReportVO.setCmmntId(writeReviewReportVO.getCmmntId());
		reviewReportVO.setEmilAddr(writeReviewReportVO.getEmilAddr());
		
		boolean isReported = this.countReviewReport(reviewReportVO);
		
		if(isReported) {
			throw new ReviewReportFailException("이미 신고한 리뷰입니다");
		}
		
		int insertCount = this.reviewReportDao.insertOneReviewReport(writeReviewReportVO);
		
		if(insertCount > 0) {
			return this.reviewService.updateReviewReportCount(writeReviewReportVO.getCmmntId());
		}
		return false;
	}
	
	@Override
	public boolean countReviewReport(ReviewReportVO reviewReportVO) {
		int reviewReportCount = this.reviewReportDao.countReviewReport(reviewReportVO);
		return reviewReportCount > 0;
	}
}
