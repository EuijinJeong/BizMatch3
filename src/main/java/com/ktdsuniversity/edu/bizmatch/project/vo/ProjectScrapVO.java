package com.ktdsuniversity.edu.bizmatch.project.vo;

public class ProjectScrapVO {
	private String pjScrpId; // 프로젝트 스크랩 아이디.
	private String emilAddr; // 스크랩을 한 사용자의 아이디.
	private String pjId; // 스크랩한 프로젝트의 아이디.
	
	// getter and setter
	public String getPjScrpId() {
		return pjScrpId;
	}
	public void setPjScrpId(String pjScrpId) {
		this.pjScrpId = pjScrpId;
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
}
