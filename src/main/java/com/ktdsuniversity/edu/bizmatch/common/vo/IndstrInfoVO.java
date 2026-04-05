package com.ktdsuniversity.edu.bizmatch.common.vo;

public class IndstrInfoVO {

	/**
	 * 산업군 아이디
	 */
	private String indstrId;

	/**
	 * 산업군 명
	 */
	private String indstrNm;

	/**
	 * 상위 산업군 아이디
	 */
	private String upprIndstrId;

	// getter and setter.
	public String getIndstrId() {
		return indstrId;
	}

	public void setIndstrId(String indstrId) {
		this.indstrId = indstrId;
	}

	public String getIndstrNm() {
		return indstrNm;
	}

	public void setIndstrNm(String indstrNm) {
		this.indstrNm = indstrNm;
	}

	public String getUpprIndstrId() {
		return upprIndstrId;
	}

	public void setUpprIndstrId(String upprIndstrId) {
		this.upprIndstrId = upprIndstrId;
	}

}
