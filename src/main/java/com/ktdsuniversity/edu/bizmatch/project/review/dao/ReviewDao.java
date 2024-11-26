package com.ktdsuniversity.edu.bizmatch.project.review.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.project.review.vo.DeleteReviewVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.WriteReviewVO;

public interface ReviewDao {

	public String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.project.review.dao.ReviewDao";
	
	/**
	 * 특정 프로젝트의 모든 리뷰 조회
	 * @param pjId 프로젝트 ID
	 * @return 해당 프로젝트의 리뷰 List
	 */
	public List<ReviewVO> selectAllReviews(String pjId);
	
	/**
	 * 특정 리뷰 아이디로 하나의 리뷰 조회
	 * @param rvwId 리뷰 아이디
	 * @return 해당 리뷰 VO 객체
	 */
	public ReviewVO selectOneReview(String rvwId);
	
	/**
	 * 새 리뷰 등록
	 * @param writeReviewVO 등록할 리뷰 정보
	 * @return 등록된 리뷰 개수
	 */
	public int insertOneReview(WriteReviewVO writeReviewVO);
	
	/**
	 * 리뷰 신고 횟수 수정
	 * @param rvwId 리뷰 아이디
	 * @return 수정된 리뷰 신고 횟수 개수
	 */
	public int updateReviewReportCount(String rvwId);
	
	/**
	 * 하나의 리뷰 삭제
	 * @param deleteReviewVO 삭제할 리뷰 정보
	 * @return 삭제된 리뷰 개수
	 */
	public int deleteOneReview(DeleteReviewVO deleteReviewVO);
}
