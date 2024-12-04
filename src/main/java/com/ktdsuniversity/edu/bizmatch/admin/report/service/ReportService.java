package com.ktdsuniversity.edu.bizmatch.admin.report.service;
/*
 * 1. 리뷰 신고 정보 가져오기
 * 2. 잘못된 신고 정보 없애기(리뷰 신고 없애기) -> 리뷰 테이블 리뷰신고 카운트 하나 줄이
 * 3. 리뷰 삭제
 * 4. IS_RPRT 신고 확인 후 처리
 */

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;

public interface ReportService {
	
	public List<ReviewVO> readAllReportReview();
	
	public boolean deleteReportInfo(List<String> rprtId);
	
	public boolean deleteReview(List<String> rvwId);
	
	public boolean updateCheckReportReview(List<String> rprtIds);
}
