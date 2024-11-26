package com.ktdsuniversity.edu.bizmatch.file.vo;

public class FileVO {
	private String attUrl; // 원래 파일 이름.
	private String attUrlNonread; // 난독화한 파일 이름.
	private String mbrSgnupAttId; // 아이디값.
	private String emilAddr; // 회원 이메일.
	
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
	public String getMbrSgnupAttId() {
		return mbrSgnupAttId;
	}
	public void setMbrSgnupAttId(String mbrSgnupAttId) {
		this.mbrSgnupAttId = mbrSgnupAttId;
	}
	public String getEmilAddr() {
		return emilAddr;
	}
	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}
}
