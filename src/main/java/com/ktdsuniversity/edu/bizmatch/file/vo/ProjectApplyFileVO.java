package com.ktdsuniversity.edu.bizmatch.file.vo;

public class ProjectApplyFileVO {
	
private String emilAddr; // 회원 이메일
	
	private String pjApplyAttId; // 프로젝트 지원 등록 첨부자료 아이디
	
	private String pjApplyId; // 프로젝트 지원 아이디
	
	private String pjApplyAttUrl; // 프로젝트 지원 등록 첨부자료 원래이름
	
	private String pjApplyAttUrlNoneread; // 첨부자료 난독화 파일이름

	public String getEmilAddr() {
		return emilAddr;
	}

	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}

	public String getPjApplyAttId() {
		return pjApplyAttId;
	}

	public void setPjApplyAttId(String pjApplyAttId) {
		this.pjApplyAttId = pjApplyAttId;
	}

	public String getPjApplyId() {
		return pjApplyId;
	}

	public void setPjApplyId(String pjApplyId) {
		this.pjApplyId = pjApplyId;
	}

	public String getPjApplyAttUrl() {
		return pjApplyAttUrl;
	}

	public void setPjApplyAttUrl(String pjApplyAttUrl) {
		this.pjApplyAttUrl = pjApplyAttUrl;
	}

	public String getPjApplyAttUrlNoneread() {
		return pjApplyAttUrlNoneread;
	}

	public void setPjApplyAttUrlNoneread(String pjApplyAttUrlNoneread) {
		this.pjApplyAttUrlNoneread = pjApplyAttUrlNoneread;
	}

	
	
}
