package com.ktdsuniversity.edu.bizmatch.member.vo;

public class MbrLkIndstrVO {

	/**
	 * 회원의 관심산업군 아이디
	 */
	private String lkIndstrId;

	/**
	 * 대분류 ID
	 */
	private String mjrId;

	/**
	 * 중분류 ID
	 */
	private String smjrId;

	/**
	 * 회원 이메일
	 */
	private String emilAddr;

	public String getLkIndstrId() {
		return lkIndstrId;
	}

	public void setLkIndstrId(String lkIndstrId) {
		this.lkIndstrId = lkIndstrId;
	}

	public String getMjrId() {
		return mjrId;
	}

	public void setMjrId(String mjrId) {
		this.mjrId = mjrId;
	}

	public String getSmjrId() {
		return smjrId;
	}

	public void setSmjrId(String smjrId) {
		this.smjrId = smjrId;
	}

	public String getEmilAddr() {
		return emilAddr;
	}

	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}

}
