package com.ktdsuniversity.edu.bizmatch.comment.vo;

public class CommentVO {

	private String pjId       ;
	private String prntCmmntId;
	private String cmmntCntnt ;
	private String crtdDt     ;
	private String lstModDt   ;
	private String isDlt      ;
	private String dltDt      ;
	private String athrId     ;
	private String pjCmmntId  ;
	private String mbrNm;
	private int rNum;

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	private int lv;

	public String getMbrNm() {
		return mbrNm;
	}

	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public String getPjCmmntId() {
		return pjCmmntId;
	}

	public void setPjCmmntId(String pjCmmntId) {
		this.pjCmmntId = pjCmmntId;
	}
	public String getPjId() {
		return pjId;
	}
	public void setPjId(String pjId) {
		this.pjId = pjId;
	}
	public String getPrntCmmntId() {
		return prntCmmntId;
	}
	public void setPrntCmmntId(String prntCmmntId) {
		this.prntCmmntId = prntCmmntId;
	}
	public String getCmmntCntnt() {
		return cmmntCntnt;
	}
	public void setCmmntCntnt(String cmmntCntnt) {
		this.cmmntCntnt = cmmntCntnt;
	}
	public String getCrtdDt() {
		return crtdDt;
	}
	public void setCrtdDt(String crtdDt) {
		this.crtdDt = crtdDt;
	}
	public String getLstModDt() {
		return lstModDt;
	}
	public void setLstModDt(String lstModDt) {
		this.lstModDt = lstModDt;
	}
	public String getIsDlt() {
		return isDlt;
	}
	public void setIsDlt(String isDlt) {
		this.isDlt = isDlt;
	}
	public String getDltDt() {
		return dltDt;
	}
	public void setDltDt(String dltDt) {
		this.dltDt = dltDt;
	}
	public String getAthrId() {
		return athrId;
	}
	public void setAthrId(String athrId) {
		this.athrId = athrId;
	}

}       
