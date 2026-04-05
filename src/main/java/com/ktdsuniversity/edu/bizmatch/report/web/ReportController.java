package com.ktdsuniversity.edu.bizmatch.report.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 모든 구역의 신고 기능에 관한 컨트롤러
 * /comment/report >> 댓글 신고 주소
 * /board/report >> 게시글 신고 주소
 * /project/report >> 프로젝트 신고 주소
 * report/commentreport >> report 폴더의 댓글 신고 화면의 commentreport.jsp 주소
 * report/boardreport >> report 폴더의 게시글 신고 화면의 boardreport.jsp 주소
 * report/projectreport >> report 폴더의 프로젝트 신고 화면의 projectreport.jsp 주소
 * @author 한상범
 */
@Controller
public class ReportController {

	@GetMapping("/comment/report")
	private String viewCommentReportModel() {
		return "report/commentreport";
	}
	
	@GetMapping("/board/report")
	private String viewBoardReportModel() {
		return "report/boardreport";
	}
	
	@GetMapping("/project/report")
	private String viewProjectReportModel() {
		return "report/projectreport";
	}

}
