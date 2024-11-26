package com.ktdsuniversity.edu.bizmatch.file.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.file.vo.FileVO;
import com.ktdsuniversity.edu.bizmatch.file.vo.ProjectApplyFileVO;
import com.ktdsuniversity.edu.bizmatch.file.vo.ProjectFileVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberPortfolioAttVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectApplyAttVO;

public interface FileDao {
	public String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.file.dao.FileDao";
	
	/**
	 * 회원가입을 할 때 파일을 insert하는 쿼리문을 호출하는 메서드이다.
	 * @param fileVO
	 * @return
	 */
	public int insertFile(FileVO fileVO);
	
	/**
	 * 포트폴리오를 등록하는 쿼리문을 호출하는 메서드이다.
	 * @param memberPortfolioAttVO : 포트폴리오의 첨부자료 정보를 담은 객체이다.
	 * @return : insert된 컬럼의 개수이다.
	 */
	public int insertPortfolioFile(MemberPortfolioAttVO memberPortfolioAttVO);
	
	/**
	 * 해당 포트폴리오에 첨부된 파일들을 조회하는 쿼리문을 호출하는 메서드.
	 * @param mbrPrtflId : 포트폴리오 아이디.
	 * @return
	 */
	public List<MemberPortfolioAttVO> selectPortfolioFileList(String mbrPrtflId);
	
	/**
	 * 하나의 포트폴리오에 첨부된 첨부파일을 전부 지우는 쿼리문을 호출하는 메서드.
	 * @param mbrPrtflId : 포트폴리오 아이디.
	 * @return : 삭제된 컬럼의 개수.
	 */
	public int deletePortfolioAllAtt(String mbrPrtflId);
	
	/**
	 * 한 프로젝트의 첨부된 첨부파일의 개수를 구하는 쿼리문을 호출하는 메서드.
	 * @param mbrPrtflId : 포트폴리오의 아이디.
	 * @return
	 */
	public int selectPortfolioAllAttCnt(String mbrPrtflId);
  
	/**
	 * 
	 * @param projectFileVO
	 * @return
	 */
	public int insertProjectFile(ProjectFileVO projectFileVO);
	
	/**
	 * 
	 * @param projectApplyFileVO
	 * @return
	 */
	public int insertApplyProjectFile(ProjectApplyFileVO projectApplyFileVO);
	
	/**
	 * 한 지원서에 첨부된 모든 첨부파일을 가져오는 쿼리문을 호출하는 메서드.
	 * @param pjApplyId : 지원서의 아이디.
	 * @return
	 */
	public List<ProjectApplyAttVO> selectAllProjectApplyAtt(String pjApplyId);
}
