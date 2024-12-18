package com.ktdsuniversity.edu.bizmatch.admin.report.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.admin.report.service.ReportService;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;

@RestController
@RequestMapping("/api/admin")
public class AdminReportController {
	@Autowired
	private ReportService reportService;
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/report/review")
	public ApiResponse readAllReportReview(Authentication authentication) {
		MemberVO loginMemberVO = (MemberVO)authentication.getPrincipal();
		int mbrCtgry = loginMemberVO.getMbrCtgry();
		
		if(mbrCtgry != 2) {
			return new ApiResponse(HttpStatus.FORBIDDEN, "관리자만 접속이 가능합니다.");
		}
		
		List<ReviewVO> allReportReview= this.reportService.readAllReportReview();
		return new ApiResponse(allReportReview);
	}
	
	/**
	 * 
	 * @param rprtIds
	 * @return
	 */
	@PostMapping("/report/delete")
	public ApiResponse deleteReportInfo(Authentication authentication,@RequestBody List<String> rprtIds) {
		MemberVO loginMemberVO = (MemberVO)authentication.getPrincipal();
		int mbrCtgry = loginMemberVO.getMbrCtgry();
		
		if(mbrCtgry != 2) {
			return new ApiResponse(HttpStatus.FORBIDDEN, "관리자만 접속이 가능합니다.");
		}
		
		boolean isSuccess = this.reportService.deleteReportInfo(rprtIds);
		return new ApiResponse(isSuccess);
	}
	
	/**
	 * 
	 * @param rvwIds
	 * @return
	 */
	@PostMapping("/review/delete")
	public ApiResponse deleteReview(Authentication authentication, @RequestBody List<String> rvwIds) {
		MemberVO loginMemberVO = (MemberVO)authentication.getPrincipal();
		int mbrCtgry = loginMemberVO.getMbrCtgry();
		
		if(mbrCtgry != 2) {
			return new ApiResponse(HttpStatus.FORBIDDEN, "관리자만 접속이 가능합니다.");
		}
		
		boolean isSuceess = this.reportService.deleteReview(rvwIds);
		return new ApiResponse(isSuceess);
	}
	
	/**
	 * asdasd
	 * @param rprtIds
	 * @return
	 */
	@PostMapping("/report/check")
	public ApiResponse checkReportInfo(Authentication authentication, @RequestBody List<String> rprtIds) {
		MemberVO loginMemberVO = (MemberVO)authentication.getPrincipal();
		int mbrCtgry = loginMemberVO.getMbrCtgry();
		
		if(mbrCtgry != 2) {
			return new ApiResponse(HttpStatus.FORBIDDEN, "관리자만 접속이 가능합니다.");
		}
		
		boolean isSuccess = this.reportService.updateCheckReportReview(rprtIds);
		return new ApiResponse(isSuccess);
	}
	
}
