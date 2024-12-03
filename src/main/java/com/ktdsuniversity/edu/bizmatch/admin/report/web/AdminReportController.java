package com.ktdsuniversity.edu.bizmatch.admin.report.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.admin.report.service.ReportService;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;

@RestController
@RequestMapping("/api")
public class AdminReportController {
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/report/review")
	public ApiResponse readAllReportReview() {
		List<ReviewVO> allReportReview= this.reportService.readAllReportReview();
		return new ApiResponse(allReportReview);
	}
	@GetMapping("/report/delete")
	public ApiResponse deleteReportInfo(@RequestBody List<String> rprtIds) {
		boolean isSuccess = this.reportService.deleteReportInfo(rprtIds);
		return new ApiResponse(isSuccess);
	}
	@GetMapping("/review/delete")
	public ApiResponse deleteReview(@RequestBody List<String> rvwIds) {
		boolean isSuceess = this.reportService.deleteReview(rvwIds);
		return new ApiResponse(isSuceess);
	}
	@GetMapping("/report/check")
	public ApiResponse checkReportInfo(@RequestBody List<String> rprtIds) {
		boolean isSuccess = this.reportService.updateCheckReportReview(rprtIds);
		return new ApiResponse(isSuccess);
	}
	
}
