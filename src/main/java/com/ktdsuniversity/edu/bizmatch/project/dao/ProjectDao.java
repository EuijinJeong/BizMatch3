package com.ktdsuniversity.edu.bizmatch.project.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.PrmStkVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ApplyProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ModifyProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentModifyVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentPaginationVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectIndustryVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectScrapVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectSkillVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.SearchApplyVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.SearchProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.SelectApplyMemberVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.UpdateProjectSttVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.WriteProjectVO;


public interface ProjectDao {

	public String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.project.dao.ProjectDao";
	
	/**
	 * 하나의 프로젝트 정보를 조회하는 메서드.
	 * @param email : 사용자의 이메일 정보.
	 * @return : 프로젝트 VO.
	 */
	public ProjectVO selectOneProjectInfo(String email);
	
	/**
	 * 하나의 프로젝트 정보를 조회하는 메소드 인데! 프로젝트 아이디로 조회
	 * @param pjId
	 * @return
	 */
	public ProjectVO selectProjectInfo(String pjId);
	
	/**
	 * 
	 * @param searchProjectVO
	 * @return
	 */
	public int selectProjectAllCount(SearchProjectVO searchProjectVO);
	
	/**
	 * 
	 * @return
	 */
	public List<ProjectVO> selectAllCardProject();
	
	public List<ProjectVO> selectAllCardProjectSorted(String orderBy);
	
	public List<ProjectVO> selectAllCardProject(SearchProjectVO SearchProjectVO);
	
	public int insertOneProject(WriteProjectVO writeProjectVO);
	
	/**
	 * 한 프로젝트의 대한 내용을 수정하는 쿼리문을 호출하는 메서드.
	 * @param modifyProjectVO : 프로젝트 정보를 담은 객체.
	 * @return : 업데이트된 쿼리문의 개수.
	 */
	public int updateOneProject(ModifyProjectVO modifyProjectVO);
	
	/**
	 * 하나의 프로젝트를 삭제하는 쿼리문을 호출하는 메서드.
	 * @param pjId
	 * @return
	 */
	public int deleteOneProject(String pjId);
	
	/**
	 * 
	 * @param applyProjectVO
	 * @return
	 */
	public int insertOneProjectApply (ApplyProjectVO applyProjectVO);
	
	/**
	 * 프로젝트의 진행 상태를 업데이트해주는 쿼리문을 수행하는 메서드.
	 * @param updateProjectSttVO
	 * @return
	 */
	public int updateOneProjectStt(UpdateProjectSttVO updateProjectSttVO);
	
	/**
	 * 
	 * @param searchProjectVO
	 * @return
	 */
	public List<ProjectVO> selectForPagination(SearchProjectVO searchProjectVO);
	
	/**

	 *  이하 프로젝트 댓글 관련 Dao 메세드
	 */
	public List<ProjectCommentVO> selectAllComment(String id);
	public int insertNewComment(ProjectCommentWriteVO projectCommentWriteVO);
	public int deleteOneComment(String id);
	public int updateOneComment(ProjectCommentModifyVO projectCommentModifyVO);
	public List<ProjectCommentVO> selectPaginationComment(ProjectCommentPaginationVO projectCommentPaginationVO);
	

	/*
	 * 프로젝트 조회수 증가하는 쿼리문을 호출하는 메서드.
	 * @param  : 프로젝트 아이디
	 * @return : 업데이트된 쿼리문의 개수.
	 */
	public int updateProjectViewCnt(String pjId);
	
	/**
	 * 프로젝트의 산업군을 수정하는 쿼리문을 호출하는 메서드.
	 * @param projectIndustryVO : 프로젝트 수정 정보를 담은 객체.
	 * @return : 수정된 쿼리문의 개수.
	 */
	public int updateProjectIndustry(ProjectIndustryVO projectIndustryVO);
	
	/**
	 * 프로젝트 추가모집시 수정하는 쿼리문을 호출하는 메서드.
	 * @param addtionalRecruitmentVO : 추가모집시 수정되는 정보를 담은 객체.
	 * @return : 수정된 컬럼의 개수.
	 */
	public int updateAddtionalRecruitment(ModifyProjectVO modifyProjectVO);
	
	/**
	 * 프로젝트 지원내용을 수정하는 쿼리문을 호출하는 메서드.
	 * @param applyProjectVO
	 * @return
	 */
	public int updateProjectApply(ApplyProjectVO applyProjectVO);
	
	/**
	 * 프로젝트 지원내용을 삭제하는 쿼리문을 호출하는 메서드.
	 * @param applyProjectVO
	 * @return
	 */
	public int deleteProjectApply(String pjApplyId);
	
	/**
	 * 해당 프로젝트에 지원자 들을 찾는 메소드
	 * @param pjId
	 * @return
	 */
	public List<ApplyProjectVO> selectAllApplyMember(String pjId);
	
	/**
	 * 지원자 선택하는 메소드
	 * @param emailAddr
	 * @return
	 */
	public int updateProjectApplyMember(SelectApplyMemberVO selectApplyMemberVO);
	
	/**
	 * 기업 회원이 기업이 발주한 모든 프로젝트를 검색
	 * @param email
	 * @return
	 */
	public List<ProjectVO> selectAllProjectCompanyOrder(String email);
	
	/**
	 * 기업 회원이 기업이 수행한 모든 프로젝트를 검색
	 * @param memberVO
	 * @return
	 */
	public List<ProjectVO> selectAllProjectOrderRecipient(MemberVO memberVO);
	
	/**
	 * 내가 작성한 지원서 모두 가져오기
	 * @param memberVo
	 * @return
	 */
	public List<ApplyProjectVO> selectAllApply(MemberVO memberVo);
	
	/*
	 * 프로젝트 스크랩 정보를 추가하는 쿼리문을 호출하는 메서드.
	 * @param projectScrapVO : 프로젝트 스크랩 정보를 담고 있는 객체.
	 * @return
	 */
	public int insertProjectScrap(ProjectScrapVO projectScrapVO);
	
	/**
	 * 특정 프로젝트의 보유 기술들을 조회하는 쿼리문을 호출하는 메서드.
	 * @param pjId
	 * @return
	 */

	public List<ProjectSkillVO> selectAllProjectSkill(String pjId);

	/**
	 * 특정 프로젝트의 보유 산업군들을 조회하는 쿼리문을 호출하는 메서드.
	 * @param pjId
	 * @return
	 */
	public List<ProjectIndustryVO> selectAllProjectIndustryInfo(String pjId);

	
	/**
	 * 특정 프로젝트의 사업군을 전제 프로젝트 산업군 목록에 넣는 메서드.
	 * @param projectIndustryVO
	 * @return
	 */
	
	public int insertNewIndustryGroup(ProjectIndustryVO projectIndustryVO);

	/**
	 * 특정 프로젝트의 특정 지원서의 정보를 가져오는 쿼리문을 호출하는 메서드.
	 * @param applyProjectVO
	 * @return
	 */
	public ApplyProjectVO selectOneApplyProjectInfo(ApplyProjectVO applyProjectVO);

	public ApplyProjectVO findOneApplyProjectWithoutApplyId(ApplyProjectVO applyProjectVO);
	
	/**
	 * 모든 스킬 목록
	 * @param projectSkillVO
	 * @return
	 */
	public int insertProjectSkills(ProjectSkillVO projectSkillVO);
	
	/**
	 * 모든 기술 목록 조회
	 * 
	 * @return
	 */
	public List<PrmStkVO> selectAllProjectSkillList();

	/**
	 * 모든 프로젝트의 리스트를 불러오는 쿼리문을 호출하는 메서드이다. - 의진 -
	 * @return
	 */
	public List<ProjectVO> selectProjectList(MemberVO memberVO);
	
	/**
	 * 기업회원이 발주한 모든 프로젝트의 목록을 불러오는 쿼리문을 호출하는 메소드이다. -의진-
	 * @param memberVO
	 * @return
	 */
	public List<ProjectVO> selectAllMyOrderProjectList(String cmpId);
	
	/**
	 * 작성된 지원서를 조회하는 메서드 -동원-
	 * @param searchApplyVO 이메일과 프로젝트를 멤버변수로 갖는 객체
	 * @return 작성된 지원서 정보를 담은 객체
	 */
	public ApplyProjectVO selectOneApplyProject(SearchApplyVO searchApplyVO);
	
	/**
	 * 발주자가 지원서 조회하는 메서드 -태현-
	 * @param searchApplyVO
	 * @return
	 */
	public ApplyProjectVO selectOneApplyViewInfo(SearchApplyVO searchApplyVO);
	
	public int updateProject(ProjectVO projectVO);
	
	/**
	 * 프로젝트 지원서 하나 가져오는 메서드
	 * @param pjApplyId
	 * @return
	 */
	public ApplyProjectVO selectOneApplyInfo(String pjApplyId);
	
	
	/**
	 * 특정 프로젝트 첨부자료를 전부 지우는 쿼리문을 호출하는 메서드.
	 * @param pjId
	 * @return
	 */
	public int deleteAllProjectAtt(String pjId);
	public int deleteApplyAtt(String pjApplyId);
}

