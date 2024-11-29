package com.ktdsuniversity.edu.bizmatch.admin.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.edu.bizmatch.admin.member.service.AdminMemberService;

@Controller
public class AdminMemberController {
	
	@Autowired
	private AdminMemberService adminMemberService;
	
	@PostMapping("/update/member/status")
	public String updateMemberSignUpStatus(@RequestParam String email) {
		
		this.adminMemberService.handleMemberSignUp(email);
		return "관리자 페이지 url";
	}
	
	// 1.회원 가입 승인
	
	// 회원 가입 맴버 VO  가입 state 가 0 인 애들을 가져옴
	// 
	
	// 2.회원 활동 정지 기능
	
	// 3.신고 알람 받기
}
