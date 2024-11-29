package com.ktdsuniversity.edu.bizmatch.admin.member.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.admin.member.service.AdminMemberService;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;

@RestController
@RequestMapping("/api/admin")
public class AdminMemberController {
	
	@Autowired
	private AdminMemberService adminMemberService;
	
	/**
	 * 회원가입시 회원 활성화를 수정하는 컨트롤러.
	 * @param email
	 * @return
	 */
	@PostMapping("/update/memberstt")
	public ApiResponse postMemberStt(@RequestBody List<String> email) {
		boolean isUpdated = this.adminMemberService.updateMemberSignupStt(email); 
		return new ApiResponse(isUpdated);
	}
	
	/**
	 * 회원가입시 회원가입 승낙을 거절하는 컨트롤러.
	 * @param email
	 * @return
	 */
	@PostMapping("/delete/memberstt")
	public ApiResponse postMemberDeleteStt(@RequestBody List<String> email) {
		boolean isDeleted = this.adminMemberService.updateMemberSignupSttToRefuse(email);
		return new ApiResponse(isDeleted);
	}
}
