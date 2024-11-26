package com.ktdsuniversity.edu.bizmatch.member.vo;

public class MemberLoginVO {

	/**
	 * 이메일주소. (pk)
	 */
	private String emilAddr;
	
	/**
	 * 비밀번호.
	 */
	private String pwd;
	
	private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getEmilAddr() {
		return emilAddr;
	}

	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
