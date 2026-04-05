package com.ktdsuniversity.edu.bizmatch.project.vo;

public class ProjectApplyAttVO {
	private String pjApplyAttId; //프로젝트 지원 첨부 자료 아이디
	private String pjApplyId; //프로젝트 지원 정보 아이디
	private String pjApplyAttUrl; //프로젝트 지원 첨부자료 원래이름
	private String pjApplyAttUrlNonread; //첨부자료 난독화 파일 이름

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
	public String getPjApplyAttUrlNonread() {
		return pjApplyAttUrlNonread;
	}
	public void setPjApplyAttUrlNonread(String pjApplyAttUrlNonread) {
		this.pjApplyAttUrlNonread = pjApplyAttUrlNonread;
	}
	
	
}
