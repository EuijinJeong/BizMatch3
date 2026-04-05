package com.ktdsuniversity.edu.bizmatch.report.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.report.vo.ReviewReportVO;
import com.ktdsuniversity.edu.bizmatch.report.vo.WriteReviewReportVO;

public interface ReviewReportDao {

	public String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.report.dao.ReviewReportDao";

	/**
	 * 특정 리뷰의 모든 리뷰 신고 조회
	 * 
	 * @param cmmntId 리뷰 아이디
	 * @return 해당 리뷰의 신고 List
	 */
	public List<ReviewReportVO> selectAllReviewReports(String cmmntId);

	/**
	 * 특정 리뷰 신고 아이디로 하나의 리뷰 신고 조회
	 * 
	 * @param rprtId 리뷰 신고 아이디
	 * @return 해당 리뷰 신고 객체
	 */
	public ReviewReportVO selectOneReviewReport(String rprtId);

	/**
	 * 리뷰 신고 등록
	 * 
	 * @param writeReviewReportVO 등록할 리뷰 신고 정보
	 * @return 등록된 리뷰 신고 개수
	 */
	public int insertOneReviewReport(WriteReviewReportVO writeReviewReportVO);
	
	/**
	 * 리뷰 신고 개수 조회
	 * @param reviewReportVO
	 * @return 리뷰 신고 개수 조회
	 */
	public int countReviewReport(ReviewReportVO reviewReportVO);
}
