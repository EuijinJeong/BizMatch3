package com.ktdsuniversity.edu.bizmatch.member.vo;

public class MemberSignupAttachmentVO {

	/**
	 * 회원 가입 첨부자료 아이디
	 */
	private String mbrSgnpAttId;

	/**
	 * 회원 이메일
	 */
	private String emilAddr;

	/**
	 * 회원가입 첨부자료 원래 이름
	 */
	private String attUrl;

	/**
	 * 첨부자료 난독화 파일 이름
	 */
	private String attFileName;

	/**
	 * 회원 포트폴리오 아이디
	 */
	private String portfolioId;

	public String getMbrSgnpAttId() {
		return mbrSgnpAttId;
	}

	public void setMbrSgnpAttId(String mbrSgnpAttId) {
		this.mbrSgnpAttId = mbrSgnpAttId;
	}

	public String getEmilAddr() {
		return emilAddr;
	}

	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}

	public String getAttUrl() {
		return attUrl;
	}

	public void setAttUrl(String attUrl) {
		this.attUrl = attUrl;
	}

	public String getAttFileName() {
		return attFileName;
	}

	public void setAttFileName(String attFileName) {
		this.attFileName = attFileName;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
}
