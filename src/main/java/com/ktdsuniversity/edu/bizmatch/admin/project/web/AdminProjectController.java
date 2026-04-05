package com.ktdsuniversity.edu.bizmatch.admin.project.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.admin.project.service.AdminProjectService;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;


@RestController
@RequestMapping("/api/admin")
public class AdminProjectController {
	
	@Autowired
	public AdminProjectService adminProjectService;
	
	@GetMapping("/read/allproject")
	public ApiResponse readAllProjectList(Authentication authentication) {
		MemberVO loginMemberVO = (MemberVO)authentication.getPrincipal();
		int mbrCtgry = loginMemberVO.getMbrCtgry();
		
		if(mbrCtgry != 2) {
			return new ApiResponse(HttpStatus.FORBIDDEN, "관리자만 접속이 가능합니다.");
		}
		
		List<ProjectVO> answer = this.adminProjectService.readAllProject();
		return new ApiResponse(answer);
	}
	
	@GetMapping("/read/oneproject/{pjId}")
	public ApiResponse readOneProject(Authentication authentication, @PathVariable String pjId) {
		MemberVO loginMemberVO = (MemberVO)authentication.getPrincipal();
		int mbrCtgry = loginMemberVO.getMbrCtgry();
		
		if(mbrCtgry != 2) {
			return new ApiResponse(HttpStatus.FORBIDDEN, "관리자만 접속이 가능합니다.");
		}
		
		ProjectVO projectVO = this.adminProjectService.readOneProject(pjId);
		return new ApiResponse(projectVO);
	}
	
	@PostMapping("/delete/project")
	public ApiResponse doDeleteOneProject(Authentication authentication, @RequestBody List<String> pjIdList) {
		MemberVO loginMemberVO = (MemberVO)authentication.getPrincipal();
		int mbrCtgry = loginMemberVO.getMbrCtgry();
		
		if(mbrCtgry != 2) {
			return new ApiResponse(HttpStatus.FORBIDDEN, "관리자만 접속이 가능합니다.");
		}
		
		boolean isSuccess = this.adminProjectService.deleteProject(pjIdList);
		
		return new ApiResponse(isSuccess);
		
	}
	

}
