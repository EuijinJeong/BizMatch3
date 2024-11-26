package com.ktdsuniversity.edu.bizmatch.payment.vo;

public class PaymentVO {

	private String pymntId; // 결제정보 아이디.
	private String pjId; // 프로젝트 아이디.
	private int grntAmt; // 보증금 금액.
	private int cntrctAmt; // 계약금 금액.
	private String grntPdDt; // 보증금 납부 날짜.
	private String cntrctPdDt; // 계약금 납부 날짜.
	private String accntNm; // 계좌번호.
	private int pymntTyp; // 결제 종류 0- 보증금 1- 계약금.
	private String impUid; // 아임포트 결제시 생성되는 고유 아이디.
	private int dpstRfndAmnt; // 보증금 환금액.
	private String dpstRfndDt; // 보증금을 환급해준 날짜.
	
	
	public int getPymntTyp() {
		return pymntTyp;
	}
	public void setPymntTyp(int pymntTyp) {
		this.pymntTyp = pymntTyp;
	}
	public String getImpUid() {
		return impUid;
	}
	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}
	public int getDpstRfndAmnt() {
		return dpstRfndAmnt;
	}
	public void setDpstRfndAmnt(int dpstRfndAmnt) {
		this.dpstRfndAmnt = dpstRfndAmnt;
	}
	public String getDpstRfndDt() {
		return dpstRfndDt;
	}
	public void setDpstRfndDt(String dpstRfndDt) {
		this.dpstRfndDt = dpstRfndDt;
	}
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
	public int getGrntAmt() {
		return grntAmt;
	}
	public void setGrntAmt(int grntAmt) {
		this.grntAmt = grntAmt;
	}
	public int getCntrctAmt() {
		return cntrctAmt;
	}
	public void setCntrctAmt(int cntrctAmt) {
		this.cntrctAmt = cntrctAmt;
	}
	public String getGrntPdDt() {
		return grntPdDt;
	}
	public void setGrntPdDt(String grntPdDt) {
		this.grntPdDt = grntPdDt;
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
}
