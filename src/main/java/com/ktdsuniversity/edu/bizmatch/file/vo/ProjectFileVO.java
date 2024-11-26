package com.ktdsuniversity.edu.bizmatch.file.vo;

public class ProjectFileVO {
	
private String emilAddr; // 회원 이메일
	
	private String pjAddId; // 프로젝트 등록 첨부자료 아이디
	
	private String pjId; // 프로젝트 아이디
	
	private String pjAttUrl; // 프로젝트 등록 첨부자료 원래이름
	
	private String pjAttUrlNonread; // 첨부자료 난독화 파일이름

	
	
	public String getEmilAddr() {
		return emilAddr;
	}

	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}

	public String getPjAddId() {
		return pjAddId;
	}

	public void setPjAddId(String pjAddId) {
		this.pjAddId = pjAddId;
	}

	public String getPjId() {
		return pjId;
	}

	public void setPjId(String pjId) {
		this.pjId = pjId;
	}

	public String getPjAttUrl() {
		return pjAttUrl;
	}

	public void setPjAttUrl(String pjAttUrl) {
		this.pjAttUrl = pjAttUrl;
	}

	public String getPjAttUrlNonread() {
		return pjAttUrlNonread;
	}

	public void setPjAttUrlNonread(String pjAttUrlNonread) {
		this.pjAttUrlNonread = pjAttUrlNonread;
	}
	
	

	
}
