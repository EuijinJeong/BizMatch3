package com.ktdsuniversity.edu.bizmatch.payment.vo;

public class PaymentUpdateVO {
	private String pymntId; // 결제 아이디.
	private String pjId; // 프로젝트 아이디.
	private int cntrctAmt; // 계약금 금액 (수수료 포함)
	
	// getter and setter
	public String getPymntId() {
		return pymntId;
	}
	public void setPymntId(String pymntId) {
		this.pymntId = pymntId;
	}
	public String getPjId() {
		return pjId;
	}
	public void setPjId(String pjId) {
		this.pjId = pjId;
	}
	public int getCntrctAmt() {
		return cntrctAmt;
	}
	public void setCntrctAmt(int cntrctAmt) {
		this.cntrctAmt = cntrctAmt;
	}
}
