package com.ktdsuniversity.edu.bizmatch.member.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.bizmatch.common.category.vo.CategoryVO;
import com.ktdsuniversity.edu.bizmatch.member.dao.MemberDao;
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

@Repository
public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int updateOneMember(MemberResetPwdVO memberResetPwdVO) {
		return this.getSqlSession().update(NAMESPACE + ".updateOneMemberForResetPwd", memberResetPwdVO);
	}
	
	@Override
	public MemberVO selectOneMember(String email) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectOneMemberForResetPwd", email);
	}
	
	@Override
	public MemberVO selectOneMember(MemberLoginVO memberLoginVO) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectOneMember", memberLoginVO);
	}
	
	/**
	 * 로그인 시 비밀번호 확인 위해 Salt 값 가져온다.
	 */
	@Override
	public String selectSalt(String email) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectSalt",email);
	}

	@Override
	public int updateLoginFailCnt(MemberLoginVO memberLoginVO) {
		return this.getSqlSession().update(NAMESPACE+".updateLoginFailCnt",memberLoginVO);
	}

	@Override
	public int selectLoginFailCnt(MemberLoginVO memberLoginVO) {
		return this.getSqlSession().selectOne(NAMESPACE+".selectLoginFailCnt", memberLoginVO);
	}

	@Override
	public int updateLoginSuccessState(MemberLoginVO memberLoginVO) {
		return this.getSqlSession().update(NAMESPACE+".updateLoginSuccessState", memberLoginVO);
	}
	
	@Override
	public int insertOneMemberCompany(MemberCompanySignUpVO memberCompanySignUpVO) {
		return this.getSqlSession().insert(NAMESPACE + ".insertOneMemberCompany", memberCompanySignUpVO);
	}
	
	@Override
	public int insertOneMemberFreelancer(MemberSignUpVO memberSignUpVO) {
		return this.getSqlSession().insert(NAMESPACE + ".insertOneMemberFreelancer", memberSignUpVO);
	}
	
	@Override
	public int selectEmailCnt(String email) {
		return this.getSqlSession().selectOne(NAMESPACE +".selectEmailCnt", email);
	}
	
	/*
	 * 마이페이지 시작
	 */
	
	
	@Override
	public List<PrmStkVO> selectAllSkills() {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAllSkills");
	}
	
	@Override
	public int insertNewSkills(PrmStkVO prmStkVO) {
		return this.getSqlSession().insert(NAMESPACE + ".insertNewSkills", prmStkVO);
	}
	
	@Override
	public List<MemberPortfolioVO> selectAllPortfolios(String emilAddr) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAllPortfolios", emilAddr);
	}
	
	@Override
	public MemberPortfolioVO selectOnePortfolio(String portfolioId) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectOnePortfolio", portfolioId);
	}
	
	@Override
	public int insertNewPortfolio(MemberPortfolioVO memberPortfolioVO) {
		return this.getSqlSession().insert(NAMESPACE + ".insertNewPortfolio", memberPortfolioVO);
	}
	
	@Override
	public int updateOnePortfolio(MemberPortfolioVO memberPortfolioVO) {
		return this.getSqlSession().update(NAMESPACE + ".updateOnePortfolio", memberPortfolioVO);
	}
	
	@Override
	public int deleteOnePortfolio(String portfolioId) {
		return this.getSqlSession().delete(NAMESPACE+ ".deleteOnePortfolio", portfolioId);
	}
	

	@Override
	public int updateMemberDeactivate(String email) {
		return this.getSqlSession().update(NAMESPACE+".updateMemberDeactivate", email);
	}

	@Override
	public CompanyVO selectOneCompany(String cmpnyBrn) {
		return this.getSqlSession().selectOne(NAMESPACE+".selectOneCompany",cmpnyBrn);
	}

	@Override
	public int insertOneMember(MemberCompanySignUpVO memberSignUpVO) {
		return this.getSqlSession().insert(NAMESPACE+".insertOneMember", memberSignUpVO);
	}
	
	@Override
	public CompanyVO selectOneCompanyByEmilAddr(String cmpnyId) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectOneCompanyByEmilAddr", cmpnyId);
	}
	
	@Override
	public List<ReviewVO> selectReviewList(String emilAddr) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectReviewList", emilAddr);
	}

	@Override
	public int insertOneIndustryInfo(CategoryVO categoryVO) {
		return this.getSqlSession().insert(NAMESPACE + ".insertOneIndustryInfoSignUp", categoryVO);
	}
	
	@Override
	public List<ReviewVO> selectReviewListByScrDesc(String emilAddr) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectReviewListByScrDesc", emilAddr);
	}
	
	@Override
	public List<ReviewVO> selectReviewListByScrAsc(String emilAddr) {
		return this.getSqlSession().selectList(NAMESPACE+ ".selectReviewListByScrAsc", emilAddr);
	}
	
	@Override
	public MemberMyPageIndsryVO selectMbrLkIndstr(String cmpId) {
		return this.getSqlSession().selectOne(NAMESPACE+ ".selectMbrLkIndstr", cmpId);
	}
	
	@Override
	public int updateMemberCompanyMyPage(MemberCompanyModifyVO memberCompanyModifyVO) {
		return this.getSqlSession().update(NAMESPACE+".updateMemberCompanyMyPage", memberCompanyModifyVO);
	}

	@Override
	public List<ReviewVO> selectPagination(MemberPaginationVO memberPaginationVO) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectPagination", memberPaginationVO);
	}

	@Override
	public List<ReviewVO> selectPaginationByScrDesc(MemberPaginationVO memberPaginationVO) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectPaginationByScrDesc", memberPaginationVO);
	}

	@Override
	public List<ReviewVO> selectPaginationByScrAsc(MemberPaginationVO memberPaginationVO) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectPaginationByScrAsc", memberPaginationVO);
	}

	@Override
	public int updateMyInfoMember(MemberModifyVO memberModifyVO) {
		return this.getSqlSession().update(NAMESPACE+".updateMyInfoMember", memberModifyVO);
	}
	
//	@Override
//	public MemberVO selectMemberForMypage(String email) {
//		return this.getSqlSession().selectOne(NAMESPACE+".selectMemberForMypage", email);
//	}

	@Override
	public int updateFrreelancerMemberMypage(MemberFreelancerModifyVO memberFreelancerModifyVO) {
		this.getSqlSession().update(NAMESPACE+".updateFrreelancerMemberMypage", memberFreelancerModifyVO);
		return 0;
	}
	
	@Override
	public MemberMyPageIndsryVO selectMbrIndsty(String cmpId) {
		return this.getSqlSession().selectOne(NAMESPACE+".selectMbrIndsty", cmpId);
	}


	
	@Override
	public MemberMyPageIndsryVO selectIndstrNmByEmilAddr(String emilAddr) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectIndstrNmByEmilAddr", emilAddr);
	}
	
	@Override
	public MemberVO selectOneMemberVO(String emilAddr) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectOneMemberVO", emilAddr);
	}
	
	@Override
	public int updateCmpnyLkIndstr(MemberCompanyModifyVO memberCompanyModifyVO) {
		return this.getSqlSession().update(NAMESPACE + ".updateCmpnyLkIndstr", memberCompanyModifyVO);
	}

	@Override
	public List<MemberPortfolioVO> selectAllPortfoliosForPagenation(
			MemberPortfolioPagenationVO memberPortfolioPagenationVO) {
		return this.getSqlSession().selectList(NAMESPACE+".selectAllPortfoliosForPagenation",memberPortfolioPagenationVO);
	}

	@Override
	public List<MemberPortfolioVO> selectAllCmpnyPortfolios(String cmpId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectAllCmpnyPortfolios", cmpId);
	}

	@Override
	public List<ReviewVO> selectCmpnyPagination(MemberPaginationVO memberPaginationVO) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectCmpnyPagination", memberPaginationVO);
	}

	@Override
	public List<ReviewVO> selectCmpnyPaginationByScrDesc(MemberPaginationVO memberPaginationVO) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectCmpnyPaginationByScrDesc", memberPaginationVO);
	}

	@Override
	public List<ReviewVO> selectCmpnyPaginationByScrAsc(MemberPaginationVO memberPaginationVO) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectCmpnyPaginationByScrAsc", memberPaginationVO);
	}

	@Override
	public List<ReviewVO> selectCompanyReviewList(String cmpId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectCompanyReviewList", cmpId);
	}

	@Override
	public List<ReviewVO> selectCompanyReviewListByScrDesc(String cmpId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectCompanyReviewListByScrDesc", cmpId);
	}

	@Override
	public List<ReviewVO> selectCompanyReviewListByScrAsc(String cmpId) {
		return this.getSqlSession().selectList(NAMESPACE + ".selectCompanyReviewListByScrAsc", cmpId);
	}
	
	
}