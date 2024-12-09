package com.ktdsuniversity.edu.bizmatch.member.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.RestTemplate;

import com.ktdsuniversity.edu.bizmatch.common.category.vo.CategoryVO;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.ResetPassword;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.SessionNotFoundException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.SignUpCompanyException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.SignUpFailException;
import com.ktdsuniversity.edu.bizmatch.common.skills.vo.MbrPrmStkVO;
import com.ktdsuniversity.edu.bizmatch.common.utils.ParameterCheck;
import com.ktdsuniversity.edu.bizmatch.common.vo.ApiResponse;
import com.ktdsuniversity.edu.bizmatch.member.service.MemberService;
import com.ktdsuniversity.edu.bizmatch.member.vo.CompanyVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberCompanyModifyVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberCompanySignUpVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberFreelancerModifyVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberModifyVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberMyPageIndsryVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberPaginationVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberPortfolioVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberResetPwdVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberSignUpVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;

/**
 * 이 컨트롤러는 회원과 관련한 api 요청을 다루는 컨트롤러입니다.
 * 
 * @author jeong-uijin
 */
@RestController
@RequestMapping("/api")
public class MemberController {
	
	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	private final RestTemplate restTemplate;
	
	public MemberController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	/**
	 * 비밀번호 재설정 요청을 처리하는 컨트롤러.
	 * @param email
	 * @return
	 */
	@PostMapping("/member/findpwd")
	public ApiResponse sendMemberFindPwd(@RequestParam String email) {
		boolean isSuccess = this.memberService.sendFindPwdEmail(email);
		return new ApiResponse(isSuccess);
	}
	
	/**
	 * 비밀번호 재설정 요청을 처리하는 컨트롤러.
	 */
	@PostMapping("/member/resetpwd")
	public ApiResponse requestResetPwd(MemberResetPwdVO memberResetPwdVO) {
		
		if(ParameterCheck.parameterCodeValid(memberResetPwdVO.getPwd(), 0)) {
			throw new ResetPassword("비밀번호는 필수 입력사항입니다.");
		}
		if(ParameterCheck.parameterCodeValid(memberResetPwdVO.getConfirmNewPwd(), 0)) {
			throw new ResetPassword("비밀번호 확인은 필수 입력사항입니다.");
		}
		
		boolean isSuccess = this.memberService.resetMemberPwd(memberResetPwdVO);
		
		return new ApiResponse(isSuccess);
	}
	
	/**
	 * 기업형 회원가입을 처리하는 컨트롤러이다.
	 * 
	 * @param memberCompanySignUpVO
	 * @return
	 */
	@PostMapping(value = "/member/signup/company", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse signUpCompanyMember(@ModelAttribute MemberCompanySignUpVO memberCompanySignUpVO) {
		
//		 사용자가 입력한 값 유효성 검사.
		if(ParameterCheck.parameterCodeValid(memberCompanySignUpVO.getMbrNm(), 0)) {
			throw new SignUpCompanyException("이용자명은 필수 입력사항입니다.", memberCompanySignUpVO);
		}
		
		if(ParameterCheck.parameterCodeValid(memberCompanySignUpVO.getEmilAddr(), 7)) {
			throw new SignUpCompanyException("이메일은 필수 입력사항입니다.", memberCompanySignUpVO);
		}
		if(ParameterCheck.parameterCodeValid(memberCompanySignUpVO.getEmilAddrCnfrmNmbr(), 0)) {
			throw new SignUpCompanyException("인증번호를 입력해주세요", memberCompanySignUpVO);
		}
		
		if(ParameterCheck.parameterCodeValid(memberCompanySignUpVO.getCmpnyBrn() , 10)) {
			throw new SignUpCompanyException("사업자 번호는 필수 입력사항입니다.", memberCompanySignUpVO);
		}
		 
		if(memberCompanySignUpVO.getCmpnyEmplyCnt() == null || memberCompanySignUpVO.getCmpnyEmplyCnt() <= 0) {
			throw new SignUpCompanyException("직원 수는 필수 입력사항입니다.", memberCompanySignUpVO);
		}
		
		if(ParameterCheck.parameterCodeValid(memberCompanySignUpVO.getMbrPhnNum(), 10)) {
			throw new SignUpCompanyException("전화번호는 필수 입력사항입니다.", memberCompanySignUpVO);
		}
		
		if(ParameterCheck.parameterCodeValid(memberCompanySignUpVO.getCmpnyNm(), 0)) {
			throw new SignUpCompanyException("기업명은 필수 입력사항입니다.", memberCompanySignUpVO);
		}
		
		if(ParameterCheck.parameterCodeValid(memberCompanySignUpVO.getCmpnyPhnNum(), 0)) {
			throw new SignUpCompanyException("기업 전화번호는 필수 입력사항입니다.", memberCompanySignUpVO);
		}
		
		if(ParameterCheck.parameterCodeValid(memberCompanySignUpVO.getCmpnyIndstrId().getMjrId(),0)) {
			throw new SignUpCompanyException("기업 주요 산업분야는 필수 입력사항입니다.", memberCompanySignUpVO);
		}
		
		if(memberCompanySignUpVO.getAgreeOne()==null|| memberCompanySignUpVO.getAgreeThree()==null || memberCompanySignUpVO.getAgreeTwo()==null) {
			throw new SignUpCompanyException("동의 사항에 모두 동의해야합니다.", memberCompanySignUpVO);
		}
		
		boolean isSuccessed = this.memberService.signupCompanyMember(memberCompanySignUpVO);
		return new ApiResponse(isSuccessed);
	}
	
	/**
	 * 이미 회원 목록에 존재하는 기업 회원인지 조회하는 컨트롤러.
	 * @param cmpnyBrn
	 * @return
	 */
	@GetMapping("/member/signup/cmpnycheck")
	public ApiResponse companyCheck(@RequestParam String cmpnyBrn) {
		CompanyVO companyVO = this.memberService.readOneCompany(cmpnyBrn);
		return new ApiResponse(companyVO);
	}

	/**
	 * 사업자번호 외부 api 요청하는 컨트롤러.
	 * @param cmpnyBrn
	 * @return
	 */
	@GetMapping("/bizno/api/ask")
	public Map handleBiznoApi(@RequestParam String cmpnyBrn) {
		Map<String, Object> request = new HashMap<>();
		// TODO 사업자 번호 - 이거 뜯을 수 있나?
		request.put("key", "amVqMDAxMjI4QGdtYWlsLmNvbSAg");
		request.put("gb", "1");
		request.put("q", cmpnyBrn);
		request.put("type", "json");
		
		String queryParam = "?";
		
		queryParam += request.entrySet()
				.stream()
				.map(entry -> entry.getKey() + "=" + entry.getValue().toString())
				.collect(Collectors.joining("&"));
		
		ResponseEntity<Map> response = this.restTemplate.getForEntity("https://bizno.net/api/fapi" + queryParam, Map.class);
		
		logger.debug(response.getBody().toString());
		
		return response.getBody();
	}
	
	/**
	 * 프리랜서형 회원가입을 처리하는 컨트롤러.
	 * @param memberSignUpVO
	 * @param categoryVO
	 * @return
	 */
	@PostMapping(value = "/member/signup/freelancer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse signUpFreelancer(MemberSignUpVO memberSignUpVO , CategoryVO categoryVO) {
		if(ParameterCheck.parameterCodeValid(memberSignUpVO.getMbrNm(), 0)) {
			throw new SignUpFailException("이용자명은 필수 입력사항입니다.", memberSignUpVO);
		}
		
		if (ParameterCheck.parameterCodeValid(memberSignUpVO.getBrthDt(), 0)) {
			throw new SignUpFailException("생년월일은 필수 입력사항입니다.", memberSignUpVO);
		}
		if(memberSignUpVO.getAddr() == null) {
			throw new SignUpFailException("주소는 필수 입력사항입니다.", memberSignUpVO);
		}
		if(ParameterCheck.parameterCodeValid(memberSignUpVO.getEmilAddr(), 0)) {
			throw new SignUpFailException("이메일은 필수 입력사항입니다.", memberSignUpVO);
		}
		
		if(memberSignUpVO.getAgree1()==null|| memberSignUpVO.getAgree2()==null || memberSignUpVO.getAgree3()==null) {
			throw new SignUpFailException("동의 사항에 모두 동의해야합니다.", memberSignUpVO);
		}
		
		boolean isInserted = this.memberService.signupFreelancerMember(memberSignUpVO, categoryVO);
		
		// 회원가입을 완료하면 메인페이지로 이동.
		return new ApiResponse(isInserted);
	}
	
	/**
	 * 이메일 중복확인 요청을 받는 컨트롤러.
	 * @param email
	 * @return
	 */
	@GetMapping("/member/signup/email/available/") 
	public ApiResponse checkAvailableEmail(@RequestParam String email) {
				
		boolean isAvailableEmail = this.memberService.isDuplicatedEmail(email);
		
		return new ApiResponse(isAvailableEmail);
	}
	
	/**
	 * 
	 * @param authentication
	 * @return
	 */
	@GetMapping("/member/myinfo")
	public ApiResponse responseMemberInfo(Authentication authentication) {
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		
		return new ApiResponse(memberVO);
	}
	
	/**
	 * 기업형 마이페이지를 로드하는 컨트롤러.
	 * @param loginMemberVO
	 * @param orderBy
	 * @param cmpnyId
	 * @return
	 */
	@GetMapping("/member/mypage/company/{cmpId}")
	public ApiResponse loadCompanyMyPage(Authentication loginMemberVO
								 , @RequestParam(required = false, defaultValue = "late-date") String orderBy
								 , @PathVariable String cmpId) {
		
		CompanyVO companyVO = this.memberService.selectOneCompanyByEmilAddr(cmpId);
		
		// 보유기술 리스트 조회
		List<MbrPrmStkVO> mbrPrmStkList = companyVO.getMbrPrmStkVOList();
		
		// 주요 산업 조회
		MemberMyPageIndsryVO mbrIndstrVO = memberService.readMbrIndstr(companyVO.getCmpnyId());
		
		// 리뷰 리스트 조회
		Map<String, Function<String, List<ReviewVO>>> sortMethodMap = new HashMap<>();
		sortMethodMap.put("late-date", memberService::selectCompanyReviewList);
		sortMethodMap.put("high-rate", memberService::selectCompanyReviewListByScrDesc);
		sortMethodMap.put("low-rate", memberService::selectCompanyReviewListByScrAsc);
		
		List<ReviewVO> reviewList = sortMethodMap.getOrDefault(orderBy, memberService::selectReviewList).apply(companyVO.getCmpnyId());
		
		// 전체 리뷰 평균 별 계산
		double averageRate = reviewList.stream().mapToDouble(ReviewVO::getScr).average().orElse(0);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("skillList", mbrPrmStkList);
		resultMap.put("industry", mbrIndstrVO);
		resultMap.put("reviewList", reviewList);
		resultMap.put("averageRate", averageRate);
		resultMap.put("companyVO", companyVO);
		
		return new ApiResponse(resultMap);
	}
	
	/**
	 * 프리랜서 마이페이지 로드하는 컨트롤러.
	 * @return
	 */
	@GetMapping("/member/mypage/freelancer/{email}/")
	public String loadFreelancerMyPage(@SessionAttribute(value = "_LOGIN_USER_", required = false)MemberVO loginMemberVO
			, @PathVariable String email, Model model
			, @RequestParam(required = false, defaultValue = "late-date") String orderBy) {
		// 보유기술 리스트 조회
		List<MbrPrmStkVO> mbrPrmStkList = memberService.selectMbrPrmStkList(email);
		model.addAttribute("mbrPrmStkList", mbrPrmStkList);
		
		// 관심 산업 조회
		MemberMyPageIndsryVO mbrIndstrVO = memberService.readMbrIndstr(email);
		model.addAttribute("mbrIndstrVO", mbrIndstrVO);
		
		// 리뷰 리스트 조회
		Map<String, Function<String, List<ReviewVO>>> sortMethodMap = new HashMap<>();
		sortMethodMap.put("late-date", memberService::selectReviewList);
		sortMethodMap.put("high-rate", memberService::selectReviewListBySrcDesc);
		sortMethodMap.put("low-rate", memberService::selectReviewListBySrcAsc);
		
		List<ReviewVO> reviewList = sortMethodMap.getOrDefault(orderBy, memberService::selectReviewList).apply(email);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("orderBy", orderBy);
		
		// 전체 리뷰 평균 별 계산
		double averageRate = reviewList.stream().mapToDouble(ReviewVO::getScr).average().orElse(0);
		model.addAttribute("averageRate", averageRate);
		
		// 소속 산업군명 조회
		MemberMyPageIndsryVO memberMyPageIndsryVO = memberService.selectIndstrNmByEmilAddr(email);
		model.addAttribute("memberMyPageIndsryVO", memberMyPageIndsryVO);
		
		MemberVO memberVO = memberService.selectOneMemberVO(email);
		model.addAttribute("memberVO", memberVO);
				
		return "member/mypage_freelancer";
	}
	
	/**
	 * 기업 마이페이지 리뷰 더 보기 상세페이지
	 * @return
	 */
	@GetMapping("/member/mypage/company/reviewlist/{cmpnyId}")
	public String viewMoreReviewListPage(@PathVariable String cmpnyId, @SessionAttribute(value = "_LOGIN_USER_")MemberVO loginMemberVO, Model model, @RequestParam(required = false, defaultValue = "late-date") String orderBy, MemberPaginationVO memberPaginationVO) {
		if(loginMemberVO == null || loginMemberVO.getEmilAddr() == null || loginMemberVO.getEmilAddr().isEmpty()) {
			return "redirect:/";
		}
		
		int reviewListSize = this.memberService.selectCompanyReviewList(cmpnyId).size();
		memberPaginationVO.setPageCount(reviewListSize);
		memberPaginationVO.setSearchEmilParameter(cmpnyId);
		
		// 리뷰 리스트 조회
		// String: 정렬 기준
		// Function<MemberPaginationVO, List<ReviewVO>>: MemberPaginationVO 입력 받아 List<ReviewVO> 반환하는 함수
		// Map.ofEntries: 3개의 키-값 쌍을 한 번에 Map으로 생성
		Map<String, Function<MemberPaginationVO, List<ReviewVO>>> sortMethodMap = Map.ofEntries(
				Map.entry("high-rate",
						paginationVO -> memberService.selectPaginationByScrDesc(paginationVO,
								cmpnyId)),
				Map.entry("low-rate",
						paginationVO -> memberService.selectPaginationByScrAsc(paginationVO,
								cmpnyId)),
				Map.entry("late-date",
						paginationVO -> memberService.selectPagination(paginationVO, cmpnyId)));
		
		// 기본 정렬 함수로 "late-date"의 pagination 적용
		List<ReviewVO> reviewList = sortMethodMap
				.getOrDefault(orderBy,
						paginationVO -> memberService.selectCmpnyPagination(paginationVO, cmpnyId))
				.apply(memberPaginationVO);
		
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("paginationVO", memberPaginationVO);
		
		
		return "member/morereviewlist";
	}
	
	/**
	 * 프리랜서 마이페이지 리뷰 더 보기 상세페이지
	 * @return
	 */
	@GetMapping("/member/mypage/freelancer/reviewlist/{email}")
	public String viewFreelancerMoreReviewListPage(@PathVariable String email, @SessionAttribute(value = "_LOGIN_USER_")MemberVO loginMemberVO, Model model, @RequestParam(required = false, defaultValue = "late-date") String orderBy, MemberPaginationVO memberPaginationVO) {
		if(loginMemberVO == null || loginMemberVO.getEmilAddr() == null || loginMemberVO.getEmilAddr().isEmpty()) {
			return "redirect:/";
		}
		
		int reviewListSize = this.memberService.selectReviewList(email).size();
		memberPaginationVO.setPageCount(reviewListSize);
		memberPaginationVO.setSearchEmilParameter(email);
		
		// 리뷰 리스트 조회
		// String: 정렬 기준
		// Function<MemberPaginationVO, List<ReviewVO>>: MemberPaginationVO 입력 받아 List<ReviewVO> 반환하는 함수
		// Map.ofEntries: 3개의 키-값 쌍을 한 번에 Map으로 생성
		Map<String, Function<MemberPaginationVO, List<ReviewVO>>> sortMethodMap = Map.ofEntries(
				Map.entry("high-rate",
						paginationVO -> memberService.selectPaginationByScrDesc(paginationVO,
								email)),
				Map.entry("low-rate",
						paginationVO -> memberService.selectPaginationByScrAsc(paginationVO,
								email)),
				Map.entry("late-date",
						paginationVO -> memberService.selectPagination(paginationVO, email)));
		
		// 기본 정렬 함수로 "late-date"의 pagination 적용
		List<ReviewVO> reviewList = sortMethodMap
				.getOrDefault(orderBy,
						paginationVO -> memberService.selectPagination(paginationVO, email))
				.apply(memberPaginationVO);
		
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("paginationVO", memberPaginationVO);
		
		
		return "member/morereviewlistfreelancer";
	}
	
	/**
	 * 기업 마이페이지 수정 페이지
	 * @return
	 */
	@GetMapping("/member/mypage/company/edit/{cmpnyId}")
	public String viewCompanyMyPageEdit(Model model 
										,@PathVariable String cmpnyId
										, @SessionAttribute(value = "_LOGIN_USER_", required = false)MemberVO memberVO) {
		
		if(!memberVO.getCmpId().equals(cmpnyId) || memberVO.getCmpId()==null ) {
			throw new SessionNotFoundException("회사정보가 없습니다");
		}
		
		CompanyVO companyVO =  this.memberService.selectOneCompanyByEmilAddr(cmpnyId);
		model.addAttribute("companyVO",companyVO);
		
		// 보유기술 리스트 조회
		List<MbrPrmStkVO> mbrPrmStkList = memberService.selectMbrPrmStkCmpnyList(cmpnyId);
		model.addAttribute("mbrPrmStkList", mbrPrmStkList);
		
		return "member/mypagecompanyedit";
	}
	
	/**
	 * 
	 * @param memberCompanyModifyVO
	 * @param memberVO
	 * @return
	 */
	@PostMapping("/member/mypage/company/edit")
	public Map<String, Object> doCompanyMyPageEdit(@RequestBody MemberCompanyModifyVO memberCompanyModifyVO 
									, @SessionAttribute(value = "_LOGIN_USER_" , required = false)MemberVO memberVO) {
		
		if (memberVO == null) {
			return Map.of("response", false, "message", "로그인 세션이 만료되었거나 유효하지 않습니다.");
		}
		
		if (memberCompanyModifyVO.getCmpnyNm() == null || memberCompanyModifyVO.getCmpnyAddr() == null || memberCompanyModifyVO.getCmpnyAccuntNum() == null) {
			return Map.of("response",
					false,
					"message",
					"필수 정보(회사명, 회사 주소, 회사 계좌번호)가 누락되었습니다. 모든 필수 정보를 입력해 주세요.",
					"data",
					memberCompanyModifyVO
			);
		}
		
		boolean isSuccess = this.memberService.updateCompanyMemberMyPage(memberCompanyModifyVO, memberVO);
		
		if (isSuccess && memberCompanyModifyVO.getMbrPrmStkList() != null) {
			boolean skillsUpdated = memberService.updateMbrSkills(memberCompanyModifyVO.getMbrPrmStkList(), memberVO.getEmilAddr());
			if (!skillsUpdated) {
				return Map.of("response", false, "message", "보유 기술 업데이트에 실패했습니다.");
			}
		}
		
		return Map.of("response", isSuccess, "data", memberCompanyModifyVO);
	}
	
//	/**
//	 * 프리랜서 마이페이지 수정 페이지
//	 * @return
//	 */
//	@GetMapping("/member/mypage/freelancer/edit/{email}")
//	public String viewFreelancerMyPageEdit(Authentication loginMemberVO
//										, @PathVariable String email, Model model) {
//		
//		int ctgry = loginMemberVO.getMbrCtgry();
//		if(ctgry == 0) {
//			return "redirect:/member/mypage/company/edit/"+loginMemberVO.getCmpId();
//		}
//		
//		// 보유기술 리스트 조회
//		List<MbrPrmStkVO> mbrPrmStkList = memberService.selectMbrPrmStkList(loginMemberVO.getEmilAddr());
//		model.addAttribute("mbrPrmStkList", mbrPrmStkList);
//		
//		// 소속 산업군명 조회
//		MemberMyPageIndsryVO memberMyPageIndsryVO = memberService.selectIndstrNmByEmilAddr(loginMemberVO.getEmilAddr());
//		model.addAttribute("memberMyPageIndsryVO", memberMyPageIndsryVO);
//		
//		MemberVO memberVO = memberService.selectOneMemberVO(loginMemberVO.getEmilAddr());
//		model.addAttribute("memberVO", memberVO);
//
//		return "member/mypagefreelanceredit";
//	}
	
	/**
	 * 
	 * @param memberVO
	 * @param memberFreelancerModifyVO
	 * @return
	 */
	@PostMapping("/member/mypage/freelancer/edit")
	public ApiResponse doFreelancerMyPageEdit(Authentication memberVO
										, @RequestBody MemberFreelancerModifyVO memberFreelancerModifyVO) {
		
		boolean isSuccess = memberService.updateFreelancerMemberMypage(memberFreelancerModifyVO);
		return new ApiResponse(isSuccess);
	}
	
//	@GetMapping("/member/newportfolio")
//	public String loadWriteNewPortfolioPage() {
//		return "/portfolio/portfolio_write";
//	}
	
	/**
	 * 새로운 포트폴리오를 등록하는 요청을 보내는 컨트롤러이다.
	 * @param memberPortfolioVO : 사용자가 입력한 포트폴리오 정보.
	 * @return
	 */
	@PostMapping("/member/newportfolio")
	public ApiResponse requestNewPortfolio(Authentication memberVO
									, MemberPortfolioVO memberPortfolioVO) {
		// 서비스에 새로운 포트폴리오 등록 요청을 보낸다.
		memberPortfolioVO.setEmilAddr(memberVO.getName());
		boolean isCreated = this.memberService.createNewPortfolio(memberPortfolioVO);
		
		return new ApiResponse(isCreated);
	}
	
//	/**
//	 * 프리랜서 포트폴리오 페이지를 로드하는 컨트롤러.
//	 * @param loginMemberVO
//	 * @return
//	 */
//	@GetMapping("/member/mypage/freelancer/portfolio/{email}")
//	public String loadPortfolioListPageFL(@PathVariable String email,
//										Authentication loginMemberVO) {
//		return "portfolio/portfoliolist";
//	}
	
//	/**
//	 * 기업 회원의 포트폴리오 목록 리스트 페이지로딩.
//	 * @return
//	 */	
//	@GetMapping("/member/mypage/company/portfolio/{cmpnyId}")
//	public String loadPortfolioListPageCmp(@PathVariable String cmpnyId
//										, Authentication loginMemberVO) {
//		return "portfolio/portfoliolist";
//	}
//	
	/**
	 * 특정 회원의 포트폴리오 목록 리스트 불러오는 메서드.
	 * @param loginMemberVO
	 * @return
	 */
	@GetMapping("/member/mypage/company/portfolio/content/{parameter}")
	public ApiResponse viewPortfolioListPage(@PathVariable String parameter) {
		return new ApiResponse();
	}
	
	/**
	 * 내정보 수정을 하는 컨트롤러.
	 * @param memberModifyVO
	 * @param memberVO
	 * @param model
	 * @return
	 */
	@PostMapping("/member/mypage/myinfo-edit")
	public ApiResponse doMyInfoEdit(MemberModifyVO memberModifyVO) {
		boolean isSuccess = this.memberService.updateMyInfoMember(memberModifyVO);
		
		return new ApiResponse(isSuccess);
	}
	
	/**
	 * 기업 주소 정보
	 * @param loginMemberVO
	 * @return
	 */
	@GetMapping("/member/mypage/company/address/{cmpId}")
	public ApiResponse getCompanyAddress(@PathVariable String cmpId) {
		CompanyVO companyVO = memberService.selectOneCompanyByEmilAddr(cmpId);
		
		return new ApiResponse(companyVO);
	}
	
	/**
	 * 기업 회원 리뷰 조회하는 페이지를 로드하는 컨트롤러.
	 * @param loginMemberVO 로그인한 회원 정보 객체
	 * @param orderBy 리뷰 정렬 기준
	 * @return
	 */
	// TODO 이거 수정해야함 -의진-
	@GetMapping("/member/mypage/company/reviews/{cmpnyId}")
	public ApiResponse selectCompanySortedReviewsList(@PathVariable String cmpnyId) {

		return new ApiResponse();
	}
	
	/**
	 * 특정 이메일의 프리랜서 리뷰 목록을 조회하는 컨트롤러
	 * @param email 리뷰를 조회할 프리랜서의 이메일
	 * @param orderBy 리뷰 정렬 기준
	 * @return
	 */
	// TODO 이거 수정해야함 -의진-
	@GetMapping("/member/mypage/freelancer/reviews/{email}")
	public ApiResponse selectSortedReviewsList(@PathVariable String email) {
		
		return new ApiResponse();
	}
	
	/**
	 * 특정 회원의 관심 산업 데이터를 조회
	 * @param loginMemberVO 로그인한 멤버 정보 객체
	 * @return
	 */
	@GetMapping("/member/mypage/company/industry/{cmpId}")
	public ApiResponse getCompanyIndustry(@PathVariable String cmpId) {
		MemberMyPageIndsryVO mbrLkIndstrVO = memberService.selectMbrLkIndstr(cmpId);
		
		return new ApiResponse(mbrLkIndstrVO);
	}
	
	/**
	 * 하나의 포트폴리오 정보를 조회하는 컨트롤러.
	 * @param loginMemberVO
	 * @param mbrPrtflId
	 * @return
	 */
	@GetMapping("/view/portfolio/detail/{mbrPrtflId}")
	public ApiResponse loadDataPortfolioDetailsOne(@PathVariable String mbrPrtflId) {
		MemberPortfolioVO memberPortfolioVO = this.memberService.selectOnePortfolio(mbrPrtflId);
		return new ApiResponse(memberPortfolioVO);
	}
	
//	/**
//	 * 
//	 * @return
//	 */
//	@GetMapping("/view/portfolio/view/detail/{mbrPrtflId}")
//	public String loadDataPortfolioDetailsOne() {
//		return "/portfolio/portfoliolist";
//	}
	
	/**
	 * 하나의 포트폴리오 정보를 제거하는 메소드.
	 * @param mbrPrtflId
	 * @return
	 */
	@PostMapping("/member/delete/portfolio/{mbrPrtflId}")
	public ApiResponse deleteOnePortfolio(@PathVariable String mbrPrtflId) {
		boolean isDeleted = this.memberService.deleteOnePortfolio(mbrPrtflId);
		
		return new ApiResponse(isDeleted);
	}
	
	/**
	 * 회원의 포트폴리오를 수정하는 컨트롤러.
	 * @param memberVO
	 * @param memberPortfolioVO
	 * @return
	 */
	@PostMapping("/member/update/portfolio/{mbrPrtflId}")
	public ApiResponse updatePortfolio(MemberPortfolioVO memberPortfolioVO) {
		boolean isUpdated = this.memberService.updateOnePortfolio(memberPortfolioVO);
		
		return new ApiResponse(isUpdated);
	}
	
//	@GetMapping("/member/company/
//	public ApiResponse getCmpIdByEmail(Authentication memberVO) {
//		String email = memberVO.getName();
//		
//		return new ApiResponse();
//	}
	
	@GetMapping("/member/logout")
	public ApiResponse doLogout(Authentication memberVO) {
		return new ApiResponse();
	}
}
