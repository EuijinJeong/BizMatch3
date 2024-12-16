package com.ktdsuniversity.edu.bizmatch.admin.project.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.bizmatch.admin.project.service.AdminProjectService;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;


@RestController
@RequestMapping("/api/admin")
public class AdminProjectController {
	
	@Autowired
	public AdminProjectService adminProjectService;
	
	@GetMapping("/read/allproject")
	public ApiResponse readAllProjectList() {
		List<ProjectVO> answer = this.adminProjectService.readAllProject();
		return new ApiResponse(answer);
	}
	
	@GetMapping("/read/oneproject/{pjId}")
	public ApiResponse readOneProject(@PathVariable String pjId) {
		ProjectVO projectVO = this.adminProjectService.readOneProject(pjId);
		return new ApiResponse(projectVO);
	}
	
	@PostMapping("/delete/project")
	public ApiResponse doDeleteOneProject(@RequestBody List<String> pjIdList) {
		
		boolean isSuccess = this.adminProjectService.deleteProject(pjIdList);
		
		return new ApiResponse(isSuccess);
		
	}
	

}
