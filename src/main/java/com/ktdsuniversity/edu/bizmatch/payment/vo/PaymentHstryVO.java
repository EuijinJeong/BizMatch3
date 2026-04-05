package com.ktdsuniversity.edu.bizmatch.payment.vo;

public class PaymentHstryVO {
	private String vrtlAccntId; // bizmatch 계좌
	private int amnt; //돈 
	private String type; // 입금/출금
	private String accntInfo; // 상대계좌 번호
	private String accntDt ; // 입/출금 시간
	private String dpstr; // 예금주명
	private String bnkInfo; // 은행 정보
	public String getVrtlAccntId() {
		return vrtlAccntId;
	}
	public void setVrtlAccntId(String vrtlAccntId) {
		this.vrtlAccntId = vrtlAccntId;
	}
	public int getAmnt() {
		return amnt;
	}
	public void setAmnt(int amnt) {
		this.amnt = amnt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccntInfo() {
		return accntInfo;
	}
	public void setAccntInfo(String accntInfo) {
		this.accntInfo = accntInfo;
	}
	public String getAccntDt() {
		return accntDt;
	}
	public void setAccntDt(String accntDt) {
		this.accntDt = accntDt;
	}
	public String getDpstr() {
		return dpstr;
	}
	public void setDpstr(String dpstr) {
		this.dpstr = dpstr;
	}
	public String getBnkInfo() {
		return bnkInfo;
	}
	public void setBnkInfo(String bnkInfo) {
		this.bnkInfo = bnkInfo;
	}
	
	
}
