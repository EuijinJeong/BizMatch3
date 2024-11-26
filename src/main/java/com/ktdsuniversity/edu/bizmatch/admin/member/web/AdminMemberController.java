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
}
