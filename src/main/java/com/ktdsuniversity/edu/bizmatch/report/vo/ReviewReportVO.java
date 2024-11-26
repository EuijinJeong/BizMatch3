package com.ktdsuniversity.edu.bizmatch.report.vo;

public class ReviewReportVO {

	/**
	 * 리뷰 댓글 신고 정보 아이디
	 */
	private String rprtId;

	/**
	 * 신고자 정보
	 */
	private String emilAddr;

	/**
	 * 리뷰 아이디
	 */
	private String cmmntId;

	/**
	 * 신고 유형 (1): 부적절한 게시물, (2): 비방언어, (3): 광고, (4): 기타
	 */
	private int rprtCtgry;

	/**
	 * 신고 내용
	 */
	private String rprtCntnt;

	public String getRprtId() {
		return rprtId;
	}

	public void setRprtId(String rprtId) {
		this.rprtId = rprtId;
	}

	public String getEmilAddr() {
		return emilAddr;
	}

	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}

	public String getCmmntId() {
		return cmmntId;
	}

	public void setCmmntId(String cmmntId) {
		this.cmmntId = cmmntId;
	}

	public int getRprtCtgry() {
		return rprtCtgry;
	}

	public void setRprtCtgry(int rprtCtgry) {
		this.rprtCtgry = rprtCtgry;
	}

	public String getRprtCntnt() {
		return rprtCntnt;
	}

	public void setRprtCntnt(String rprtCntnt) {
		this.rprtCntnt = rprtCntnt;
	}
}
