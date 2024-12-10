package com.ktdsuniversity.edu.bizmatch.report.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ktdsuniversity.edu.bizmatch.common.exceptions.comment.ReviewReportFailException;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.project.review.service.ReviewService;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;
import com.ktdsuniversity.edu.bizmatch.report.service.ReviewReportService;
import com.ktdsuniversity.edu.bizmatch.report.vo.ReviewReportVO;
import com.ktdsuniversity.edu.bizmatch.report.vo.WriteReviewReportVO;

@RestController
@RequestMapping("/api")
public class ReviewReportController {
	
	public static final Logger logger = LoggerFactory.getLogger(ReviewReportController.class);
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ReviewReportService reviewReportService;
	
	@GetMapping("/review/{cmmntId}/reportlist")
	public Map<String, Object> selectAllReviewReports(@PathVariable String cmmntId) {
		List<ReviewReportVO> reviewReportList = reviewReportService.selectAllReviewReports(cmmntId);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("reviewReportList", reviewReportList);
		resultMap.put("count", reviewReportList.size());
		
		return resultMap;
	}
	
	@GetMapping("/review/report/{rprtId}")
	public Map<String, Object> selectOneReviewReport(@PathVariable String rprtId) {
		ReviewReportVO reviewReport = reviewReportService.selectOneReviewReport(rprtId);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("reviewReport", reviewReport);
		
		return resultMap;
	}

	/**
	 * 특정 리뷰를 신고하는 컨트롤러.
	 * @param cmmntId
	 * @param writeReviewReportVO
	 * @param memberVO
	 * @return
	 */
	@PostMapping("/review/{cmmntId}/reviewreport")
	public Map<String, Object> doInsertNewReport(@PathVariable String cmmntId
											, @RequestBody WriteReviewReportVO writeReviewReportVO
											, Authentication memberVO) {
		
		ReviewVO existingReview = reviewService.selectOneReview(cmmntId);
		
		if(existingReview == null) {
			throw new ReviewReportFailException("신고하려는 리뷰가 존재하지 않습니다");
		}
		
		writeReviewReportVO.setCmmntId(cmmntId);
		MemberVO member = (MemberVO) memberVO.getPrincipal();
		writeReviewReportVO.setEmilAddr(member.getEmilAddr());
		
		boolean isSuccess = reviewReportService.insertOneReviewReport(writeReviewReportVO);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", isSuccess);
		
		return resultMap;
	}
}
