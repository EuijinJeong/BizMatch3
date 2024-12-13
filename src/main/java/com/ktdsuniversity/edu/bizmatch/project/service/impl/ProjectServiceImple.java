package com.ktdsuniversity.edu.bizmatch.project.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.bizmatch.common.beans.FileHandler;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.file.CreateNewProjectFileException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.file.FileUploadFailedException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.payment.PaymentException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectApplyFailException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectDeleteException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectScrapException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectUpdateFailException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.project.ProjectWriteFailException;
import com.ktdsuniversity.edu.bizmatch.common.vo.StoreResultVO;
import com.ktdsuniversity.edu.bizmatch.file.dao.FileDao;
import com.ktdsuniversity.edu.bizmatch.file.vo.ProjectApplyFileVO;
import com.ktdsuniversity.edu.bizmatch.file.vo.ProjectFileVO;
import com.ktdsuniversity.edu.bizmatch.member.dao.MemberDao;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.PrmStkVO;
import com.ktdsuniversity.edu.bizmatch.payment.dao.PaymentDao;
import com.ktdsuniversity.edu.bizmatch.project.dao.ProjectDao;
import com.ktdsuniversity.edu.bizmatch.project.service.ProjectService;
import com.ktdsuniversity.edu.bizmatch.project.vo.ApplyProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ModifyProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectApplyAttVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentModifyVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentPaginationVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectCommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectDateVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectIndustryVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectListVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectScrapVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectSkillVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.SearchApplyVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.SearchProjectVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.SelectApplyMemberVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.WriteProjectVO;

@Service
public class ProjectServiceImple implements ProjectService {

	@Autowired
	private FileDao fileDao;

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private FileHandler fileHandler;

	@Override
	public boolean createNewProject(WriteProjectVO writeProjectVO) throws ParseException {

		// 날짜 정보 설정하기.
		ProjectDateVO projectDateVO = new ProjectDateVO();
		projectDateVO.setStrtDt(writeProjectVO.getStrtDt());
		projectDateVO.setEndDt(writeProjectVO.getEndDt());
		projectDateVO.setPjRcrutStrtDt(writeProjectVO.getPjRcrutStrtDt());
		projectDateVO.setPjRcrutEndDt(writeProjectVO.getPjRcrutEndDt());
		
		try {
			if(!this.verifyPrjectDate(projectDateVO)) {
				throw new ProjectWriteFailException("날짜 정보가 유효하지 않습니다.", writeProjectVO);
			}
		} catch (ParseException e) {
			throw new ProjectWriteFailException("에외 ", writeProjectVO);
		}

		int insertCount = this.projectDao.insertOneProject(writeProjectVO);
		if(insertCount == 0) {
			//TODO
			throw new IllegalArgumentException("예외 잡아");
		}
		
		String pjId = writeProjectVO.getPjId();
		int deposit = (int) (writeProjectVO.getCntrctAccnt() * 0.1);
		writeProjectVO.setGrntAmt(deposit); // 보증금.
		
		int insertedPaymentCnt = this.paymentDao.isnertNewPaymentInfo(writeProjectVO);
		if (insertedPaymentCnt == 0) {
			throw new PaymentException("결제정보를 데이터베이스에 저장하면서 오류가 발생했습니다.");
		}

		// 파일 업로드 처리
		List<MultipartFile> fileList = writeProjectVO.getFileList(); // 사용자가 입력한 파일 리스트 가져옴.

		if (fileList == null || fileList.isEmpty()) {
			throw new CreateNewProjectFileException("파일첨부는 필수사항입니다.", writeProjectVO);
		}
		List<StoreResultVO> fileStoreList = this.fileHandler.storeListFile(fileList); // 파일 리스트 난독화 해서 리스트 반환해줌.
		if (fileStoreList == null || fileStoreList.isEmpty()) {
			throw new CreateNewProjectFileException("서버상의 이유로 파일 저장에 실패했습니다.", writeProjectVO);
		}

		int totalInsertedFiles = 0;

		for (StoreResultVO fileResult : fileStoreList) {
			ProjectFileVO projectFileVO = new ProjectFileVO();
			String originfileName = fileResult.getOriginFileName(); // 원본 파일 이름.
			String obfuscatedFileName = fileResult.getObfuscatedFileName(); // 난독화한 파일 이름.
			projectFileVO.setPjAttUrl(originfileName);
			projectFileVO.setPjAttUrlNonread(obfuscatedFileName);
			projectFileVO.setEmilAddr(writeProjectVO.getEmilAddr());
			projectFileVO.setPjId(pjId);

			// 파일을 insert함.
			int insertFileCnt = this.fileDao.insertProjectFile(projectFileVO);

			if (insertFileCnt == 0) {
				throw new ProjectWriteFailException("시스템 오류로 파일 처리가 불가능합니다. 다시 시도해 주세요.", writeProjectVO);
			}
			totalInsertedFiles++;
		}

//		ProjectSkillVO projectSkillVO = new ProjectSkillVO();
//		// 프로젝트 스킬정보를 데이터베이스에 저장한다.
//		for (String id : skillList) {
//			projectSkillVO.setPjId(pjId);
//			projectSkillVO.setPrmStkId(id);
//			int insertedCnt = this.projectDao.insertProjectSkills(projectSkillVO);
//			if (insertedCnt == 0) {
//				throw new IllegalArgumentException("프로젝트 주요 기술들을 서버에 저장하는 도중 오류가 발생했습니다.");
//			}
//		}
		
		// 프로젝트 산업군 집합 테이블에 입력 받은 값을 넣는 추가 작업 
		
		ProjectIndustryVO projectIndustryVO= new ProjectIndustryVO();
		
		projectIndustryVO.setMjrId(writeProjectVO.getFirstIndstrId());
		projectIndustryVO.setSmjrId(writeProjectVO.getSecondIndstrId());
		projectIndustryVO.setPjId(writeProjectVO.getPjId());
		
		
		return this.projectDao.insertNewIndustryGroup(projectIndustryVO)>0;
	}
	
			

	@Override
	public boolean updateOneProject(ModifyProjectVO modifyProjectVO) {
		int updateCount = this.projectDao.updateOneProject(modifyProjectVO);
		int updateIndustryCount = this.projectDao.updateProjectIndustry(modifyProjectVO.getProjectIndustryVO());
		
//		if(updateCount == 0 || updateIndustryCount == 0) {
//			throw new ProjectWriteFailException("서버상의 문제로 정보 수정이 불가능합니다.", modifyProjectVO);
//		}
		
		return updateCount > 0;
	}

	@Override
	public boolean deleteOneProject(String pjId) {
		int deleteCount = this.projectDao.deleteOneProject(pjId);
		// TODO: 해당 프로젝트와 관련된 파일들도 삭제해야함.
		if(deleteCount == 0) {
			throw new ProjectDeleteException("서버상의 이유로 삭제 중 오류가 발생했습니다.", pjId);
		}
		return deleteCount > 0;
	}

	@Override
	public ProjectListVO selectAllCardProject(SearchProjectVO searchProjectVO) {

		int projectCount = this.projectDao.selectProjectAllCount(searchProjectVO);
		

		if (projectCount == 0) {
			ProjectListVO projectListVO = new ProjectListVO();
			projectListVO.setProjectCnt(0);
			projectListVO.setProjectList(new ArrayList<>());

			return projectListVO;

		}

		List<ProjectVO> projectList = null;
		if (searchProjectVO == null) {
			projectList = this.projectDao.selectAllCardProject();
		} else {
			projectList = this.projectDao.selectAllCardProject(searchProjectVO);
		}

		ProjectListVO projectListVO = new ProjectListVO();
		projectListVO.setProjectCnt(projectCount);
		projectListVO.setProjectList(projectList);

		return projectListVO;
	}

	@Override
	public boolean createNewProjectApply(ApplyProjectVO applyProjectVO) {

		int insertCount = this.projectDao.insertOneProjectApply(applyProjectVO);
		// 파일 업로드 처리
		List<MultipartFile> fileList = applyProjectVO.getFileList(); // 사용자가 입력한 파일 리스트 가져옴.

		if (fileList == null || fileList.isEmpty()) {
			throw new FileUploadFailedException("업로드할 파일이 없습니다.");
		}
		List<StoreResultVO> fileStoreList = this.fileHandler.storeListFile(fileList); // 파일 리스트 난독화 해서 리스트 반환해줌.
		
		if (fileStoreList == null || fileStoreList.isEmpty()) {
			throw new FileUploadFailedException("파일 저장에 실패했습니다.");
		}

		int totalInsertedFiles = 0;

		for (StoreResultVO fileResult : fileStoreList) {
			ProjectApplyFileVO projectApplyFileVO = new ProjectApplyFileVO();
			String originfileName = fileResult.getOriginFileName(); // 원본 파일 이름.
			String obfuscatedFileName = fileResult.getObfuscatedFileName(); // 난독화한 파일 이름.
			projectApplyFileVO.setPjApplyAttUrl(originfileName);
			projectApplyFileVO.setPjApplyAttUrlNoneread(obfuscatedFileName);
			projectApplyFileVO.setEmilAddr(applyProjectVO.getEmilAddr());
			projectApplyFileVO.setPjApplyId(applyProjectVO.getPjApplyId());
			
		

			// 파일을 insert함.

			int insertFileCnt = this.fileDao.insertApplyProjectFile(projectApplyFileVO);
//			if (insertFileCnt == 0) {
//				throw new ProjectWriteFailException("시스템 오류로 파일 처리가 불가능합니다. 다시 시도해 주세요.", applyProjectVO);
//			}

			totalInsertedFiles++;
		}

		return insertCount > 0 && totalInsertedFiles > 0;
	}


	@Override
	public ProjectListVO selectAllCardProjectSorted(String orderBy) {

		List<ProjectVO> projects = projectDao.selectAllCardProjectSorted(orderBy);

		ProjectListVO projectList = new ProjectListVO();
		projectList.setProjectList(projects);

		return projectList; 
	}

	@Override
	public List<ProjectVO> selectForPagination(SearchProjectVO searchProjectVO) {

		List<ProjectVO> projectList = null;

		if (searchProjectVO == null) {
			projectList = this.projectDao.selectAllCardProject();
		} else {
			projectList = this.projectDao.selectForPagination(searchProjectVO);
		}
		
		for (ProjectVO projectVO : projectList) {
			String pjId = projectVO.getPjId();
			List<ProjectSkillVO> skillList = this.projectDao.selectAllProjectSkill(pjId);
			List<ProjectSkillVO> minimizeSkillList = new ArrayList<>();
			
			// 만약에 기술 리스트가 2보다 큼.
			if(skillList.size() > 2) {
				for(int i = 0; i< 2; i++) {
					minimizeSkillList.add(i, skillList.get(i));
				}
			}
			projectVO.setProjectSkillList(minimizeSkillList);
		}
		return projectList;
	}

	@Transactional
	@Override
	public ProjectVO readOneProjectInfo(String pjId) {
		// 해당 프로젝트에 대한 정보를 가져와야함.
		ProjectVO projectVO = this.projectDao.selectProjectInfo(pjId);
		this.projectDao.updateProjectViewCnt(pjId);
		return projectVO;
	}

	/**
	 * 이하 댓글 관련 Service 메서드
	 */
	@Override
	public boolean updateDeleteCommentState(String id) {
		return this.projectDao.deleteOneComment(id)>0;
	}

	@Override
	public List<ProjectCommentVO> getPaginationComment(ProjectCommentPaginationVO projectCommentPaginationVO, String id) {
		List<ProjectCommentVO> result;
		if(projectCommentPaginationVO==null) {
			result =  this.projectDao.selectAllComment(id);
		}
		else {
			result = this.projectDao.selectPaginationComment(projectCommentPaginationVO);
		}
		
		return result;
	};

	@Override
	public boolean createNewComment(ProjectCommentWriteVO projectCommentWriteVO) {
		return this.projectDao.insertNewComment(projectCommentWriteVO)>0;
	}

	@Override
	public boolean modifyComment(ProjectCommentModifyVO projectCommentModifyVO) {
		return this.projectDao.updateOneComment(projectCommentModifyVO)>0;
	}

	@Override
	public List<ProjectCommentVO> getAllComment(String id) {
		List<ProjectCommentVO> result = this.projectDao.selectAllComment(id);
		return result ;
	}

	@Override
	public boolean updateProjectViewCnt(String pjId) {
		int updateCnt = this.projectDao.updateProjectViewCnt(pjId);
		return updateCnt > 0;
	}
	
	@Override
	public boolean updateAddtionalRecruitment(ModifyProjectVO modifyProjectVO) {
		// 기본적인 정보 수정.
		int updateCnt = this.projectDao.updateAddtionalRecruitment(modifyProjectVO);
		// 산업정보 수정.
		int updateIndustryCnt = this.projectDao.updateProjectIndustry(modifyProjectVO.getProjectIndustryVO());
		
		if(updateCnt == 0 || updateIndustryCnt == 0) {
			throw new ProjectUpdateFailException("서버상의 이유로 정보 수정이 불가능합니다.", modifyProjectVO);
		}
		return true;
	}
	
	@Override
	public boolean updateProjectApply(@ModelAttribute ApplyProjectVO applyProjectVO) {
		int updateCnt = this.projectDao.updateProjectApply(applyProjectVO);
		if(updateCnt == 0) {
			throw new ProjectApplyFailException("프로젝트 지원서를 수정하는 중 서버에서 오류가 발생했습니다.", applyProjectVO);
		}
		return updateCnt > 0;
	}
	
	@Override
	public void deleteProjectApply(ApplyProjectVO applyProjectVO) {
		int deleteCnt = this.projectDao.deleteProjectApply(applyProjectVO);
		if(deleteCnt == 0) {
			throw new ProjectDeleteException("지원서를 삭제하는 중 서버상의 이유로 오류가 발생했습니다.", applyProjectVO.getPjId());
		}
	}

	@Override
	public List<ApplyProjectVO> readAllApplyMember(String pjId, String email) {
		ProjectVO projectVO = this.projectDao.selectProjectInfo(pjId);
		
		// if(!projectVO.getOrdrId().equals(memberVO.getEmilAddr())){
		// 	throw new IllegalArgumentException("정보가 일치하지 않습니다.");
		// }
		if(!(projectVO.getObtnId() == null)) {
			// TODO 지원자 선정 완료 오류 고쳐야함.
			throw new IllegalArgumentException("지원자 선정을 완료하였습니다.");
		}
		
		return this.projectDao.selectAllApplyMember(pjId);
	}

	@Override
	public boolean updateApplyMember(SelectApplyMemberVO selectApplyMemberVO, String email) {
		MemberVO memberVO = memberDao.selectOneMember(email);
		ProjectVO projectVO = this.projectDao.selectProjectInfo(selectApplyMemberVO.getPjId());
		
		if(!projectVO.getOrdrId().equals(memberVO.getEmilAddr())){
			throw new IllegalArgumentException("정보가 일치하지 않습니다.");
		}
		if(projectVO.getObtnId()!=null) {
			throw new IllegalArgumentException("지원자 선정을 완료하였습니다.");
		}
		
		return this.projectDao.updateProjectApplyMember(selectApplyMemberVO) > 0;
	}
	
	/**
	 * 날짜 관련해서 검증을 수행하는 메서드이다.
	 * @param projectDateVO
	 * @return
	 * @throws ParseException 
	 */
	private boolean verifyPrjectDate(ProjectDateVO projectDateVO) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		// 프로젝트 일정 시작일 일정 마감일.
		String startDt = projectDateVO.getStrtDt();
		String endDt = projectDateVO.getEndDt();
		Date startDate = format.parse(startDt);
		Date endDate = format.parse(endDt);
		int compare = endDate.compareTo(startDate);
		if(compare <= 0) {
			return false;
		}
		
		// 프로젝트 모집 일정 시작일 마감일.
		String recuritStartDt = projectDateVO.getPjRcrutStrtDt();
		String recuritEndDt = projectDateVO.getPjRcrutEndDt();
		Date recuritStartDate = format.parse(recuritStartDt);
		Date recuritEndDate = format.parse(recuritEndDt);
		compare = recuritEndDate.compareTo(recuritStartDate);
		
		if(compare <= 0) {
			return false;
		}
		return true;
	}
	
	@Override
	public List<ProjectSkillVO> readAllProjectSkill(String pjId) {
		return this.projectDao.selectAllProjectSkill(pjId);
	}

	@Override
	public List<ApplyProjectVO> readAllApply(String email) {
		MemberVO memberVO = this.memberDao.selectOneMember(email);
		return this.projectDao.selectAllApply(memberVO);
	}

	@Override
	public void insertProjectScrap(ProjectScrapVO projectScrapVO) {
		int isInserted = this.projectDao.insertProjectScrap(projectScrapVO);
		if(isInserted == 0) {
			throw new ProjectScrapException("프로젝트 정보를 스크랩하는 중 오류가 발생했습니다." , projectScrapVO);
		}
	}

	@Override
	public List<ProjectVO> readAllProjectOrderRecipient(MemberVO memberVO) {
		return this.projectDao.selectAllProjectOrderRecipient(memberVO);
	}

	@Override
	public List<PrmStkVO> selectAllProjectSkillList() {
		return this.projectDao.selectAllProjectSkillList();
	}

	@Override
	public ApplyProjectVO readOneApplyProject(String pjId) {
		ApplyProjectVO applyProjectVO = new ApplyProjectVO();
		applyProjectVO.setPjId(pjId); // pjId 설정
	

		applyProjectVO = this.projectDao.selectOneApplyProjectInfo(applyProjectVO);
		List<ProjectApplyAttVO> projectApplyAttList = this.fileDao
				.selectAllProjectApplyAtt(applyProjectVO.getPjApplyId());
		applyProjectVO.setProjectApplyAttVOList(projectApplyAttList);

		return applyProjectVO;
	}

	@Override
	public ApplyProjectVO findOneApplyProjectWithoutApplyId(ApplyProjectVO applyProjectVO) {
		ApplyProjectVO applyProjectVO2 = this.projectDao.findOneApplyProjectWithoutApplyId(applyProjectVO);
		List<ProjectApplyAttVO> projectApplyAttList = this.fileDao.selectAllProjectApplyAtt(applyProjectVO2.getPjApplyId());
		applyProjectVO2.setProjectApplyAttVOList(projectApplyAttList);
		
		return applyProjectVO2;
	}

	@Override
	public List<ProjectVO> readAllProjectList(MemberVO memberVO) {
		List<ProjectVO> projectList = this.projectDao.selectProjectList(memberVO);
		return projectList;
	}

	@Override
	public List<ProjectVO> readAllMyOrderProjectList(String email) {
		MemberVO memberVO = this.memberDao.selectOneMember(email);
		if(memberVO == null) {
			throw new IllegalArgumentException("해당 회원이 존재하지 않습니다.");
		}
		List<ProjectVO> projectList = this.projectDao.selectAllMyOrderProjectList(memberVO.getCmpId());
		return projectList;
	}
	
	@Override
	public ApplyProjectVO selectOneApplyProject(SearchApplyVO searchApplyVO) {
		return this.projectDao.selectOneApplyProject(searchApplyVO);
	}

	@Override
	public ApplyProjectVO selectOneApplyViewInfo(SearchApplyVO searchApplyVO) {
		
		return this.projectDao.selectOneApplyViewInfo(searchApplyVO);
	}

	@Override
	public boolean updateProject(ProjectVO projectVO) {
		int updateCount = this.projectDao.updateProject(projectVO);
		return updateCount > 0;
	}



	@Override
	public ApplyProjectVO selectOneApplyInfo(String pjApplyId) {
		return this.projectDao.selectOneApplyInfo(pjApplyId);
	}
}
