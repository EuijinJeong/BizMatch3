package com.ktdsuniversity.edu.bizmatch.payment.vo;

public class PaymentRequestVO {
	private String pymntId; // 결제 아이디.
	private String pjId; // 프로젝트 아이디.
	private int cntrctAmt; // 계약금 결제 금액 (수수료 포함)
	private String cntrctPdDt; // 계약금 납부일.
	private String accntNm; // 계좌 번호.
	private String emilAddr; // 결제자 이메일 정보.
	private int paymentType; // 0 보증금 1 계약금.
	private String impUid; //아임포트 결제 성공 시 고유한 식별번호
	private String mbrNm; // 결제자 이름.
	private int grntAmt; // 보증금 금액.
	private String grntPdDt; // 보증금 납부일.

	// getter and setter
	public String getGrntPdDt() {
		return grntPdDt;
	}
	
	public void setGrntPdDt(String grntPdDt) {
		this.grntPdDt = grntPdDt;
	}
	
	public int getGrntAmt() {
		return grntAmt;
	}
	
	public void setGrntAmt(int grntAmt) {
		this.grntAmt = grntAmt;
	}
	
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
	public String getCntrctPdDt() {
		return cntrctPdDt;
	}
	public void setCntrctPdDt(String cntrctPdDt) {
		this.cntrctPdDt = cntrctPdDt;
	}
	public String getAccntNm() {
		return accntNm;
	}
	public void setAccntNm(String accntNm) {
		this.accntNm = accntNm;
	}
	public String getEmilAddr() {
		return emilAddr;
	}
	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}
	public int getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}
	public String getImpUid() {
		return impUid;
	}
	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}
	
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}
	public String getMbrNm() {
		return mbrNm;
	}
}
