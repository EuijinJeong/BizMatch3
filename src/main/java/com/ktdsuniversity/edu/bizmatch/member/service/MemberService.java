package com.ktdsuniversity.edu.bizmatch.member.service;


import java.util.List;

import com.ktdsuniversity.edu.bizmatch.common.category.vo.CategoryVO;
import com.ktdsuniversity.edu.bizmatch.common.skills.vo.MbrPrmStkVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.CompanyVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberCompanyModifyVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberCompanySignUpVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberFreelancerModifyVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberLoginVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberModifyVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberMyPageIndsryVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberPaginationVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberPortfolioAttVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberPortfolioPagenationVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberPortfolioVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberResetPwdVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberSignUpVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.PrmStkVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;


/**
 * 이 클래스는 memberService에 대한 인터페이스입니다.
 * MemberServiceImpl에서 구현해야할 메소드 목록이 들어있습니다.
 * 
 * @author jeong-uijin
 */
public interface MemberService {
	
	/**
	 * 회원의 비밀번호 변경을 수행하는 메서드이다.
	 * @param memberResetPwdVO : 사용자가 입력한 비밀번호 재설정 정보.
	 * @return : 재설정 성공 여부.
	 */
	public boolean resetMemberPwd(MemberResetPwdVO memberResetPwdVO);
	
	/**
	 * 로그인을 시도하는 회원이 로그인이 가능한 회원인지 비즈니스 로직을 수행하는 메서드이다.
	 * 
	 * @param memberLoginVO : 사용자가 로그인을 시도할 때 입력한 정보를 담은 객체.
	 * @return : 로그인 가능 여부를 반환한다.
	 */
	public MemberVO isVarifiedMemberLogin(MemberLoginVO memberLoginVO);
	
	
	
	/**
	 * 회원유형이 기업인 사용자의 회원가입의 비즈니스 로직을 수행하는 메서드이다.
	 * 
	 * @param memberCompanySignUpVO : 기업 유형의 사용자가 입력한 회원가입 정보를 담은 객체이다.
	 * @return : 회원가입의 성공 여부를 반환한다.
	 */
	public boolean signupCompanyMember(MemberCompanySignUpVO memberCompanySignUpVO);
	
	/**
	 * 회원가입 중복 방지를 위해 중복된 아이디의 개수를 확인하는 비즈니스 로직을 수행하는 메서드이다.
	 * 
	 * @param email
	 * @return
	 */
	public boolean isDuplicatedEmail(String email);
	
	/**
	 * 비밀번호 찾기 이메일 보내는 메소드.
	 * @param email : 사용자가 입력한 이메일 주소.
	 * @return : 이메일 전송 여부.
	 */
	public boolean sendFindPwdEmail(String email);
	
	/**
	 * 하나의 회사를 검색하는 로직
	 * @param cmpnyBrn
	 * @return
	 */
	public CompanyVO readOneCompany(String cmpnyBrn);
	
	/**
	 * 제공된 주요 기술 List 조회
	 * @return 주요기술 List
	 */
	public List<PrmStkVO> selectAllSkills();
	
	/**
	 * 특정 회원이 보유한 기술 List 조회
	 * @param emilAddr 회원 이메일
	 * @return 특정 회원 보유 기술 List
	 */
	public List<MbrPrmStkVO> selectMemberSkills(String emilAddr);
	
	/**
	 * 사이트에서 제공된 주요 기술 리스트 중 보유 기술 추가
	 * @param mbrPrmStkVO 회원 ID와 추가할 기술 ID 리스트를 포함한 객체
	 * @return 성공하면 true, 실패하면 false 반환
	 */
	public boolean insertMemberSkills(List<PrmStkVO> prmStkVOList, MemberVO memberVO);
	
	/**
	 * 회원의 보유 기술 중 보유 기술 삭제
	 * @param mbrPrmStkVO 회원 ID와 삭제할 기술 ID 리스트를 포함한 객체
	 * @return 성공하면 true, 실패하면 false 반환
	 */
	public boolean deleteMemberSkills(MbrPrmStkVO mbrPrmStkVO);
	
	/**
	 * 사이트에서 제공되는 보유기술 리스트에 없는 새로운 보유기술 추가
	 * @param prmStkVO 추가할 기술 정보를 포함한 객체
	 * @return 성공하면 true, 실패하면 false 반환
	 */
	public boolean insertNewSkills(PrmStkVO prmStkVO);
	
	
	/**
	 * 특정 회원의 모든 포트폴리오 조회
	 * @param emilAddr 회원 이메일
	 * @return 성공하면 true, 실패하면 false 반환
	 */
	public List<MemberPortfolioVO> selectAllPortfolios(String emilAddr);
	
	/**
	 * 특정 기업의 모든 포트폴리오 조회
	 * @param emilAddr
	 * @return
	 */
	public List<MemberPortfolioVO> selectAllCmpnyPortfolios(String cmpnyId);
	
	/**
	 * 특정 포트폴리오 아이디로 하나의 포트폴리오 조회
	 * @param portfolioId 회원 포트폴리오 아이디
	 * @return 해당 포트폴리오 객체
	 */
	public MemberPortfolioVO selectOnePortfolio(String portfolioId);
	
	/**
	 * 새로운 포트폴리오 추가
	 * 
	 * @param memberPortfolioVO 추가할 포트폴리오 정보
	 * @return 성공하면 true, 실패하면 false 반환
	 */
	public boolean createNewPortfolio(MemberPortfolioVO memberPortfolioVO);
	
	/**
	 * 기존의 포트폴리오 수정
	 * @param memberPortfolioVO 수정할 포트폴리오 정보
	 * @return 성공하면 true, 실패하면 false 반환
	 */
	public boolean updateOnePortfolio(MemberPortfolioVO memberPortfolioVO);
	
	/**
	 * 하나의 포트폴리오 삭제
	 * @param portfolioId 포트폴리오 아이디
	 * @return 성공하면 true, 실패하면 false 반환
	 */
	public boolean deleteOnePortfolio(String portfolioId);
	
	/**
	 * 회원계정 비활성화
	 * @param email
	 * @return
	 */
	public boolean deactivateMember(String email);

	/**
	 * 기업 정보 조회
	 * @param email 회원 이메일
	 * @return
	 */
	public CompanyVO selectOneCompanyByEmilAddr(String email);
	
	/**
	 * 회원 보유 기술 리스트 조회
	 * @param emilAddr 회원 이메일
	 * @return
	 */
	public List<MbrPrmStkVO> selectMbrPrmStkList (String emilAddr);
	
	/**
	 * 기업 회원 보유기술 리스트 조회
	 * @param cmpnyId
	 * @return
	 */
	public List<MbrPrmStkVO> selectMbrPrmStkCmpnyList(String cmpnyId);
	/**
	 * 리뷰 리스트 조회
	 * @param emilAddr 회원 이메일
	 * @return
	 */
	public List<ReviewVO> selectReviewList(String emilAddr);
	
	/**
	 * 리뷰 리스트 별점 높은 순 조회
	 * @param emilAddr 회원 이메일
	 * @return
	 */
	public List<ReviewVO> selectReviewListBySrcDesc(String emilAddr);
	
	/**
	 * 리뷰 리스트 별점 낮은 순 조회
	 * @param emilAddr 회원 이메일
	 * @return
	 */
	public List<ReviewVO> selectReviewListBySrcAsc(String emilAddr);
	
	/**
	 * 회원의 관심산업군 조회
	 * @param emilAddr 회원 이메일
	 * @return 특정 회원 관심산업군 객체
	 */
	public MemberMyPageIndsryVO selectMbrLkIndstr(String cmpId);

	public MemberMyPageIndsryVO readMbrIndstr(String cmpId);
	/**
	 * 프리랜서 회원의 회원가입 로직을 수행하는 메서드.
	 * @param memberSignUpVO
	 * @param categoryVO
	 * @return
	 */
	public boolean signupFreelancerMember(MemberSignUpVO memberSignUpVO , CategoryVO categoryVO);

	
	/**
	 * 한 포트폴리오에 대한 첨부자료를 조회하는 메서드.
	 * @param mbrPrtflId : 포트폴리오 아이디.
	 * @return : 첨부자료 리스트.
	 */
	public List<MemberPortfolioAttVO> selectAllPortfolioAtt(String mbrPrtflId);


	/**
	 * 기업 회원의 마이페이지 수정을 하는 메서드 
	 * @param memberCompanyModifyVO
	 * @return
	 */
	public boolean updateCompanyMemberMyPage(MemberCompanyModifyVO memberCompanyModifyVO, MemberVO memberVo);

	/**
	 * 리뷰 최신순 리스트 pagination 조회
	 * @param memberPaginationVO
	 * @return
	 */
	public List<ReviewVO> selectPagination(MemberPaginationVO memberPaginationVO, String emilAddr);
	
	/**
	 * 리뷰 별점 높은순 리스트 pagination 조회
	 * @param memberPaginationVO
	 * @return
	 */
	public List<ReviewVO> selectPaginationByScrDesc(MemberPaginationVO memberPaginationVO, String emilAddr);
	
	/**
	 * 별점 낮은순 리스트 pagination 조회
	 * @param memberPaginationVO
	 * @return
	 */
	public List<ReviewVO> selectPaginationByScrAsc(MemberPaginationVO memberPaginationVO, String emilAddr);
	
	/**
	 * 회원 정보 수정
	 */
	public boolean updateMyInfoMember(MemberModifyVO memberModifyVO);
	
	/**
	 * 개인회원 마이페이지 수정 메소드
	 * @param memberFreelancerModifyVO
	 * @return
	 */
	public boolean updateFreelancerMemberMypage(MemberFreelancerModifyVO memberFreelancerModifyVO);
	
	
	/**
	 * 특정 회원의 소속 산업군 조회
	 * @param emilAddr 회원 이메일
	 * @return 산업군 정보 객체
	 */
	public MemberMyPageIndsryVO selectIndstrNmByEmilAddr(String emilAddr);
	
	/**
	 * 한 명의 회원 정보를 조회하는 쿼리문을 수행하는 메서드이다.
	 * @param emilAddr : 사용자가 입력한 이메일 정보
	 * @return MemberVO
	 */
	public MemberVO selectOneMemberVO(String emilAddr);
	
	/**
	 * 특정 회원의 보유스킬 리스트 업데이트
	 * @param emilAddr : 특정회원의 이메일 정보
	 * @param newSkills : 새롭게 추가되는 보유기술 리스트
	 * @return
	 */
	public boolean updateMbrSkills(List<MbrPrmStkVO> newSkills, String emilAddr);
	
	/**
	 * 특정 회원의 모든 포트폴리오 조회 ver Pagenation
	 * @param MemberPortfolioPagenationVO  페이지 네이션에 필요한 정보들이 담겨있어요
	 * @return 페이지 네이션이 적용된 포트폴리오 리스트 
	 */
	public List<MemberPortfolioVO> selectAllPortfoliosForPagenation(MemberPortfolioPagenationVO memberPortfolioPagenationVO);
	
	
	/**
	 * 리뷰 최신순 리스트 pagination 조회
	 * @param memberPaginationVO
	 * @return
	 */
	public List<ReviewVO> selectCmpnyPagination(MemberPaginationVO memberPaginationVO, String cmpId);
	
	/**
	 * 리뷰 별점 높은순 리스트 pagination 조회
	 * @param memberPaginationVO
	 * @return
	 */
	public List<ReviewVO> selectCmpnyPaginationByScrDesc(MemberPaginationVO memberPaginationVO, String cmpId);
	
	/**
	 * 별점 낮은순 리스트 pagination 조회
	 * @param memberPaginationVO
	 * @return
	 */
	public List<ReviewVO> selectCmpnyPaginationByScrAsc(MemberPaginationVO memberPaginationVO, String cmpId);
	
	/**
	 * 기업 리뷰 리스트 조회
	 * 
	 * @param cmpId 회원 기업 아이디
	 * @return
	 */
	public List<ReviewVO> selectCompanyReviewList(String cmpId);

	/**
	 * 기업 리뷰 리스트 별점 높은 순 조회
	 * 
	 * @param cmpId 회원 기업 아이디
	 * @return
	 */
	public List<ReviewVO> selectCompanyReviewListByScrDesc(String cmpId);

	/**
	 * 기업 리뷰 리스트 별점 낮은 순 조회
	 * 
	 * @param cmpId 회원 기업 아이디
	 * @return
	 */
	public List<ReviewVO> selectCompanyReviewListByScrAsc(String cmpId);
	
}
