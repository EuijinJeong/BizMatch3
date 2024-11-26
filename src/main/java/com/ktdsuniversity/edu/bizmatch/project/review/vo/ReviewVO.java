package com.ktdsuniversity.edu.bizmatch.project.review.vo;

public class ReviewVO {

	/**
	 * 리뷰 아이디
	 */
	private String rvwId;

	/**
	 * 리뷰 내용
	 */
	private String rvwCntnt;

	/**
	 * 리뷰 작성날짜
	 */
	private String rvwDt;

	/**
	 * 별점
	 */
	private double scr;

	/**
	 * 리뷰 삭제 여부 0: 미삭제, 1: 삭제
	 */
	private int isDlt;

	/**
	 * 리뷰 작성자
	 */
	private String emilAddr;

	/**
	 * 프로젝트 ID
	 */
	private String pjId;

	/**
	 * 리뷰 신고 횟수
	 */
	private int rvwRprtCnt;

	public String getRvwId() {
		return rvwId;
	}

	public void setRvwId(String rvwId) {
		this.rvwId = rvwId;
	}

	public String getRvwCntnt() {
		return rvwCntnt;
	}

	public void setRvwCntnt(String rvwCntnt) {
		this.rvwCntnt = rvwCntnt;
	}

	public String getRvwDt() {
		return rvwDt;
	}

	public void setRvwDt(String rvwDt) {
		this.rvwDt = rvwDt;
	}

	public double getScr() {
		return scr;
	}

	public void setScr(double scr) {
		this.scr = scr;
	}

	public int getIsDlt() {
		return isDlt;
	}

	public void setIsDlt(int isDlt) {
		this.isDlt = isDlt;
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

	public int getRvwRprtCnt() {
		return rvwRprtCnt;
	}

	public void setRvwRprtCnt(int rvwRprtCnt) {
		this.rvwRprtCnt = rvwRprtCnt;
	}

}
