package com.ktdsuniversity.edu.bizmatch.project.review.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.bizmatch.common.exceptions.comment.ReviewFailException;
import com.ktdsuniversity.edu.bizmatch.common.utils.ParameterCheck;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.project.review.service.ReviewService;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.DeleteReviewVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.WriteReviewVO;

@Controller
public class ReviewController {

	public static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	private ReviewService reviewService;
	

	// 리뷰 목록 조회
	@ResponseBody
	@GetMapping("/project/{pjId}/reviewlist")
	public Map<String, Object> selectAllReviews(@PathVariable String pjId) {
		List<ReviewVO> reviewList = reviewService.selectAllReviews(pjId);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("reviews", reviewList);
		
		return resultMap;
	}
	
	// 리뷰 등록
	
	@PostMapping("/project/{pjId}/review")
	@ResponseBody
	public Map<String, Object> doInsertNewReviews(@PathVariable String pjId
												, @RequestBody WriteReviewVO writeReviewVO
												, @SessionAttribute(value = "_LOGIN_USER_" , required = false)MemberVO memberVO) {
		logger.debug(writeReviewVO.toString());
		
		if(memberVO.getEmilAddr() == null || memberVO.getEmilAddr().isEmpty()) {
			throw new ReviewFailException("로그인이 필요합니다");
		}
		
		if(ParameterCheck.parameterCodeValid(writeReviewVO.getRvwCntnt(), 0)) {
			throw new ReviewFailException("리뷰 내용을 입력해주세요");
		}
		
		if(writeReviewVO.getScr() < 0 || writeReviewVO.getScr() > 5) {
			throw new ReviewFailException("별점은 0 이상 5점 이하만 가능합니다");
		}
		
		writeReviewVO.setPjId(pjId);
		writeReviewVO.setEmilAddr(memberVO.getEmilAddr());
		
		boolean isSuccess = reviewService.insertOneReview(writeReviewVO);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		
		return resultMap;
	}
	
	// 리뷰 수정: 리뷰 신고횟수 추가
	@ResponseBody
	@PostMapping("/project/review/modify/{rvwId}")
	public Map<String, Object> doUpdateReview(@PathVariable String rvwId,@RequestBody ReviewVO reviewVO, @SessionAttribute("_LOGIN_USER_") MemberVO memberVO) {
		
		reviewVO.setRvwId(rvwId);
		reviewVO.setEmilAddr(memberVO.getEmilAddr());
		
		boolean isSuccess = reviewService.updateReviewReportCount(rvwId);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		
		return resultMap;
	}
	
	// 리뷰 삭제: 리뷰 isDlt 변경
	@ResponseBody
	@PostMapping("/project/review/delete/{rvwId}")
	public Map<String, Object> doDeleteReview(@PathVariable String rvwId, DeleteReviewVO deleteReviewVO, @SessionAttribute("_LOGIN_USER_") MemberVO memberVO) {
		
		ReviewVO existingReview = reviewService.selectOneReview(rvwId);
		
		if(existingReview == null) {
			throw new ReviewFailException("삭제하려는 리뷰가 존재하지 않습니다");
		}
		
		if(!existingReview.getEmilAddr().equals(memberVO.getEmilAddr())) {
			throw new ReviewFailException("삭제 권한이 없습니다");
		}
		
		deleteReviewVO.setRvwId(rvwId);
		deleteReviewVO.setEmilAddr(memberVO.getEmilAddr());
		
		boolean isSuccess = reviewService.deleteOneReview(deleteReviewVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		
		return resultMap;
	}
	
	@GetMapping("/review/reviewpage")
	public String loadReviewPage() {
		return "common/review";
	}
}
