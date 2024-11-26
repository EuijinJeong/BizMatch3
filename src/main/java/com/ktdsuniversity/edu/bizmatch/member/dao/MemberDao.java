package com.ktdsuniversity.edu.bizmatch.member.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.common.category.vo.CategoryVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.CompanyVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberCompanyModifyVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberCompanySignUpVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberFreelancerModifyVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberLoginVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberModifyVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberMyPageIndsryVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberPaginationVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberPortfolioPagenationVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberPortfolioVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberResetPwdVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberSignUpVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.PrmStkVO;
import com.ktdsuniversity.edu.bizmatch.project.review.vo.ReviewVO;

public interface MemberDao {

	public String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.member.dao.MemberDao";

	/**
	 * 회원의 관심 산업군을 추가하는 쿼리문을 호출하는 메서드.
	 * 
	 * @param categoryVO : 산업군의 정보.
	 * @return : insert된 컬럼의 개수.
	 */
	public int insertOneIndustryInfo(CategoryVO categoryVO);

	/**
	 * 사용자의 비밀번호 정보를 갱신하는 쿼리문을 호출하는 메서드.
	 * 
	 * @param memberResetPwdVO : 사용자가 입력한 비밀번호 재설정 정보.
	 * @return : 업데이트된 컬럼의 갯수.
	 */
	public int updateOneMember(MemberResetPwdVO memberResetPwdVO);

	
	/**
	 * 한 명의 회원 정보를 조회하는 쿼리문을 수행하는 메서드이다.
	 * @param emilAddr : 사용자가 입력한 이메일 정보
	 * @return MemberVO
	 */
	public MemberVO selectOneMemberVO(String emilAddr);
	
	/**
	 * 한 명의 회원 정보를 조회하는 쿼리문을 수행하는 메서드이다.
	 * 
	 * @param email : 사용자가 입력한 이메일 정보.
	 * @return : 멤버 정보.
	 */
	public MemberVO selectOneMember(String email);

	/**
	 * 한 명의 회원 정보를 조회하는 쿼리문을 수행하는 메서드이다.
	 * 
	 * @param memberLoginVO : 사용자가 로그인을 시도할 때 입력한 정보를 담은 객체.
	 * @return memberVO : 멤버 정보.
	 */
	public MemberVO selectOneMember(MemberLoginVO memberLoginVO);

	/**
	 * 
	 * @param cmpnyBrn
	 * @return
	 */
	public CompanyVO selectOneCompany(String cmpnyBrn);

	/**
	 * 
	 * @param email
	 * @return
	 */
	public String selectSalt(String email);

	/**
	 * 
	 * @param memberLoginVO
	 * @return
	 */
	public int updateLoginFailCnt(MemberLoginVO memberLoginVO);

	/**
	 * 
	 * @param memberLoginVO
	 * @return
	 */
	public int selectLoginFailCnt(MemberLoginVO memberLoginVO);

	/**
	 * 
	 * @param memberLoginVO
	 * @return
	 */

	public int updateLoginSuccessState(MemberLoginVO memberLoginVO);

	/**
	 * 한 기업의 회원 정보를 삽입하는 쿼리문을 수행하는 메서드이다.
	 * 
	 * @param memberCompanySignUpVO : 회원유형이 기업에 해당하는 사용자가 입력한 정보를 담은 객체.
	 * @return : 삽입된 쿼리문의 개수..
	 */
	public int insertOneMemberCompany(MemberCompanySignUpVO memberCompanySignUpVO);

	/**
	 * 
	 * @param memberSignUpVO
	 * @return
	 */
	public int insertOneMemberFreelancer(MemberSignUpVO memberSignUpVO);

	/**
	 * 
	 * @param memberSignUpVO
	 * @return
	 */
	public int insertOneMember(MemberCompanySignUpVO memberSignUpVO);

	/**
	 * 이메일 중복 방지를 위한 쿼리문을 수행하는 메서드.
	 * 
	 * @param email
	 * @return
	 */
	public int selectEmailCnt(String email);

	/**
	 * 회원 계정 비활성화(삭제)
	 * 
	 * @param email
	 * @return
	 */
	public int updateMemberDeactivate(String email);

	/**
	 * 제공된 주요 기술 List 조회
	 * 
	 * @return 주요기술 List
	 */
	public List<PrmStkVO> selectAllSkills();

	/**
	 * 사이트에서 제공되는 보유기술 리스트에 없는 새로운 보유기술 추가
	 * 
	 * @param prmStkVO : 추가할 기술 정보를 포함한 객체
	 * @return 추가된 보유기술 ID
	 */
	public int insertNewSkills(PrmStkVO prmStkVO);

	/**
	 * 특정 회원의 모든 포트폴리오 조회
	 * 
	 * @param emilAddr 회원 이메일
	 * @return 특정 회원의 포트폴리오 리스트
	 */
	public List<MemberPortfolioVO> selectAllPortfolios(String emilAddr);

	/**
	 * 특정 포트폴리오 아이디로 하나의 포트폴리오 조회
	 * 
	 * @param portfolioId 회원 포트폴리오 아이디
	 * @return 해당 포트폴리오 객체
	 */
	public MemberPortfolioVO selectOnePortfolio(String portfolioId);

	/**
	 * 새로운 포트폴리오 추가
	 * 
	 * @param memberPortfolioVO 추가할 포트폴리오 정보
	 * @return 추가된 포트폴리오 개수
	 */
	public int insertNewPortfolio(MemberPortfolioVO memberPortfolioVO);

	/**
	 * 기존의 포트폴리오 수정
	 * 
	 * @param memberPortfolioVO 수정할 포트폴리오 정보
	 * @return 수정된 포트폴리오 개수
	 */
	public int updateOnePortfolio(MemberPortfolioVO memberPortfolioVO);

	/**
	 * 하나의 포트폴리오 삭제
	 * 
	 * @param portfolioId 포트폴리오 아이디
	 * @return 삭제된 포트폴리오 개수
	 */
	public int deleteOnePortfolio(String portfolioId);

	/**
	 * 기업 정보 조회
	 * 
	 * @param emilAddr 회원 이메일
	 * @return
	 */
	public CompanyVO selectOneCompanyByEmilAddr(String cmpnyId);
	
	
// 	/*
// 	 * 회원, 기업 정보 조회
// 	 * 
// 	 * @param emilAddr 회원 이메일
// 	 * @return
// 	 */
// 	public MemberVO selectMemberAndCompany(String emilAddr);

	/**
	 * 리뷰 리스트 조회
	 * 
	 * @param emilAddr 회원 이메일
	 * @return
	 */
	public List<ReviewVO> selectReviewList(String emilAddr);

	/**
	 * 리뷰 리스트 별점 높은 순 조회
	 * 
	 * @param emilAddr 회원 이메일
	 * @return
	 */
	public List<ReviewVO> selectReviewListByScrDesc(String emilAddr);

	/**
	 * 리뷰 리스트 별점 낮은 순 조회
	 * 
	 * @param emilAddr 회원 이메일
	 * @return
	 */
	public List<ReviewVO> selectReviewListByScrAsc(String emilAddr);

	/**
	 * 회원의 관심산업군 조회
	 * 
	 * @param emilAddr 회원 이메일
	 * @return 특정 회원 관심산업군 객체
	 */
	public MemberMyPageIndsryVO selectMbrLkIndstr(String cmpId);
	
	/**
	 * 회원의 산업군 가져오기
	 * @param emilAddr
	 * @return
	 */
	public MemberMyPageIndsryVO selectMbrIndsty(String cmpId);

	/**
	 * 기업 회원 마이페이지 수정
	 * 
	 * @param 회사 이름 , 회사 사이트, 소개글, 회사 주소 수정 가능
	 */
	public int updateMemberCompanyMyPage(MemberCompanyModifyVO memberCompanyModifyVO);

	/**
	 * 리뷰 최신순 리스트 pagination 조회
	 * 
	 * @param memberPaginationVO
	 * @return
	 */
	public List<ReviewVO> selectPagination(MemberPaginationVO memberPaginationVO);

	/**
	 * 리뷰 별점 높은순 리스트 pagination 조회
	 * 
	 * @param memberPaginationVO
	 * @return
	 */
	public List<ReviewVO> selectPaginationByScrDesc(MemberPaginationVO memberPaginationVO);

	/**
	 * 별점 낮은순 리스트 pagination 조회
	 * 
	 * @param memberPaginationVO
	 * @return
	 */
	public List<ReviewVO> selectPaginationByScrAsc(MemberPaginationVO memberPaginationVO);

	/**
	 * 내 정보 수정
	 * 
	 * @param memberModifyVO
	 * @return
	 */
	public int updateMyInfoMember(MemberModifyVO memberModifyVO);

	public int updateFrreelancerMemberMypage(MemberFreelancerModifyVO memberFreelancerModifyVO);
	
	/**
	 * 특정 회원의 소속 산업군 조회
	 * @param emilAddr 회원 이메일
	 * @return 산업군 정보 객체
	 */
	public MemberMyPageIndsryVO selectIndstrNmByEmilAddr(String emilAddr);
	
	/**
	 * 특정 회원의 관심 산업군 변경
	 * @param memberCompanyModifyVO
	 * @return
	 */
	public int updateCmpnyLkIndstr(MemberCompanyModifyVO memberCompanyModifyVO);
	
	/**
	 * 특정 회원의 모든 포트폴리오 조회 ver Pagenation
	 * 
	 * @param memberPortfolioPagenationVO 페이지네이션에 필요한 VO ( 이메일 등 포함)
	 * @return 특정 회원의 페이지 네이션 된 포트폴리오 리스트 
	 */
	public List<MemberPortfolioVO> selectAllPortfoliosForPagenation(MemberPortfolioPagenationVO memberPortfolioPagenationVO);

	/**
	 * 특정 기업의 모든 포트폴리오 조회
	 * @param cmpId
	 * @return
	 */
	public List<MemberPortfolioVO> selectAllCmpnyPortfolios(String cmpId);
	
	/**
	 * 기업 리뷰 최신순 리스트 pagination 조회
	 * 
	 * @param memberPaginationVO
	 * @return
	 */
	public List<ReviewVO> selectCmpnyPagination(MemberPaginationVO memberPaginationVO);

	/**
	 * 기업 리뷰 별점 높은순 리스트 pagination 조회
	 * 
	 * @param memberPaginationVO
	 * @return
	 */
	public List<ReviewVO> selectCmpnyPaginationByScrDesc(MemberPaginationVO memberPaginationVO);

	/**
	 * 기업 별점 낮은순 리스트 pagination 조회
	 * 
	 * @param memberPaginationVO
	 * @return
	 */
	public List<ReviewVO> selectCmpnyPaginationByScrAsc(MemberPaginationVO memberPaginationVO);
	
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