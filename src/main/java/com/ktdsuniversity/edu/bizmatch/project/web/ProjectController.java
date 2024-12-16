package com.ktdsuniversity.edu.bizmatch.project.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectApplyFailException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectWriteFailException;
import com.ktdsuniversity.edu.bizmatch.common.utils.ParameterCheck;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.PrmStkVO;
import com.ktdsuniversity.edu.bizmatch.payment.service.PaymentService;
import com.ktdsuniversity.edu.bizmatch.project.service.ProjectService;
import com.ktdsuniversity.edu.bizmatch.project.vo.ApplyProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectApplyAttVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentPaginationVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectSkillVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.SearchApplyVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.SelectApplyMemberVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.WriteProjectVO;

@RestController
@RequestMapping("/api")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private PaymentService paymentService;

	/**
	 * 프로젝트 등록 페이지를 로드하는 컨트롤러.
	 * 
	 * @return
	 */
//	@GetMapping("/project/regist")
//	public String viewProjectRegistPage(@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO) {
//		// 만약 회원 유형이 프리랜서 유형이라면 접근 못하게 막아야한다.
//		if (memberVO.getMbrCtgry() == 1) {
//			// TODO: 기업 회원만 접근 가능하다고 알려줘야한다.
//			return "redirect:/";
//		}
//		CompanyVO companyVO = this.memberService.selectOneCompanyByEmilAddr(memberVO.getCmpId());
//		// TODO: 만약 회원이 계좌인증을 안했으면 접근 못하게 막아야한다.
//		if (companyVO.getCmpnyAccuuntNum() == null) {
//			// 또한, 계좌인증을 해달라고 클라이언트에게 메세지를 전달해야한다.
//			return "redirect:/";
//		}
//		return "project/project_register";
//	}

	/**
	 * 프로젝트 지원페이지를 로드하는 컨트롤러.
	 * 
	 * @param pjId
	 * @param model
	 * @param memberVO
	 * @return
	 */
//	@GetMapping("/project/apply/{pjId}")
//	public String viewApplyPage(@PathVariable String pjId, Model model,
//			@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO) {
//		if (memberVO == null) {
//			return "redirect:/";
//		}
//		return "project/project_apply";
//	}

	/**
	 * 프로젝트 상세보기 페이지.
	 * 
	 * @param pjId
	 * @param model
	 * @return
	 */
	@GetMapping("/project/info/{pjId}")
	public ApiResponse viewProjectInfoPage(@PathVariable String pjId,
			ProjectCommentPaginationVO projectCommentPaginationVO,
			Authentication loginMemberVO) {
		int listSize = this.projectService.getAllComment(pjId).size();
		projectCommentPaginationVO.setPageCount(listSize);
		projectCommentPaginationVO.setSearchIdParam(pjId);
		List<ProjectCommentVO> commentList = this.projectService.getPaginationComment(projectCommentPaginationVO, pjId);

		ProjectVO projectVO = this.projectService.readOneProjectInfo(pjId);
		System.out.println(projectVO);
		return new ApiResponse(projectVO);
	}

	/**
	 * 프로젝트 문의 페이지. temp
	 * 
	 * @return
	 */
//	@GetMapping("/project/inquiry")
//	public String viewProjectInquiryPage() {
//		return "project/project_inquiry_page";
//	}

	/**
	 * 프로젝트 찾기 페이지를 반환하는 컨트롤러이다.
	 * 
	 * @param memberVO
	 * @return
	 */
//	@GetMapping("/project/findpage")
//	public String viewProjectFindPage(@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO) {
//
//		if (memberVO == null) {
//			return "redirect:/";
//		}
//		return "project/project_find";
//	}

	/**
	 * 모든 프로젝트 리스트의 정보를 반환하는 컨트롤러.
	 */
	@GetMapping("/project/find")
	public ApiResponse getProjectList(Authentication loginMemberVO) {
		MemberVO memberVO = (MemberVO)loginMemberVO.getPrincipal();
		List<ProjectVO> projectList = this.projectService.readAllProjectList(memberVO);
		return new ApiResponse(projectList);
		
	}

	/**
	 * 프로젝트 등록 요청을 하는 컨트롤러.
	 * 
	 * @param writeProjectVO
	 * @param model
	 * @param loginMemberVO
	 * @return
	 * @throws ParseException
	 */
	@PostMapping("/project/write")
	public ApiResponse doCreateProject(WriteProjectVO writeProjectVO
			,@RequestParam("prmStkId") List<String> prmStkIdList,
			Authentication memberVO) throws ParseException {

		// 유효성 검사들.
		// 프로젝트 제목
		if (writeProjectVO.getPjTtl() == null) {
			throw new ProjectWriteFailException("프로젝트 제목은 필수 입력 사항입니다.", writeProjectVO);
		}
		// 프로젝트 일정
		if (writeProjectVO.getStrtDt() == null || writeProjectVO.getEndDt() == null) {
			throw new ProjectWriteFailException("프로젝트 일정은 필수 입력 사항입니다.", writeProjectVO);
		}
		// 지원 내용
		if (writeProjectVO.getPjDesc() == null) {
			throw new ProjectWriteFailException("프로젝트 상세 설명은 필수 입력 사항입니다.", writeProjectVO);
		}

		if (writeProjectVO.getCntrctAccnt() == null) {
			throw new ProjectWriteFailException("프로젝트 입찰가격은 필수 입력사항입니다.", writeProjectVO);
		}

		// 프로젝트 입찰가격
		if (writeProjectVO.getCntrctAccnt() < 0) {
			throw new ProjectWriteFailException("프로젝트 입찰가격은 1,000,000원 이상입니다.", writeProjectVO);
		}

		if (writeProjectVO.getCntrctAccnt() == 0) {
			throw new ProjectWriteFailException("프로젝트 입찰가격은 필수 입력사항입니다.", writeProjectVO);
		}

		// 프로젝트 모집일
		if (writeProjectVO.getPjRcrutStrtDt() == null || writeProjectVO.getPjRcrutEndDt() == null) {
			throw new ProjectWriteFailException("프로젝트 모집일은 필수 입력 사항입니다.", writeProjectVO);
		}

		// 날짜 문자열을 LocalDate로 변환
		LocalDate startDate = LocalDate.parse(writeProjectVO.getPjRcrutStrtDt());
		LocalDate endDate = LocalDate.parse(writeProjectVO.getPjRcrutEndDt());

		// 날짜 차이 계산
		long daysBetween = java.time.Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();

		// 모집 기간 최소 7일 체크
		if (daysBetween < 7) {
			throw new ProjectWriteFailException("프로젝트 모집기간은 최소 7일 이상이어야 합니다.", writeProjectVO);
		}

		// 종료일이 시작일보다 이전인 경우 체크
		if (daysBetween < 0) {
			throw new ProjectWriteFailException("종료일은 시작일 이후여야 합니다.", writeProjectVO);
		}

		// 프로젝트 인원
		if (writeProjectVO.getPjRcrutCnt() <= 0) {
			throw new ProjectWriteFailException("프로젝트 모집 인원은 필수 입력 사항입니다.", writeProjectVO);
		}
		MemberVO loginMemberVO = (MemberVO)memberVO.getPrincipal();
		writeProjectVO.setOrdrId(loginMemberVO.getEmilAddr());

		// 이제 수정된 호출
		List<String> skillList = new ArrayList<>(prmStkIdList);
		
		System.out.println("Received prmStkId List: " + skillList);

		boolean isSuccessed = this.projectService.createNewProject(writeProjectVO,skillList);

		return new ApiResponse(isSuccessed);
	}

	/**
	 * 프로젝트 지원을 요청하는 컨트롤러.
	 * 
	 * @param applyProjectVO
	 * @param pjId
	 * @param memberVO
	 * @return
	 */
	@PostMapping("/project/apply/{pjId}")
	public ApiResponse doApplyProject(ApplyProjectVO applyProjectVO, @PathVariable String pjId,
			Authentication memberVO) {
		
		MemberVO loginMemberVO = (MemberVO)memberVO.getPrincipal();
		applyProjectVO.setPjId(pjId);

		applyProjectVO.setEmilAddr(loginMemberVO.getEmilAddr());

		if (ParameterCheck.parameterCodeValid(applyProjectVO.getPjApplyDesc(), 0)) {
			throw new ProjectApplyFailException("pjApplyDesc를 입력해주세요", applyProjectVO);
		}

		boolean isSuccessed = this.projectService.createNewProjectApply(applyProjectVO);
		
		return new ApiResponse(isSuccessed);
	}

	/**
	 * 프로젝트 게시글의 조회수를 증가하는 요청을 받는 컨트롤러.
	 * 
	 * @param memberVO
	 * @param pjId
	 * @return
	 */
	@ResponseBody
	@PostMapping("/project/update/viewcount/{pjId}")
	public Map<String, Object> updateProjectViewCnt(@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO,
			@PathVariable String pjId) {
		Map<String, Object> resultMap = new HashMap<>();

		boolean isUpdate = this.projectService.updateProjectViewCnt(pjId);
		resultMap.put("response", isUpdate);
		return resultMap;
	}

//	/**
//	 * 특정 프로젝트 수정페이지를 로드하는 컨트롤러. -TODO-
//	 * 
//	 * @param memberVO
//	 * @param pjId
//	 * @return
//	 */
//	@GetMapping("/project/update/content/{pjId}")
//	public String loadUpdateProjectPage(@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO,
//			@PathVariable String pjId) {
//		return null;
//	}
//
//	/**
//	 * 프로젝트 수정 요청을 보내는 컨트롤러.
//	 * 
//	 * @param modifyProjectVO
//	 * @return
//	 */
//	@PostMapping("/project/update/content/{pjId}")
//	public String UpdateProjectInfo(ModifyProjectVO modifyProjectVO) {
//		boolean isUpdated = this.projectService.updateOneProject(modifyProjectVO);
//		return null;
//	}

	/**
	 * 프로젝트 추가모집 수정 페이지를 로드하는 컨트롤러.
	 * 
	 * @param memberVO
	 * @param projectVO
	 * @return
	 */
//	@GetMapping("/project/update/addrecurit/")
//	public String loadAddRecuritPage(@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO, ProjectVO projectVO) {
//
//		return null;
//	}
//
//	/**
//	 * 추가모집 정보를 서버에 업로드하는 컨트롤러.
//	 * 
//	 * @param modifyProjectVO
//	 * @return
//	 */
//	@PostMapping("/project/update/addrecurit/")
//	public String createRecuritInfo(ModifyProjectVO modifyProjectVO) {
//		boolean isUpdated = this.projectService.updateAddtionalRecruitment(modifyProjectVO);
//
//		return null;
//	}

	/**
	 * 추가모집 3일 추가
	 * @param memberVO
	 * @param pjId
	 * @return
	 */
	@PostMapping("/project/update/addrecruitment/{pjId}")
	public ApiResponse UpdateAddThreeDate(@PathVariable String pjId) {
		ProjectVO projectVO = this.projectService.readOneProjectInfo(pjId);
		try {
			// 1. 모집 종료일을 3일 연장하기
			String currentEndDate = projectVO.getPjRcrutEndDt(); // 기존 모집 종료일
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 형식에 맞게 포맷
			Date endDate = sdf.parse(currentEndDate); // String을 Date로 변환

			// 2. 3일 더하기
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endDate);
			calendar.add(Calendar.DAY_OF_YEAR, 3); // 3일 더하기
			Date newEndDate = calendar.getTime(); // 새로운 종료일

			// 3. 새로운 종료일을 다시 String으로 변환
			String newEndDateStr = sdf.format(newEndDate);

			// 4. 프로젝트 VO에 새로운 종료일 설정
			projectVO.setPjRcrutEndDt(newEndDateStr);

			// 5. 프로젝트 정보 업데이트 (서비스 계층 호출)
			boolean isSuccess = projectService.updateProject(projectVO);

			// 6. 업데이트 후 페이지 리다이렉션 (예: 프로젝트 상세 페이지로 이동)
			return new ApiResponse(isSuccess);

		} catch (ParseException e) {
			e.printStackTrace();
			return new ApiResponse(HttpStatus.BAD_REQUEST); // 날짜 포맷 오류 시 에러 페이지로 이동
		}
	}

//	/**
//	 * 프로젝트 삭제를 처리하는 컨트롤러. -TODO-
//	 * 
//	 * @param memberVO
//	 * @param pjId
//	 * @return
//	 */
//	@PostMapping("/project/delete/")
//	public String deleteProject(@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO, String pjId) {
//		boolean isDeleted = this.projectService.deleteOneProject(null);
//
//		return null;
//	}

	/**
	 * 지원자의 지원서 불러오는 컨트롤러
	 * 
	 * @return
	 */
	@GetMapping("/project/apply/member/view/{pjId}/{pjApplyId}")
	public String viewMemberApplyView(@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO,
			Model model, @PathVariable String pjApplyId, @PathVariable String pjId) {

		if (memberVO == null) {
			return "redirect:/login";
		}

		SearchApplyVO searchApplyVO = new SearchApplyVO();
		searchApplyVO.setPjId(pjId);
		searchApplyVO.setPjApplyId(pjApplyId);

		ApplyProjectVO applyProjectVO = projectService.selectOneApplyViewInfo(searchApplyVO);

		List<ProjectApplyAttVO> projectApplyAttVOList = applyProjectVO.getProjectApplyAttVOList();

		if (projectApplyAttVOList == null) {
			projectApplyAttVOList = new ArrayList<>();
		}

		// 모델에 데이터 추가
		model.addAttribute("applyProjectVO", applyProjectVO);
		model.addAttribute("projectApplyAttVOList", projectApplyAttVOList);

		return "project/memberApplyView";
	}

	/**
	 * 지원서 수정을 요청하는 컨트롤러.
	 * 
	 * @param applyProjectVO
	 * @return
	 */
	@PostMapping("/project/apply/edit")
	public ApiResponse updateApplyContent(ApplyProjectVO applyProjectVO) {
		boolean isUpdated = this.projectService.updateProjectApply(applyProjectVO);
		return new ApiResponse(isUpdated);
	}

	/**
	 * 지원자 리스트 사이즈 받아와서 지원자 없으면 결제를 막는다.
	 */
	@GetMapping("/project/apply/member/check/{pjId}")
	public ApiResponse doCheckApplyMemberCnt(@PathVariable String pjId
											, Authentication memberVO) {

		List<ApplyProjectVO> applyList = this.projectService.readAllApplyMember(pjId, memberVO.getName());
		return new ApiResponse(applyList);
	}

	/**
	 * 프로젝트 지원을 삭제 요청을 하는 컨트롤러.
	 * 
	 * @param memberVO
	 * @param applyProjectVO
	 * @return
	 */
	@PostMapping("/project/apply/delete/{pjApplyId}")
	public ApiResponse deleteApplyContent(Authentication memberVO
									, ApplyProjectVO applyProjectVO
									, @PathVariable String pjApplyId) {

		applyProjectVO.setPjApplyId(pjApplyId);
		this.projectService.deleteProjectApply(applyProjectVO);

		return new ApiResponse(true);
	}

	/**
	 * 회사에 지원한 지원 기업리스트
	 * 
	 * @param memberVO
	 * @param pjId
	 * @param model
	 * @return
	 */
	@GetMapping("/project/apply/member/{pjId}")
	public ApiResponse viewApplyMemberPage(Authentication memberVO,
									@PathVariable String pjId) {
		ProjectVO projectVO = this.projectService.readOneProjectInfo(pjId);
		
		// 돈을 안냈으면.
		if (projectVO.getPaymentVO().getGrntPdDt() == null) {
			return new ApiResponse(false);
		}

		List<ApplyProjectVO> applyProjectVOList = this.projectService.readAllApplyMember(pjId, memberVO.getName());

		// 보증금을 납부했는지 먼저 검사해야한다.
		boolean isPaid = this.paymentService.readIsPaymentDeposit(pjId);

		if (!isPaid) {
			return new ApiResponse(false);
		}
		return new ApiResponse(applyProjectVOList);
	}

	/**
	 * 프로젝트에 첨부할 수 있는 전체 스킬들의 목록을 불러오는 컨트롤러이다.
	 * 
	 * @return
	 */
	@GetMapping("/project/skill") // api/project/skill
	public ApiResponse showSkils() {
		List<PrmStkVO> skillist = this.projectService.selectAllProjectSkillList();
		
		return new ApiResponse(skillist);
	}

	/**
	 * 검색할 때 스킬 목록 불러오기.
	 * @param pjId
	 * @return
	 */
	@GetMapping("/project/skill/{pjId}")
	public ApiResponse getAllSkills(@PathVariable String pjId) {
		List<ProjectSkillVO> skills = this.projectService.readAllProjectSkill(pjId);
		
		return new ApiResponse(skills);
	}

	/**
	 * 지원기업 선택하기
	 * 
	 * @param pjId
	 * @param memberVO
	 * @param selectApplyMemberVO
	 * @return
	 */
	@PostMapping("/project/apply/member/{pjId}")
	public ApiResponse doChoiceApplyMember(@PathVariable String pjId
									, Authentication memberVO
									, SelectApplyMemberVO selectApplyMemberVO) {
		boolean isUpdated = this.projectService.updateApplyMember(selectApplyMemberVO, memberVO.getName());
		
		return new ApiResponse(isUpdated);
	}

	/**
	 * 기업회원 내 프로젝트 조회페이지를 로드하는 컨트롤러.
	 * 
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/project/myproject")
	public ApiResponse viewAllProjectOrder(Authentication memberVO) {
		
		return new ApiResponse();
	}

//	/**
//	 * 한명의 회원이 수주했던 즉 수행했던 모든 프로젝트 조회하는 페이지를 로드하는 컨트롤러 -의진-
//	 * 
//	 * @param memberVO
//	 * @return
//	 */
//	@GetMapping("/project/all/order/recipient")
//	public String viewAllProject(Authentication memberVO) {
//		
//	}

	/**
	 * 
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/project/all/order/applicant")
	public List<ApplyProjectVO> myProjectApplications(@RequestParam String email) {
		List<ApplyProjectVO> applyProjectList = this.projectService.readAllApply(email);

		return applyProjectList;
	}

	/**
	 * 내가 지원한 지원서 불러오는 컨트롤러
	 */
	@GetMapping("/project/apply/list")
	public ApiResponse viewApplyList(@RequestParam String email) {
		List<ApplyProjectVO> applyProjectVOList = this.projectService.readAllApply(email);
		
		return new ApiResponse(applyProjectVOList);
	}

//	/*
//	 * 프로젝트 스크랩을 요청하는 컨트롤러.
//	 * 
//	 * @param pjId
//	 * 
//	 * @param memberVO
//	 * 
//	 * @return
//	 */
//	@PostMapping("/project/scrap/{pjId}")
//	public ApiResponse doScrapProject(@PathVariable String pjId, Authentication memberVO) {
//		ProjectScrapVO projectScrapVO = new ProjectScrapVO();
//
//		projectScrapVO.setEmilAddr(memberVO.getName());
//		projectScrapVO.setPjId(pjId);
//
//		this.projectService.insertProjectScrap(projectScrapVO);
//
//		return "redirect:/project/info/{pjId}";
//	}
//	
	/**
	 * 
	 * @param projectCommentWriteVO
	 * @param memberVO
	 * @return
	 */
	@PostMapping("/project/info/write")
	public ApiResponse writeComment(ProjectCommentWriteVO projectCommentWriteVO,
									Authentication memberVO) {
		projectCommentWriteVO.setAthrId(memberVO.getName());
		boolean result = this.projectService.createNewComment(projectCommentWriteVO);
		
		return new ApiResponse(result);
	}

	/**
	 * 기업이 발주한 프로젝트의 목록을 가져오는 컨트롤러.
	 * 
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/project/myproject/orderproject")
	public ApiResponse getMyOrderProjectList(@RequestParam String email) {
		List<ProjectVO> projectList = this.projectService.readAllMyOrderProjectList(email);
		
		return new ApiResponse(projectList);
	}
	
	/**
	 * 지원서 하나 가져오는 컨트롤러
	 * @param pjApplyId 지원서 아이디
	 */
	@GetMapping("/project/apply/script")
	public ApiResponse getApplyScripty(@RequestParam String pjApplyId) {
		ApplyProjectVO applyProjectVO = this.projectService.selectOneApplyInfo(pjApplyId);
		return new ApiResponse(applyProjectVO);
	}
	
	@PostMapping("/project/apply/att/delete")
	public ApiResponse deleteApplyAtt(@RequestParam String pjApplyAttId) {
		boolean isSuccess = this.projectService.deleteApplyAtt(pjApplyAttId);
		return new ApiResponse(isSuccess);
	}
}
