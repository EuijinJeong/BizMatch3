package com.ktdsuniversity.edu.bizmatch.common.skills.vo;

import com.ktdsuniversity.edu.bizmatch.member.vo.PrmStkVO;

public class MbrPrmStkVO {

	/**
	 * 회원보유기술 아이디
	 */
	private String mbrPrmStkId;

	/**
	 * 주요 기술 아이디
	 */
	private String prmStkId;

	/**
	 * 회원 이메일
	 */
	private String emilAddr;

	/**
	 * 사이트에서 제공하는 보유기술 List
	 */
	private PrmStkVO prmStkVO;
	

	public PrmStkVO getPrmStkVO() {
		return prmStkVO;
	}

	public void setPrmStkVO(PrmStkVO prmStkVO) {
		this.prmStkVO = prmStkVO;
	}

	public String getMbrPrmStkId() {
		return mbrPrmStkId;
	}

	public void setMbrPrmStkId(String mbrPrmStkId) {
		this.mbrPrmStkId = mbrPrmStkId;
	}

	public String getPrmStkId() {
		return prmStkId;
	}

	public void setPrmStkId(String prmStkId) {
		this.prmStkId = prmStkId;
	}

	public String getEmilAddr() {
		return emilAddr;
	}

	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}

	
}
