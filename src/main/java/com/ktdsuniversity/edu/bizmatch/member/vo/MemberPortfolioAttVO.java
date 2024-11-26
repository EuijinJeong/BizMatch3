package com.ktdsuniversity.edu.bizmatch.member.vo;

public class MemberPortfolioAttVO {
	
	private String mbrPrtflAttId; // 포트폴리오 첨부파일 아이디.
	private String attUrl; // 첨부파일 난독화 전 url.
	private String attUrlNonread; // 첨부파일 난독화 한 후 url.
	private String mbrPrtflId; // 멤버 포트폴리오 정보 아이디값. fk.
	
	// getter and setter.
	public String getMbrPrtflAttId() {
		return mbrPrtflAttId;
	}
	public void setMbrPrtflAttId(String mbrPrtflAttId) {
		this.mbrPrtflAttId = mbrPrtflAttId;
	}
	public String getAttUrl() {
		return attUrl;
	}
	public void setAttUrl(String attUrl) {
		this.attUrl = attUrl;
	}
	public String getAttUrlNonread() {
		return attUrlNonread;
	}
	public void setAttUrlNonread(String attUrlNonread) {
		this.attUrlNonread = attUrlNonread;
	}
	public String getMbrPrtflId() {
		return mbrPrtflId;
	}
	public void setMbrPrtflId(String mbrPrtflId) {
		this.mbrPrtflId = mbrPrtflId;
	}
}
