package com.ktdsuniversity.edu.bizmatch.project.vo;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentVO;
import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;
import com.ktdsuniversity.edu.bizmatch.payment.vo.PaymentVO;

public class ProjectVO {

	private String pjId; // 프로젝트 아이디
	private String ordrId; // 발주자 아이디
	private String obtnId; // 수주자 아이디
	private String pjTtl; // 프로젝트 제목
	private String rgstrDt; // 프로젝트 등록일
	private String strtDt; // 프로젝트 시작일
	private String endDt; // 프로젝트 종료일
	private int cntrctAccnt; // 프로젝트 입찰가격
	private String pjDesc; // 프로젝트 상세설명
	private int isDlt; // 프로젝트 게시글 삭제여부
	private String dltDt; // 프로젝트 게시글 삭제일
	private int isRcrutAdd; // 프로젝트 추가모집 여부
	private int pjStt; // 프로젝트 상태
	private int viewCnt; // 조회수
	private int pjRcrutCnt; // 프로젝트 모집인원
	private String pjRcrutStrtDt; // 프로젝트 모집 시작일
	private String pjRcrutEndDt; // 프로젝트 모집 종료일
	private MemberVO memberVO; // 프로젝트 등록을 작성한 회원의 정보
	private List<CommentVO> projectCommentList; // 프로젝트에 작성된 댓글들 리스트
	private List<ProjectSkillVO> projectSkillList; // 해당 프로젝트의 보유 기술 목록
	private String smjrNm; //프로젝트 분야 중분류
	private String pjApplyId;
	private PaymentVO paymentVO;
	private ProjectIndustryVO projectIndustryVO;
	private List<ApplyProjectVO> applyProjectVOList;
	private List<ProjectAttachmentVO> projectAtt;
	private List<ProjectScrapVO> projectScrapList;
	
	
	
	public List<ProjectScrapVO> getProjectScrapList() {
		return projectScrapList;
	}
	public void setProjectScrapList(List<ProjectScrapVO> projectScrapList) {
		this.projectScrapList = projectScrapList;
	}
	public List<ProjectAttachmentVO> getProjectAtt() {
		return projectAtt;
	}
	public void setProjectAtt(List<ProjectAttachmentVO> projectAtt) {
		this.projectAtt = projectAtt;
	}
	public List<ApplyProjectVO> getApplyProjectVOList() {
		return applyProjectVOList;
	}
	public void setApplyProjectVOList(List<ApplyProjectVO> applyProjectVOList) {
		this.applyProjectVOList = applyProjectVOList;
	}
	public String getPjId() {
		return pjId;
	}
	public void setPjId(String pjId) {
		this.pjId = pjId;
	}
	public String getOrdrId() {
		return ordrId;
	}
	public void setOrdrId(String ordrId) {
		this.ordrId = ordrId;
	}
	public String getObtnId() {
		return obtnId;
	}
	public void setObtnId(String obtnId) {
		this.obtnId = obtnId;
	}
	public String getPjTtl() {
		return pjTtl;
	}
	public void setPjTtl(String pjTtl) {
		this.pjTtl = pjTtl;
	}
	public String getRgstrDt() {
		return rgstrDt;
	}
	public void setRgstrDt(String rgstrDt) {
		this.rgstrDt = rgstrDt;
	}
	public String getStrtDt() {
		return strtDt;
	}
	public void setStrtDt(String strtDt) {
		this.strtDt = strtDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public int getCntrctAccnt() {
		return cntrctAccnt;
	}
	public void setCntrctAccnt(int cntrctAccnt) {
		this.cntrctAccnt = cntrctAccnt;
	}
	public String getPjDesc() {
		return pjDesc;
	}
	public void setPjDesc(String pjDesc) {
		this.pjDesc = pjDesc;
	}
	public int getIsDlt() {
		return isDlt;
	}
	public void setIsDlt(int isDlt) {
		this.isDlt = isDlt;
	}
	public String getDltDt() {
		return dltDt;
	}
	public void setDltDt(String dltDt) {
		this.dltDt = dltDt;
	}
	public int getIsRcrutAdd() {
		return isRcrutAdd;
	}
	public void setIsRcrutAdd(int isRcrutAdd) {
		this.isRcrutAdd = isRcrutAdd;
	}
	public int getPjStt() {
		return pjStt;
	}
	public void setPjStt(int pjStt) {
		this.pjStt = pjStt;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getPjRcrutCnt() {
		return pjRcrutCnt;
	}
	public void setPjRcrutCnt(int pjRcrutCnt) {
		this.pjRcrutCnt = pjRcrutCnt;
	}
	public String getPjRcrutStrtDt() {
		return pjRcrutStrtDt;
	}
	public void setPjRcrutStrtDt(String pjRcrutStrtDt) {
		this.pjRcrutStrtDt = pjRcrutStrtDt;
	}
	public String getPjRcrutEndDt() {
		return pjRcrutEndDt;
	}
	public void setPjRcrutEndDt(String pjRcrutEndDt) {
		this.pjRcrutEndDt = pjRcrutEndDt;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public List<CommentVO> getProjectCommentList() {
		return projectCommentList;
	}
	public void setProjectCommentList(List<CommentVO> projectCommentList) {
		this.projectCommentList = projectCommentList;
	}
	public List<ProjectSkillVO> getProjectSkillList() {
		return projectSkillList;
	}
	public void setProjectSkillList(List<ProjectSkillVO> projectSkillList) {
		this.projectSkillList = projectSkillList;
	}
	public String getSmjrNm() {
		return smjrNm;
	}
	public void setSmjrNm(String smjrNm) {
		this.smjrNm = smjrNm;
	}
	public String getPjApplyId() {
		return pjApplyId;
	}
	public void setPjApplyId(String pjApplyId) {
		this.pjApplyId = pjApplyId;
	}
	public PaymentVO getPaymentVO() {
		return paymentVO;
	}
	public void setPaymentVO(PaymentVO paymentVO) {
		this.paymentVO = paymentVO;
	}
	public ProjectIndustryVO getProjectIndustryVO() {
		return projectIndustryVO;
	}
	public void setProjectIndustryVO(ProjectIndustryVO projectIndustryVO) {
		this.projectIndustryVO = projectIndustryVO;
	}
}
