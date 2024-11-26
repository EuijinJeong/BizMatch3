package com.ktdsuniversity.edu.bizmatch.project.review.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.bizmatch.project.review.dao.ReviewDao;
import com.ktdsuniversity.edu.bizmatch.project.review.service.ReviewService;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.DeleteReviewVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.WriteReviewVO;
import com.ktdsuniversity.edu.bizmatch.report.vo.ReviewReportVO;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	public List<ReviewVO> selectAllReviews(String pjId) {
		return this.reviewDao.selectAllReviews(pjId);
	}
	
	@Override
	public ReviewVO selectOneReview(String rvwId) {
		return this.reviewDao.selectOneReview(rvwId);
	}
	
	@Transactional
	@Override
	public boolean insertOneReview(WriteReviewVO writeReviewVO) {
		int insertCount = this.reviewDao.insertOneReview(writeReviewVO);
		return insertCount > 0;
	}
	
	@Transactional
	@Override
	public boolean updateReviewReportCount(String rvwId) {
		int updateCount = this.reviewDao.updateReviewReportCount(rvwId);
		return updateCount > 0;
	}
	
	@Transactional
	@Override
	public boolean deleteOneReview(DeleteReviewVO deleteReviewVO) {
		int deleteCount = this.reviewDao.deleteOneReview(deleteReviewVO);
		return deleteCount > 0;
	}
}
