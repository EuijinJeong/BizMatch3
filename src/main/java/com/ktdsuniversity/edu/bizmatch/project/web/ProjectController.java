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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectApplyFailException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectWriteFailException;
import com.ktdsuniversity.edu.bizmatch.common.utils.ParameterCheck;
import com.ktdsuniversity.edu.bizmatch.member.service.MemberService;
import com.ktdsuniversity.edu.bizmatch.member.vo.CompanyVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.PrmStkVO;
import com.ktdsuniversity.edu.bizmatch.payment.service.PaymentService;
import com.ktdsuniversity.edu.bizmatch.project.service.ProjectService;
import com.ktdsuniversity.edu.bizmatch.project.vo.ApplyProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ModifyProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectApplyAttVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentPaginationVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectScrapVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectSkillVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.SearchApplyVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.SelectApplyMemberVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.WriteProjectVO;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private MemberService memberService;
	/**
	 * 프로젝트 등록 페이지를 로드하는 컨트롤러.
	 * 
	 * @return
	 */
	@GetMapping("/project/regist")
	public String viewProjectRegistPage(@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO) {
		// 만약 회원 유형이 프리랜서 유형이라면 접근 못하게 막아야한다.
		if (memberVO.getMbrCtgry() == 1) {
			// TODO: 기업 회원만 접근 가능하다고 알려줘야한다.
			return "redirect:/";
		}
		CompanyVO companyVO = this.memberService.selectOneCompanyByEmilAddr(memberVO.getCmpId());
		// TODO: 만약 회원이 계좌인증을 안했으면 접근 못하게 막아야한다.
		if (companyVO.getCmpnyAccuuntNum() == null) {
			// 또한, 계좌인증을 해달라고 클라이언트에게 메세지를 전달해야한다.
			return "redirect:/";
		}
		return "project/project_register";
	}

	/**
	 * 프로젝트 지원페이지를 로드하는 컨트롤러.
	 * 
	 * @param pjId
	 * @param model
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/project/apply/{pjId}")
	public String viewApplyPage(@PathVariable String pjId, Model model,
			@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO) {
		if (memberVO == null) {
			return "redirect:/";
		}
		return "project/project_apply";
	}

	/**
	 * 프로젝트 상세보기 페이지.
	 * 
	 * @param pjId
	 * @param model
	 * @return
	 */
	@GetMapping("/project/info/{pjId}")
	public String viewProjectInfoPage(@PathVariable String pjId, Model model,
			ProjectCommentPaginationVO projectCommentPaginationVO,
			@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO loginMemberVO) {
		int listSize = this.projectService.getAllComment(pjId).size();
		projectCommentPaginationVO.setPageCount(listSize);
		projectCommentPaginationVO.setSearchIdParam(pjId);
		List<ProjectCommentVO> commentList = this.projectService.getPaginationComment(projectCommentPaginationVO, pjId);

		ProjectVO projectVO = this.projectService.readOneProjectInfo(pjId);
		model.addAttribute("projectId", pjId);
		model.addAttribute("projectVO", projectVO);
		return "project/project_info";
	}

	/**
	 * 프로젝트 문의 페이지.
	 * 
	 * @return
	 */
	@GetMapping("/project/inquiry")
	public String viewProjectInquiryPage() {
		return "project/project_inquiry_page";
	}

	/**
	 * 프로젝트 찾기 페이지를 반환하는 컨트롤러이다.
	 * 
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/project/findpage")
	public String viewProjectFindPage(@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO) {

		if (memberVO == null) {
			return "redirect:/";
		}
		return "project/project_find";
	}

	/**
	 * 프로젝트 리스트의 정보를 반환하는 컨트롤러.
	 */
	@GetMapping("/project/find")
	@ResponseBody
	public List<Map<String, Object>> getProjectList(
			@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO) {

		List<Map<String, Object>> resultList = new ArrayList<>();
		List<ProjectVO> projectList = this.projectService.readAllProjectList(memberVO);

		System.out.println("프로젝트 길이:" + projectList.size());

		for (ProjectVO projectVO : projectList) {
			Map<String, Object> tempMap = new HashMap<>();
			tempMap.put("pjTtl", projectVO.getPjTtl());
			tempMap.put("pjStt", projectVO.getPjStt());
			tempMap.put("rgstrDt", projectVO.getRgstrDt());
			tempMap.put("smjrNm", projectVO.getProjectIndustryVO().getIndstrInfoVO().getIndstrNm());
			tempMap.put("pjRcrutEndDt", projectVO.getPjRcrutEndDt());
			tempMap.put("strtDt", projectVO.getStrtDt());
			tempMap.put("endDt", projectVO.getEndDt());
			tempMap.put("pjId", projectVO.getPjId());
			tempMap.put("cntrctAccnt", projectVO.getCntrctAccnt());
			tempMap.put("projectSkillList", projectVO.getProjectSkillList());
			resultList.add(tempMap);
		}
		return resultList;
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
	public String doCreateProject(WriteProjectVO writeProjectVO, Model model,
			@RequestParam("prmStkId") List<String> prmStkIdList,
			@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO loginMemberVO) throws ParseException {

		if (loginMemberVO == null) {
			return "redirect:/";
		}

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
		if (writeProjectVO.getCntrctAccnt() < 1000000) {
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

		writeProjectVO.setOrdrId(loginMemberVO.getEmilAddr());

		// 이제 수정된 호출
		List<String> skillList = new ArrayList<>(prmStkIdList);

		boolean isSuccessed = this.projectService.createNewProject(writeProjectVO, skillList);
		// 프로젝트 등록에 성공한 경우.
		if (isSuccessed) {
			return "redirect:/project/findpage";
		}
		// 프로젝트 등록에 실패한 경우.
		else {
			return "redirect:/project/regist";
		}
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
	public String doApplyProject(ApplyProjectVO applyProjectVO, @PathVariable String pjId,
			@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO) {

		applyProjectVO.setPjId(pjId);

		if (memberVO == null) {
			return "redirect:/";
		}

		applyProjectVO.setEmilAddr(memberVO.getEmilAddr());

		if (ParameterCheck.parameterCodeValid(applyProjectVO.getPjApplyDesc(), 0)) {
			throw new ProjectApplyFailException("pjApplyDesc를 입력해주세요", applyProjectVO);
		}

		boolean isSuccessed = this.projectService.createNewProjectApply(applyProjectVO);
		// 프로젝트 지원에 성공한 경우.
		if (isSuccessed) {
			return "redirect:/project/findpage";
		}
		// 프로젝트 지원에 실패한 경우.
		else {
			return "redirect:/project/apply" + pjId;
		}
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

	/**
	 * 특정 프로젝트 수정페이지를 로드하는 컨트롤러.
	 * 
	 * @param memberVO
	 * @param pjId
	 * @return
	 */
	@GetMapping("/project/update/content/{pjId}")
	public String loadUpdateProjectPage(@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO,
			@PathVariable String pjId) {
		return null;
	}

	/**
	 * 프로젝트 수정 요청을 보내는 컨트롤러.
	 * 
	 * @param modifyProjectVO
	 * @return
	 */
	@PostMapping("/project/update/content/{pjId}")
	public String UpdateProjectInfo(ModifyProjectVO modifyProjectVO) {
		boolean isUpdated = this.projectService.updateOneProject(modifyProjectVO);
		return null;
	}

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
	public String UpdateAddThreeDate(@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO, @PathVariable String pjId) {
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
			projectService.updateProject(projectVO);

			// 6. 업데이트 후 페이지 리다이렉션 (예: 프로젝트 상세 페이지로 이동)
			return "redirect:/project/myproject";

		} catch (ParseException e) {
			e.printStackTrace();
			return "error"; // 날짜 포맷 오류 시 에러 페이지로 이동
		}
	}

	/**
	 * 프로젝트 삭제를 처리하는 컨트롤러.
	 * 
	 * @param memberVO
	 * @param pjId
	 * @return
	 */
	@PostMapping("/project/delete/")
	public String deleteProject(@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO, String pjId) {
		boolean isDeleted = this.projectService.deleteOneProject(null);

		return null;
	}

	/**
	 * 지원서 수정페이지를 로드하는 컨트롤러.
	 * 
	 * @param memberVO
	 * @param applyProjectVO
	 * @return
	 */

	@GetMapping("/project/apply/edit/{pjId}")
	public String loadUpdateApplyContentPage(@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO,
			@PathVariable String pjId, Model model) {

		SearchApplyVO searchApplyVO = new SearchApplyVO();
		searchApplyVO.setEmilAddr(memberVO.getEmilAddr());
		searchApplyVO.setPjId(pjId);

		ApplyProjectVO applyProjectVO = projectService.selectOneApplyProject(searchApplyVO);
		model.addAttribute("applyProjectVO", applyProjectVO);

		return "project/myApplyView";
	}

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
	public String updateApplyContent(ApplyProjectVO applyProjectVO) {
		boolean isUpdated = this.projectService.updateProjectApply(applyProjectVO);
		return null;
	}

	/**
	 * 지원자 리스트 사이즈 받아와서 지원자 없으면 결제를 막는다.
	 */
	@GetMapping("/project/apply/member/check/{pjId}")
	@ResponseBody
	public Map doCheckApplyMemberCnt(@PathVariable String pjId,
			@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO) {

		List<ApplyProjectVO> applyList = this.projectService.readAllApplyMember(pjId, memberVO);

		return Map.of("result", applyList.size());
	}

	/**
	 * 프로젝트 지원을 삭제 요청을 하는 컨트롤러.
	 * 
	 * @param memberVO
	 * @param applyProjectVO
	 * @return
	 */
	@PostMapping("/project/apply/delete/{pjApplyId}")
	public String deleteApplyContent(@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO,
			ApplyProjectVO applyProjectVO, @PathVariable String pjApplyId) {

		applyProjectVO.setPjApplyId(pjApplyId);
		this.projectService.deleteProjectApply(applyProjectVO);

		return "redirect:/project/apply/member";
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
	public String viewApplyMemberPage(@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO,
			@PathVariable String pjId, Model model) {
		ProjectVO projectVO = this.projectService.readOneProjectInfo(pjId);

		// 돈을 안냈으면.
		if (projectVO.getPaymentVO().getGrntPdDt() == null) {
			return "redirect:/bizmatch/payment/ask/deposit/" + pjId;
		}

		List<ApplyProjectVO> applyProjectVOList = this.projectService.readAllApplyMember(pjId, memberVO);
		model.addAttribute("projectVO", projectVO);
		model.addAttribute("applyProjectVOList", applyProjectVOList);

		// 보증금을 납부했는지 먼저 검사해야한다.
		boolean isPaid = this.paymentService.readIsPaymentDeposit(pjId);

		// 납부했으면 지원기업 리스트 페이지를 보여줘야함.
		if (isPaid) {
			return "project/projectapplylist";
		}
		// 납부 안했으면 결제 페이지로 리다이랙트.
		return "redirect:/bizmatch/payment/ask/deposit/" + pjId;
	}

	/**
	 * 프로젝트에 첨부할 수 있는 전체 스킬들의 목록을 불러오는 컨트롤러이다.
	 * 
	 * @return
	 */
	@GetMapping("/project/skill")
	public ResponseEntity<List<PrmStkVO>> showSkils() {
		List<PrmStkVO> skillist = this.projectService.selectAllProjectSkillList();
		return new ResponseEntity<>(skillist, HttpStatus.OK);
	}

	/**
	 * 검색할때 스킬 목록 불러오기
	 */
	@GetMapping("/project/skill/{pjId}")
	public ResponseEntity<List<ProjectSkillVO>> getAllSkills(@PathVariable String pjId) {
		List<ProjectSkillVO> skills = this.projectService.readAllProjectSkill(pjId);
		return new ResponseEntity<>(skills, HttpStatus.OK);
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
	public String doChoiceApplyMember(@PathVariable String pjId,
			@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO,
			SelectApplyMemberVO selectApplyMemberVO) {
		System.out.println("선택된 사람이메일" + selectApplyMemberVO.getEmilAddr());
		this.projectService.updateApplyMember(selectApplyMemberVO, memberVO);
//		return "redirect:/bizmatch/payment/ask/deposit/" + pjId;
		return "redirect:/";
	}

	/**
	 * 기업회원 내 프로젝트 조회페이지를 로드하는 컨트롤러.
	 * 
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/project/myproject")
	public String viewAllProjectOrder(@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO) {
		if (memberVO == null) {
			return "redirect:/";
		}
		return "project/myproject";
	}

	/**
	 * 한명의 회원이 수주했던 즉 수행했던 모든 프로젝트 조회하는 페이지를 로드하는 컨트롤러 -의진-
	 * 
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/project/all/order/recipient")
	public String viewAllProject(@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO) {
		return "project/myproject";
	}

	/**
	 * 
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/project/all/order/applicant")
	@ResponseBody
	public List<ApplyProjectVO> myProjectApplications(
			@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO) {

		List<ApplyProjectVO> applyProjectList = this.projectService.readAllApply(memberVO);
		System.out.println("지원서 리스트 크기:"
				+ applyProjectList.get(0).getProjectVO().getProjectSkillList().get(0).getPrmStkVO().getPrmStk());
		return applyProjectList;
	}

	/**
	 * 내가 지원한 지원서 불러오는 컨트롤러
	 */
	@GetMapping("/project/apply/list")
	public String viewApplyList(@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO,
			Model model) {

		List<ApplyProjectVO> applyProjectVOList = this.projectService.readAllApply(memberVO);
		model.addAttribute("applyProjectVOList", applyProjectVOList);

		return "project/myApplyView";
	}

	/*
	 * 프로젝트 스크랩을 요청하는 컨트롤러.
	 * 
	 * @param pjId
	 * 
	 * @param memberVO
	 * 
	 * @return
	 */
	@PostMapping("/project/scrap/{pjId}")
	public String doScrapProject(@PathVariable String pjId,
			@SessionAttribute(value = "_LOGIN_USER_") MemberVO memberVO) {
		ProjectScrapVO projectScrapVO = new ProjectScrapVO();

		projectScrapVO.setEmilAddr(memberVO.getEmilAddr());
		projectScrapVO.setPjId(pjId);

		this.projectService.insertProjectScrap(projectScrapVO);

		return "redirect:/project/info/{pjId}";
	}
	
	/**
	 * 
	 * @param projectCommentWriteVO
	 * @param memberVO
	 * @return
	 */
	@PostMapping("/project/info/write")
	@ResponseBody
	public Map<String, Object> writeComment(ProjectCommentWriteVO projectCommentWriteVO,
			@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO) {
		projectCommentWriteVO.setAthrId(memberVO.getEmilAddr());
		boolean result = this.projectService.createNewComment(projectCommentWriteVO);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}

	/**
	 * 기업이 발주한 프로젝트의 목록을 가져오는 컨트롤러.
	 * 
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/project/myproject/orderproject")
	@ResponseBody
	public List<Map<String, Object>> getMyOrderProjectList(
			@SessionAttribute(value = "_LOGIN_USER_", required = false) MemberVO memberVO) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		// FIXME: memberVO null.
		List<ProjectVO> projectList = this.projectService.readAllMyOrderProjectList(memberVO.getCmpId());

		for (ProjectVO projectVO : projectList) {
			Map<String, Object> tempMap = new HashMap<>();
			tempMap.put("pjTtl", projectVO.getPjTtl());
			tempMap.put("pjStt", projectVO.getPjStt());
			tempMap.put("rgstrDt", projectVO.getRgstrDt());
			tempMap.put("smjrNm", projectVO.getProjectIndustryVO().getIndstrInfoVO().getIndstrNm());
			tempMap.put("pjRcrutEndDt", projectVO.getPjRcrutEndDt());
			tempMap.put("strtDt", projectVO.getStrtDt());
			tempMap.put("endDt", projectVO.getEndDt());
			tempMap.put("pjId", projectVO.getPjId());
			tempMap.put("cntrctAccnt", projectVO.getCntrctAccnt());
			tempMap.put("projectSkillList", projectVO.getProjectSkillList());
			resultList.add(tempMap);
		}
		return resultList;
	}
}
