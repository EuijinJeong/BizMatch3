package com.ktdsuniversity.edu.bizmatch.admin.member.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.admin.member.service.AdminMemberService;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

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
	 * 회원을 삭제하는 컨트롤러.
	 * @param email
	 * @return
	 */
	@PostMapping("/delete/memberstt")
	public ApiResponse postMemberDeleteStt(@RequestBody List<String> email) {
		boolean isDeleted = this.adminMemberService.updateMemberSignupSttToRefuse(email);
		return new ApiResponse(isDeleted);
	}
	
	@GetMapping("/memberlist")
	public ApiResponse getMemberList() {
		List<MemberVO> memberList = this.adminMemberService.readAllMemberList();
		return new ApiResponse(memberList);
	}
	
	@PostMapping("/update/member/penalty")
	public ApiResponse updateMemberPenalty(@RequestBody List<String> email) {
		boolean isSuccess = this.adminMemberService.updateMemberPnlty(email);
		
		return new ApiResponse(isSuccess);
	}
	
	@PostMapping("/update/member/isqt")
	public ApiResponse updateMemberIsqt(@RequestBody List<String> email) {
		boolean isSuccess = this.adminMemberService.updateMemberIsqt(email);
		
		return new ApiResponse(isSuccess);
	}
}
