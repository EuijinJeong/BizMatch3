package com.ktdsuniversity.edu.bizmatch.project.review.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.common.exceptions.comment.ReviewFailException;
import com.ktdsuniversity.edu.bizmatch.common.utils.ParameterCheck;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.project.review.service.ReviewService;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.DeleteReviewVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.WriteReviewVO;

@RestController
@RequestMapping("/api")
public class ReviewController {

	public static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	private ReviewService reviewService;
	
	/**
	 * 리뷰 목록을 조회하는 컨트롤러.
	 * @param pjId
	 * @return
	 */
	@GetMapping("/project/{pjId}/reviewlist")
	public ApiResponse selectAllReviews(Authentication memberVO
										,@PathVariable String pjId) {
		
		List<ReviewVO> reviewList = reviewService.selectAllReviews(pjId);	
		return new ApiResponse(reviewList);
	}
	
	/**
	 * 리뷰 리스트 최신순으로 불러오는 컨트롤러.
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/myreview/latedate")
	public ApiResponse getAllReviewSortedLateDate(Authentication memberVO) {
		MemberVO member = (MemberVO) memberVO.getPrincipal();
		List<ReviewVO> reviewList = this.reviewService.readReviewListSortLowRate(member.getCmpId());
		return new ApiResponse(reviewList);
	}
	
	/**
	 * 리뷰 리스트 별점 높은순 으로 불러오는 컨트롤러.
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/myreview/highrate")
	public ApiResponse getAllReviewSortedHighRate(Authentication memberVO) {
		MemberVO member = (MemberVO) memberVO.getPrincipal();
		List<ReviewVO> reviewList = this.reviewService.readReviewListSortedHighRate(member.getCmpId());
		return new ApiResponse(reviewList);
	}
	
	/**
	 * 리뷰 리스트 별점 낮은순 으로 불러오는 컨트롤러.
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/myreview/lowrate")
	public ApiResponse getAllReviewSortedLowRate(Authentication memberVO) {
		MemberVO member = (MemberVO) memberVO.getPrincipal();
		List<ReviewVO> reviewList = this.reviewService.readReviewListSortLowRate(member.getCmpId());
		return new ApiResponse(reviewList);
	}
	
	/**
	 * 리뷰 작성을 하는 컨트롤러.
	 * @param pjId
	 * @param writeReviewVO
	 * @param memberVO
	 * @return
	 */
	@PostMapping("/project/{pjId}/review")
	public ApiResponse doInsertNewReviews(@PathVariable String pjId
										, @RequestBody WriteReviewVO writeReviewVO
										, Authentication memberVO) {
		
		if(ParameterCheck.parameterCodeValid(writeReviewVO.getRvwCntnt(), 0)) {
			throw new ReviewFailException("리뷰 내용을 입력해주세요");
		}
		
		if(writeReviewVO.getScr() < 0 || writeReviewVO.getScr() > 5) {
			throw new ReviewFailException("별점은 0 이상 5점 이하만 가능합니다");
		}
		
		MemberVO member = (MemberVO) memberVO.getPrincipal();
		
		writeReviewVO.setPjId(pjId);
		writeReviewVO.setEmilAddr(member.getEmilAddr());
		
		boolean isSuccess = reviewService.insertOneReview(writeReviewVO);
		
		return new ApiResponse(isSuccess);
	}
	
	/**
	 * 리뷰 신고 횟수를 추가하는 컨트롤러.
	 * @param rvwId
	 * @param reviewVO
	 * @param memberVO
	 * @return
	 */
	@PostMapping("/project/review/modify/{rvwId}")
	public ApiResponse doUpdateReview(@PathVariable String rvwId
									,@RequestBody ReviewVO reviewVO, Authentication memberVO) {
		
		reviewVO.setRvwId(rvwId);
		reviewVO.setEmilAddr(memberVO.getName());
		
		boolean isSuccess = reviewService.updateReviewReportCount(rvwId);
		
		return new ApiResponse(isSuccess);
	}

	/**
	 * 특정 리뷰 삭제하는 컨트롤러.
	 * @param rvwId
	 * @param deleteReviewVO
	 * @param memberVO
	 * @return
	 */
	@PostMapping("/project/review/delete/{rvwId}")
	public ApiResponse doDeleteReview(@PathVariable String rvwId
											, DeleteReviewVO deleteReviewVO
											, Authentication memberVO) {
		
		ReviewVO existingReview = reviewService.selectOneReview(rvwId);
		
		if(existingReview == null) {
			throw new ReviewFailException("삭제하려는 리뷰가 존재하지 않습니다");
		}
		
		if(!existingReview.getEmilAddr().equals(memberVO.getName())) {
			throw new ReviewFailException("삭제 권한이 없습니다");
		}
		
		deleteReviewVO.setRvwId(rvwId);
		deleteReviewVO.setEmilAddr(memberVO.getName());
		
		boolean isSuccess = reviewService.deleteOneReview(deleteReviewVO);
		
		return new ApiResponse(isSuccess);
	}
}
