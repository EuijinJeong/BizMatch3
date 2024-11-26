package com.ktdsuniversity.edu.bizmatch.payment.vo;

public class AccntVO {
	private String accntNm; // BizMatch 계좌번호.
	private int accntBl; // 계좌 잔고.
	
	// getter and setter.
	public String getAccntNm() {
		return accntNm;
	}
	public void setAccntNm(String accntNm) {
		this.accntNm = accntNm;
	}
	public int getAccntBl() {
		return accntBl;
	}
	public void setAccntBl(int accntBl) {
		this.accntBl = accntBl;
	}
}
