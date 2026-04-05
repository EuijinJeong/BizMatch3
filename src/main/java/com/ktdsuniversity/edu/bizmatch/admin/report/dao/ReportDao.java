package com.ktdsuniversity.edu.bizmatch.admin.report.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;
import com.ktdsuniversity.edu.bizmatch.report.vo.ReviewReportVO;

/**
 * 리뷰 신고 
 * 1. 리뷰 신고 정보 가져오기
 * 2. 잘못된 신고 정보 없애기(리뷰 신고 없애기) -> 리뷰 테이블 리뷰신고 카운트 하나 줄이
 * 3. 리뷰 삭제
 * 4. IS_RPRT 신고 확인 후 처리
 */
public interface ReportDao {
	public static final String NAMESPACE="com.ktdsuniversity.edu.bizmatch.admin.report.dao.ReportDao";
	
	public List<ReviewVO> selectAllReportReview();
	
	public List<ReviewReportVO> selectOneReviewReport(List<String> rprtId);
	/**
	 * 잘못된 신고 정보 없애
	 * @param rprtId 리뷰 신고정보 ID
	 * @return
	 */
	public int deleteReportReview(List<String> rprtId);
	
	/**
	 * 리뷰 신고 카운트 1 줄여주가.
	 * @param rvwId 리뷰 아이
	 * @return
	 */
	public int updateReviewReportCnt(String rvwId);
	
	public int deleteReview(List<String> rvwId);
	
	public int updateIsRprt(List<String> rprtId);
}
