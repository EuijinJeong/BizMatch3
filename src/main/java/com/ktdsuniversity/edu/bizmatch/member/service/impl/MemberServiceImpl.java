package com.ktdsuniversity.edu.bizmatch.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.bizmatch.accesslog.dao.AccessLogDao;
import com.ktdsuniversity.edu.bizmatch.accesslog.vo.AccessLogVO;
import com.ktdsuniversity.edu.bizmatch.admin.member.dao.AdminMemberDao;
import com.ktdsuniversity.edu.bizmatch.common.beans.FileHandler;
import com.ktdsuniversity.edu.bizmatch.common.beans.Sha;
import com.ktdsuniversity.edu.bizmatch.common.category.vo.CategoryVO;
import com.ktdsuniversity.edu.bizmatch.common.email.service.EmailService;
import com.ktdsuniversity.edu.bizmatch.common.email.vo.UserEmailAuthNumVO;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.LoginFailException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.MemberPortfolioException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.SignUpCompanyException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.SignUpFailException;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.member.SignUpNotApprovedException;
import com.ktdsuniversity.edu.bizmatch.common.skills.dao.MbrPrmStkDao;
import com.ktdsuniversity.edu.bizmatch.common.skills.vo.MbrPrmStkVO;
import com.ktdsuniversity.edu.bizmatch.common.utils.RequestUtil;
import com.ktdsuniversity.edu.bizmatch.common.vo.StoreResultVO;
import com.ktdsuniversity.edu.bizmatch.file.dao.FileDao;
import com.ktdsuniversity.edu.bizmatch.file.vo.FileVO;
import com.ktdsuniversity.edu.bizmatch.member.dao.MemberDao;
import com.ktdsuniversity.edu.bizmatch.member.service.MemberService;
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

@Service
public class MemberServiceImpl implements MemberService {

	public static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	private AccessLogDao accessLogDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private FileDao fileDao;
	
	@Autowired
	private MbrPrmStkDao mbrPrmStkDao;
	
	@Autowired
	private AdminMemberDao adminMemberDao;

	@Autowired
	private EmailService emailService;

	@Autowired
	private Sha sha;

	@Autowired
	private FileHandler fileHandler;
	
	@Override
	public boolean resetMemberPwd(MemberResetPwdVO memberResetPwdVO) {
		
		String salt = this.sha.generateSalt();
		String saltedPwd = this.sha.getEncrypt(memberResetPwdVO.getPwd(), salt);
		memberResetPwdVO.setPwd(saltedPwd);
		memberResetPwdVO.setSalt(saltedPwd);
		
		int updatedCnt = this.memberDao.updateOneMember(memberResetPwdVO);
		return updatedCnt > 0;
	}

	@Transactional
	@Override
	public MemberVO isVarifiedMemberLogin(MemberLoginVO memberLoginVO) {
		// 회원가입 대기 상태인지 확인.
		MemberVO adminMemberVO = this.adminMemberDao.selectOneMember(memberLoginVO.getEmilAddr());
		if(adminMemberVO == null) {
			throw new LoginFailException("해당 회원정보가 존재하지 않습니다.");
		}
		
		if(adminMemberVO.getMbrStt() == 0) {	
			throw new SignUpNotApprovedException("회원심사중 로그인 불가.");
		}
		
		boolean loginCnt = this.accessLogDao.selectLoginFailCount(RequestUtil.getIp()) >= 5;
		if (loginCnt) {
			// 로그인을 한시간 이내에 5번 이상 실패한 IP
			throw new LoginFailException("이메일 또는 비밀번호가 일치하지않습니다.");
		}
		String salt = this.memberDao.selectSalt(memberLoginVO.getEmilAddr());

		if (salt == null) {
			// 이메일이 일치하지 않는 사람
			AccessLogVO accessLogVO = new AccessLogVO();
			accessLogVO.setAccessMethod(RequestUtil.getRequest().getMethod().toUpperCase());
			accessLogVO.setAccessEmail(memberLoginVO.getEmilAddr());
			accessLogVO.setAccessIp(RequestUtil.getIp());
			accessLogVO.setAccessUrl(RequestUtil.getRequest().getRequestURI());
			accessLogVO.setAccessType("LOGIN");

			this.accessLogDao.insertNewAccessLog(accessLogVO);

			throw new LoginFailException("이메일 또는 비밀번호가 일치하지 않습니다.");
		}
		memberLoginVO.setPwd(sha.getEncrypt(memberLoginVO.getPwd(), salt));

		MemberVO memberVO = this.memberDao.selectOneMember(memberLoginVO);

		// 찾고자 했던 사용자가 존재하지 않는 경우.
		if (memberVO == null) {
			// 비밀번호가 일치하지 않는 사람 / 해당 이메일에 로그인 실패 상태를 업데이트
			memberLoginVO.setIp(RequestUtil.getIp());
			this.memberDao.updateLoginFailCnt(memberLoginVO);

			throw new LoginFailException("이메일 또는 비밀번호가 일치하지않습니다.");
		}
		// 이메일 또는 비밀번호를 5회 이상 틀린지 확인
		boolean isBlock = this.memberDao.selectLoginFailCnt(memberLoginVO) > 0;

		if (isBlock) {
			// 5회 이상 틀려서 한시간이내에는 로그인 불가
			throw new LoginFailException("로그인 시도 횟수 시간 초과.");
		}
		// 로그인 성공
		memberLoginVO.setIp(RequestUtil.getIp());
		this.memberDao.updateLoginSuccessState(memberLoginVO);

		AccessLogVO accessLogVO = new AccessLogVO();
		accessLogVO.setAccessType("LOGIN");
		accessLogVO.setAccessEmail(memberLoginVO.getEmilAddr());
		accessLogVO.setAccessUrl(RequestUtil.getRequest().getRequestURI());
		accessLogVO.setAccessMethod(RequestUtil.getRequest().getMethod().toUpperCase());
		accessLogVO.setAccessIp(RequestUtil.getIp());
		accessLogVO.setLoginSuccessYn("Y");
		accessLogDao.insertNewAccessLog(accessLogVO);

		return memberVO;
	}

	@Override
 	public CompanyVO readOneCompany(String cmpnyBrn) {
 		CompanyVO companyVO = this.memberDao.selectOneCompany(cmpnyBrn);
 		return companyVO;
 	}
	
	@Transactional
	@Override
	public boolean signupCompanyMember(MemberCompanySignUpVO memberCompanySignUpVO) {

		String salt = this.sha.generateSalt();
		memberCompanySignUpVO.setSalt(salt);
		memberCompanySignUpVO.setPwd(sha.getEncrypt(memberCompanySignUpVO.getPwd(), salt));
		String brn = memberCompanySignUpVO.getCmpnyBrn();
		
		if(brn.indexOf(4)=='8') {
			memberCompanySignUpVO.setCmpnyBizCtgry("0");
		} else {
			memberCompanySignUpVO.setCmpnyBizCtgry("1");
		}

		memberCompanySignUpVO.setMbrCtgry(0);

		// selectOneCompany 존재하는지 먼저 확인
		CompanyVO companyVO = this.memberDao.selectOneCompany(memberCompanySignUpVO.getCmpnyBrn());
		if (companyVO == null) {
			// 회사가 존재하지 않는다면 회사 정보 또한 새로 등록한다.
			memberCompanySignUpVO.setCmpnyRp(1); //기업 대표자로 등록
			int insertedCompanyInfo = this.memberDao.insertOneMemberCompany(memberCompanySignUpVO);
			if (insertedCompanyInfo == 0) {
				throw new SignUpCompanyException("회원가입 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.", memberCompanySignUpVO);
			}
		}

		List<MultipartFile> fileList = memberCompanySignUpVO.getFileList(); // 사용자가 입력한 파일 리스트 가져옴.
		List<StoreResultVO> fileStoreList = this.fileHandler.storeListFile(fileList); // 파일 리스트 난독화 해서 리스트 반환해줌.

		FileVO fileVO = new FileVO();

		for (StoreResultVO fileResult : fileStoreList) {
			String originFileName = fileResult.getOriginFileName(); // 원본 파일 이름.
			String obfuscatedFileName = fileResult.getObfuscatedFileName(); // 난독화한 파일 이름.
			fileVO.setAttUrl(originFileName);
			fileVO.setAttUrlNonread(obfuscatedFileName);
			fileVO.setEmilAddr(memberCompanySignUpVO.getEmilAddr());

			// 파일을 insert함.
			int insertFileCnt = this.fileDao.insertFile(fileVO);

			if (insertFileCnt == 0) {
				throw new SignUpCompanyException("시스템 오류로 파일 처리가 불가능합니다. 다시 시도해주세요.", memberCompanySignUpVO);
			}
		}
		
		UserEmailAuthNumVO userEmailAuthNumVO = new UserEmailAuthNumVO();
		userEmailAuthNumVO.setEmilAddr(memberCompanySignUpVO.getEmilAddr());
		userEmailAuthNumVO.setEmilAddrCnfrmNmbr(memberCompanySignUpVO.getEmilAddrCnfrmNmbr());
		
		memberCompanySignUpVO.getCmpnyIntrstdIndstrId().setEmilAddr(memberCompanySignUpVO.getEmilAddr());
		this.memberDao.insertOneIndustryInfo(memberCompanySignUpVO.getCmpnyIntrstdIndstrId());
		
		boolean isSuccess = this.emailService.isSameTempEmailAuthNum(userEmailAuthNumVO);
		if(!isSuccess) {
			throw new SignUpCompanyException("인증번호가 일치하지 않습니다.", memberCompanySignUpVO);
		}
		this.emailService.deleteTempEmailAuthNum(memberCompanySignUpVO.getEmilAddr());

		// 회사가 존재한다면, 회사 정보는 새로 등록하지 않는다.
		int insertedCount = this.memberDao.insertOneMember(memberCompanySignUpVO);

		return insertedCount > 0;
	}
	
	
	@Transactional
	@Override
	public boolean signupFreelancerMember(MemberSignUpVO memberSignUpVO, CategoryVO categoryVO) {
		
		// 비밀번호 암호화 해야함.
		String salt = this.sha.generateSalt();
		String saltedPwd = this.sha.getEncrypt(memberSignUpVO.getPwd(), salt);
		memberSignUpVO.setPwd(saltedPwd);
		memberSignUpVO.setSalt(salt);
		memberSignUpVO.setMbrStt(0);
		memberSignUpVO.setMbrCtgry(1);
		
		// 사용자가 입력한 파일 리스트 저장해야함.
			// 1. 파일 이름 난독화 해야함.
			// 2. 원래 이름 난독화한 파일 이름 둘 다 데이터베이스 컬럼에 저장해야함.
		
		List<MultipartFile> fileList = memberSignUpVO.getFileList(); // 사용자가 입력한 파일 리스트 가져옴.
		List<StoreResultVO> fileStoreList  = this.fileHandler.storeListFile(fileList); // 파일 리스트 난독화 해서 리스트 반환해줌.
		
		FileVO fileVO = new FileVO();
		for(StoreResultVO fileResult : fileStoreList) {
			String originFileName = fileResult.getOriginFileName(); // 원본 파일 이름.
			String obfuscatedFileName = fileResult.getObfuscatedFileName(); // 난독화한 파일 이름.
			fileVO.setAttUrl(originFileName);
			fileVO.setAttUrlNonread(obfuscatedFileName);
			fileVO.setEmilAddr(memberSignUpVO.getEmilAddr());
		
			
			int insertFileCnt = this.fileDao.insertFile(fileVO);
			if(insertFileCnt == 0) {
				throw new SignUpFailException("시스템 오류로 파일 처리가 불가능합니다. 다시 시도해주세요.", memberSignUpVO);
			}
		}

		UserEmailAuthNumVO userEmailAuthNumVO = new UserEmailAuthNumVO();
		userEmailAuthNumVO.setEmilAddr(memberSignUpVO.getEmilAddr());
		userEmailAuthNumVO.setEmilAddrCnfrmNmbr(memberSignUpVO.getEmilAddrCnfrmNmbr());

		// insert하기 전에 인증번호 값이 다르다면 회원가입 못하게 막아야함.
		if(!this.emailService.isSameTempEmailAuthNum(userEmailAuthNumVO)) {
			throw new SignUpFailException("인증번호가 일치하지 않아 회원가입이 불가능합니다. 인증번호 확인 후 재입력 해주세요.", memberSignUpVO);
		}
		
		// 회원가입을 수행하는 쿼리문을 호출시킨다.
		int insertedCnt = this.memberDao.insertOneMemberFreelancer(memberSignUpVO);
		categoryVO.setEmilAddr(memberSignUpVO.getEmilAddr());
		int insertIndustryCnt = this.memberDao.insertOneIndustryInfo(categoryVO);
		if(insertedCnt == 0 && insertIndustryCnt == 0) {
			throw new SignUpFailException("시스템 오류로 회원가입이 불가능합니다. 잠시 후 다시 시도해주세요.", memberSignUpVO);
		}
		
		// 임시번호 지워야함.
		this.emailService.deleteTempEmailAuthNum(memberSignUpVO.getEmilAddr());
		return true;
	}

	@Override
	public boolean isDuplicatedEmail(String email) {
		int emailCnt = this.memberDao.selectEmailCnt(email);

		// 이미 사용중인 이메일이 존재한다면,
		if (emailCnt > 0) {
			return false;
		}
		return emailCnt == 0;
	}
	
	@Override
	public boolean sendFindPwdEmail(String email) {
		// 사용자가 입력한 이메일이 우리 회원 데베에 있는지 먼저 조회
		MemberVO memberVO =  this.memberDao.selectOneMember(email);
		
		String findEmail = memberVO.getEmilAddr();
		
		// 사용자가 입력한 이메일에 해당하는 회원 정보가 존재하지 않는 경우.
		if(findEmail == null) {
			return false;
		}
		// 존재하는 경우.
		this.emailService.sendEmailForFindPwd(email);
		return true;
	}

	

	@Override
	public boolean deactivateMember(String email) {
		int rows = this.memberDao.updateMemberDeactivate(email);
		if (rows < 1) {
			throw new IllegalArgumentException("비활성화 상태");
		}
		return true;
	}

	@Override
	public List<PrmStkVO> selectAllSkills() {
		return this.memberDao.selectAllSkills();
	}

	@Override
	public List<MbrPrmStkVO> selectMemberSkills(String mbrId) {
		return this.mbrPrmStkDao.selectMbrSkill(mbrId);
	}

	@Transactional
	@Override
	public boolean insertMemberSkills(List<PrmStkVO> prmStkVOList, MemberVO memberVO) {
//		현재 보유하는 기술중에서 새로 추가한 기술이 있으면 제외해줘야함
		// 사용자의 보유 기술을 조회함.
		List<MbrPrmStkVO> memberStkList= this.mbrPrmStkDao.selectMbrSkill(memberVO.getEmilAddr());
		
		// 회원이 가지고 있는 보유기술 안에서.
		for (MbrPrmStkVO mbrPrmStkVO : memberStkList) {
			String prmStkId = mbrPrmStkVO.getPrmStkId();
			// 사용자가 입력한 보유기술을 돌며 검색을 한다.
			for (PrmStkVO prmStkVO : prmStkVOList) {
				String inputPrmStkId = prmStkVO.getPrmStkId();
				// 만약 사용자가 가지고 있는 보유기술 중 새롭게 입력을 한 보유기술이 존재하지 않는다면,
				if(!prmStkId.equals(inputPrmStkId)) {
					// 추가를 해줘야함.
					MbrPrmStkVO tempMbrPrmStkVO = new MbrPrmStkVO();
					tempMbrPrmStkVO.setEmilAddr(memberVO.getEmilAddr());
					tempMbrPrmStkVO.setPrmStkId(prmStkId);
					int insertCnt = this.mbrPrmStkDao.insertMbrSkill(mbrPrmStkVO);
					if(insertCnt == 0) {
						throw new IllegalArgumentException("서버상의 이유로 새로운 기술 스킬을 저장할 수 없습니다.");
					}
				}
			}
		}
		return true;
	}

	@Transactional
	@Override
	public boolean deleteMemberSkills(MbrPrmStkVO mbrPrmStkVO) {
		return this.mbrPrmStkDao.deleteMbrSkill(mbrPrmStkVO.getMbrPrmStkId()) > 0;
		
	}

	@Transactional
	@Override
	public boolean insertNewSkills(PrmStkVO prmStkVO) {
		int insertCount = memberDao.insertNewSkills(prmStkVO);
		return insertCount > 0;
	}

	@Override
	public List<MemberPortfolioVO> selectAllPortfolios(String mbrId) {
		List<MemberPortfolioVO> portfolioList = new ArrayList<>();
		portfolioList = this.memberDao.selectAllPortfolios(mbrId);
		for (MemberPortfolioVO memberPortfolioVO : portfolioList) {
			List<MemberPortfolioAttVO> attList = this.selectAllPortfolioAtt(memberPortfolioVO.getMbrPrtflId());
			memberPortfolioVO.setAttVOs(attList);
		}
		return portfolioList;
	}
	@Override
	public List<MemberPortfolioVO> selectAllCmpnyPortfolios(String cmpId){
		List<MemberPortfolioVO> portfolioList = new ArrayList<>();
		portfolioList = this.memberDao.selectAllCmpnyPortfolios(cmpId);
		for (MemberPortfolioVO memberPortfolioVO : portfolioList) {
			List<MemberPortfolioAttVO> attList = this.selectAllPortfolioAtt(memberPortfolioVO.getMbrPrtflId());
			memberPortfolioVO.setAttVOs(attList);
		}
		return portfolioList;
	}

	@Override
	public MemberPortfolioVO selectOnePortfolio(String mbrPrtflId) {
		MemberPortfolioVO memberPortfolioVO = new MemberPortfolioVO();
		memberPortfolioVO = this.memberDao.selectOnePortfolio(mbrPrtflId);
		List<MemberPortfolioAttVO> attList = this.fileDao.selectPortfolioFileList(mbrPrtflId);
		memberPortfolioVO.setAttVOs(attList);
		
		return memberPortfolioVO;
	}

	@Transactional
	@Override
	public boolean createNewPortfolio(MemberPortfolioVO memberPortfolioVO) {
		
		// 새로운 포트폴리오를 등록함.
		int insertCnt = this.memberDao.insertNewPortfolio(memberPortfolioVO);
		if(insertCnt == 0) {
			throw new MemberPortfolioException("새로운 포트폴리오를 등록하는 과정에서 오류가 발생했습니다.");
		}
		// 회원이 첨부한 파일 리스트를 가져옴.
		List<MultipartFile> attList = memberPortfolioVO.getAttList();
		List<StoreResultVO> storeList = new ArrayList<>();
		
		// 저장된 원본 이름과 난독화된 이름의 리스트를 얻는다.
		storeList = this.fileHandler.storeListFile(attList);
		MemberPortfolioAttVO memberPortfolioAttVO = new MemberPortfolioAttVO();
		
		// 데이터베이스에 난독화,원본파일,포트폴리의 게시글 아이디를 저장한다.
		for (StoreResultVO storeResultVO : storeList) {
			String originFileName = storeResultVO.getOriginFileName();
			String nonReadFileName = storeResultVO.getObfuscatedFileName();
			
			memberPortfolioAttVO.setAttUrl(originFileName);
			memberPortfolioAttVO.setAttUrlNonread(nonReadFileName);
			memberPortfolioAttVO.setMbrPrtflId(memberPortfolioVO.getMbrPrtflId());
			int insertFileCnt = this.fileDao.insertPortfolioFile(memberPortfolioAttVO);
			if(insertFileCnt == 0) {
				throw new MemberPortfolioException("첨부파일을 서버에 저장하는 과정중 오류가 발생했습니다.");
			}
		}
		return true;
	}

	@Override
	public boolean updateOnePortfolio(MemberPortfolioVO memberPortfolioVO) {
		int updateCnt = this.memberDao.updateOnePortfolio(memberPortfolioVO);
		if(updateCnt == 0) {
			throw new MemberPortfolioException("포트폴리오 게시글을 수정하는 중 서버상의 이유로 에러가 발생했습니다.");
		}
		return updateCnt > 0;
	}

	@Transactional
	@Override
	public boolean deleteOnePortfolio(String portfolioId) {
		int attCnt = this.fileDao.selectPortfolioAllAttCnt(portfolioId);
		int deleteAttCnt = this.fileDao.deletePortfolioAllAtt(portfolioId);
		if(attCnt != deleteAttCnt) {
			throw new MemberPortfolioException("포트폴리오 첨부파일을 삭제하는 중 서버에서 오류가 발생했습니다.");
		}
		int deleteCnt = this.memberDao.deleteOnePortfolio(portfolioId);
		if(deleteCnt == 0) {
			throw new MemberPortfolioException("포트폴리오를 서버에서 삭제하는 중 오류가 발생했습니다.");
		}
		return deleteCnt > 0;
	}

	@Override
	public CompanyVO selectOneCompanyByEmilAddr(String cmpnyId) {
		return this.memberDao.selectOneCompanyByEmilAddr(cmpnyId);
	}
	
	@Override
	public List<MbrPrmStkVO> selectMbrPrmStkList(String emilAddr) {
		return this.mbrPrmStkDao.selectMbrSkill(emilAddr);
	}

	@Override
	public List<ReviewVO> selectReviewList(String emilAddr) {
		List<ReviewVO> reviewList = this.memberDao.selectReviewList(emilAddr);
		return reviewList;
	}

	@Override
	public List<ReviewVO> selectReviewListBySrcDesc(String emilAddr) {
		List<ReviewVO> reviewList = this.memberDao.selectReviewListByScrDesc(emilAddr);
		return reviewList;
	}

	@Override
	public List<ReviewVO> selectReviewListBySrcAsc(String emilAddr) {
		List<ReviewVO> reviewList = this.memberDao.selectReviewListByScrAsc(emilAddr);
		return reviewList;
	}
	
	@Override
	public MemberMyPageIndsryVO selectMbrLkIndstr(String cmpId) {
		return this.memberDao.selectMbrLkIndstr(cmpId);
	}
	public MemberMyPageIndsryVO readMbrIndstr(String cmpId) {
		return this.memberDao.selectMbrIndsty(cmpId);
	}
	
	@Override
	public List<MemberPortfolioAttVO> selectAllPortfolioAtt(String mbrPrtflId) {
		List<MemberPortfolioAttVO> attList = this.fileDao.selectPortfolioFileList(mbrPrtflId);
		return attList;
	}

	@Transactional
	@Override
	public boolean updateCompanyMemberMyPage(MemberCompanyModifyVO memberCompanyModifyVO, MemberVO memberVO) {
		//회원이 기업 회원인지 -> 기업회원이라면 해당 회사의 수정권한을 가졌는지 
		if(memberVO.getCmpnyRp()!=1) {
			throw new IllegalArgumentException("수정할 권한이 없음");
		}
		if(! memberVO.getCmpId().equals(memberCompanyModifyVO.getCmpnyId()) ) {
			throw new IllegalArgumentException("수정할 권한이 없음");
		}
		
		int isSuccess = this.memberDao.updateCmpnyLkIndstr(memberCompanyModifyVO);
		if(isSuccess <= 0) {
			throw new IllegalArgumentException("에외");
		}
		
		return this.memberDao.updateMemberCompanyMyPage(memberCompanyModifyVO)>0;
	}

	@Override
	public List<ReviewVO> selectPagination(MemberPaginationVO memberPaginationVO, String emilAddr) {
		List<ReviewVO> result;
		
		if(memberPaginationVO == null) {
			result = this.memberDao.selectReviewList(emilAddr);
		}else {
			result = this.memberDao.selectPagination(memberPaginationVO);
		}
		
		return result;
	}

	@Override
	public List<ReviewVO> selectPaginationByScrDesc(MemberPaginationVO memberPaginationVO, String emilAddr) {
		List<ReviewVO> result;
		
		if(memberPaginationVO == null) {
			result = this.memberDao.selectReviewListByScrDesc(emilAddr);
		}else {
			result = this.memberDao.selectPaginationByScrDesc(memberPaginationVO);
		}
		return result;
	}

	@Override
	public List<ReviewVO> selectPaginationByScrAsc(MemberPaginationVO memberPaginationVO, String emilAddr) {
		List<ReviewVO> result;
		
		if(memberPaginationVO == null) {
			result = this.memberDao.selectReviewListByScrAsc(emilAddr);
		}else {
			result = this.memberDao.selectPaginationByScrAsc(memberPaginationVO);
		}
		return result;
	}

	@Override
	public boolean updateMyInfoMember(MemberModifyVO memberModifyVO) {
		
		boolean isPossible = isDuplicatedEmail(memberModifyVO.getNewEmilAddr());
		
		UserEmailAuthNumVO userEmailAuthNumVO = new UserEmailAuthNumVO();
		userEmailAuthNumVO.setEmilAddr(memberModifyVO.getNewEmilAddr());
		userEmailAuthNumVO.setEmilAddrCnfrmNmbr(memberModifyVO.getEmilAddrCnfrmNmbr());
		
		if(!this.emailService.isSameTempEmailAuthNum(userEmailAuthNumVO)) {
			return false;
		}
		
		
		if(isPossible) {
			this.emailService.deleteTempEmailAuthNum(memberModifyVO.getNewEmilAddr());
			
			return this.memberDao.updateMyInfoMember(memberModifyVO) > 0;
		}
		return false;
	}

	@Transactional
	@Override
	public boolean updateFreelancerMemberMypage(MemberFreelancerModifyVO memberFreelancerModifyVO) {
		
		boolean isSuccess = updateMbrSkills(memberFreelancerModifyVO.getMbrPrmStkList(), memberFreelancerModifyVO.getEmilAddr());
		if(!isSuccess) {
			throw new IllegalArgumentException("에외");
		}
		return this.memberDao.updateFrreelancerMemberMypage(memberFreelancerModifyVO)>0;
		
	}
	
	@Override
	public MemberMyPageIndsryVO selectIndstrNmByEmilAddr(String emilAddr) {
		return this.memberDao.selectIndstrNmByEmilAddr(emilAddr);
	}
	
	@Override
	public MemberVO selectOneMemberVO(String emilAddr) {
		return this.memberDao.selectOneMemberVO(emilAddr);
	}
	
	@Transactional
	@Override
	public boolean updateMbrSkills(List<MbrPrmStkVO> newSkills, String emilAddr) {
		
		// 1. 기존의 보유 기술 리스트 모두 삭제(초기화)
		mbrPrmStkDao.deleteMbrSkill(emilAddr);
		
		// 2. 새로운 보유 기술 리스트 추가
		for (MbrPrmStkVO skill : newSkills) {
			skill.setEmilAddr(emilAddr);
			mbrPrmStkDao.insertMbrSkill(skill);
		}
		
		return true;
	}

	//페이지 네이션을 위한 포트폴리오 정보 가져오기
	@Override
	public List<MemberPortfolioVO> selectAllPortfoliosForPagenation(
			MemberPortfolioPagenationVO memberPortfolioPagenationVO) {
		// 가져온 포트폴리오들을 MemberPortfolioVO 리스트에 넣어줌
		List<MemberPortfolioVO> portfolioList = new ArrayList<>();
		portfolioList = this.memberDao.selectAllPortfoliosForPagenation(memberPortfolioPagenationVO);
	
		for (MemberPortfolioVO memberPortfolioVO : portfolioList) {
			List<MemberPortfolioAttVO> attList = this.selectAllPortfolioAtt(memberPortfolioVO.getMbrPrtflId());
			memberPortfolioVO.setAttVOs(attList);
		}
		return portfolioList;
	}

	@Override
	public List<MbrPrmStkVO> selectMbrPrmStkCmpnyList(String cmpnyId) {
		return this.mbrPrmStkDao.selectMbrCmpnySkill(cmpnyId);
	}

	@Override
	public List<ReviewVO> selectCmpnyPagination(MemberPaginationVO memberPaginationVO, String cmpId) {
		List<ReviewVO> result;
		
		if(memberPaginationVO == null) {
			result = this.memberDao.selectCompanyReviewList(cmpId);
		}else {
			result = this.memberDao.selectCmpnyPagination(memberPaginationVO);
		}
		
		return result;
	}

	@Override
	public List<ReviewVO> selectCmpnyPaginationByScrDesc(MemberPaginationVO memberPaginationVO, String cmpId) {
		List<ReviewVO> result;
		
		if(memberPaginationVO == null) {
			result = this.memberDao.selectCompanyReviewListByScrDesc(cmpId);
		}else {
			result = this.memberDao.selectCmpnyPaginationByScrDesc(memberPaginationVO);
		}
		
		return result;
	}

	@Override
	public List<ReviewVO> selectCmpnyPaginationByScrAsc(MemberPaginationVO memberPaginationVO, String cmpId) {
		
		List<ReviewVO> result;
		
		if(memberPaginationVO == null) {
			result = this.memberDao.selectCompanyReviewListByScrAsc(cmpId);
		}else {
			result = this.memberDao.selectCmpnyPaginationByScrAsc(memberPaginationVO);
		}
		
		return result;
	}

	@Override
	public List<ReviewVO> selectCompanyReviewList(String cmpId) {
		return this.memberDao.selectCompanyReviewList(cmpId);
	}

	@Override
	public List<ReviewVO> selectCompanyReviewListByScrDesc(String cmpId) {
		return this.memberDao.selectCompanyReviewListByScrDesc(cmpId);
	}

	@Override
	public List<ReviewVO> selectCompanyReviewListByScrAsc(String cmpId) {
		return this.memberDao.selectCompanyReviewListByScrAsc(cmpId);
	}
}
