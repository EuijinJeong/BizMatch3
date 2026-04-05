package com.ktdsuniversity.edu.bizmatch.project.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

public class ApplyProjectVO {
	private String pjApplyId; // 프로젝트 지원 정보 아이디
	private String emilAddr; // 회원 이메일
	private String pjId; // 프로젝트 아이디
	private String pjApplyRgstrDt; // 프로젝트 지원 정보 등록일
	private String pjApplyTtl; // 프로젝트 지원 정보 제목
	private String pjApplyDesc; // 프로젝트 지원 정보 소개글
	private List<MultipartFile> fileList; //지원자 첨부자료
	private ProjectVO projectVO; // 프로젝트 정보를 담은 vo.
	private MemberVO memberVO; //프로젝트의 지원한 사람들의 정보를 가져오기위함
	private List<ProjectApplyAttVO> projectApplyAttVOList;
	
	
	public List<ProjectApplyAttVO> getProjectApplyAttVOList() {
		return projectApplyAttVOList;
	}
	public void setProjectApplyAttVOList(List<ProjectApplyAttVO> projectApplyAttVOList) {
		this.projectApplyAttVOList = projectApplyAttVOList;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	// getter and setter
	public ProjectVO getProjectVO() {
		return projectVO;
	}
	public void setProjectVO(ProjectVO projectVO) {
		this.projectVO = projectVO;
	}
	public List<MultipartFile> getFileList() {
		return fileList;
	}
	public void setFileList(List<MultipartFile> fileList) {
		this.fileList = fileList;
	}
	public String getPjApplyId() {
		return pjApplyId;
	}
	public void setPjApplyId(String pjApplyId) {
		this.pjApplyId = pjApplyId;
	}
	public String getEmilAddr() {
		return emilAddr;
	}
	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}
	public String getPjId() {
		return pjId;
	}
	public void setPjId(String pjId) {
		this.pjId = pjId;
	}
	public String getPjApplyRgstrDt() {
		return pjApplyRgstrDt;
	}
	public void setPjApplyRgstrDt(String pjApplyRgstrDt) {
		this.pjApplyRgstrDt = pjApplyRgstrDt;
	}
	public String getPjApplyTtl() {
		return pjApplyTtl;
	}
	public void setPjApplyTtl(String pjApplyTtl) {
		this.pjApplyTtl = pjApplyTtl;
	}
	public String getPjApplyDesc() {
		return pjApplyDesc;
	}
	public void setPjApplyDesc(String pjApplyDesc) {
		this.pjApplyDesc = pjApplyDesc;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("this.emilAddr"+this.emilAddr+"\n");
		sb.append("this.pjApplyId"+this.pjApplyId+"\n");
		sb.append("this.pjId"+this.pjId+"\n");
		sb.append("this.pjApplyRgstrDt"+this.pjApplyRgstrDt+"\n");
		sb.append("this.pjApplyTtl"+this.pjApplyTtl+"\n");
		sb.append("this.pjApplyDesc"+this.pjApplyDesc+"\n");
		
		return sb.toString();
	}
}
