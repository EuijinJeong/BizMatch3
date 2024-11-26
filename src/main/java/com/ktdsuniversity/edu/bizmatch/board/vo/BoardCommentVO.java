package com.ktdsuniversity.edu.bizmatch.board.vo;

public class BoardCommentVO {
	
	private String cmmntId     ;
	private String pstId       ;
	private String prntCmmntId ;
	private String lstModDt    ;
	private String cmmntCntnt  ;
	private int isDlt       ;
	private String dltDt       ;
	private String athrId      ;
	private String mbrNm     ;

	private int rNum;
	private int lv;
	
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public String getCmmntId() {
		return cmmntId;
	}
	public void setCmmntId(String cmmntId) {
		this.cmmntId = cmmntId;
	}
	public String getPstId() {
		return pstId;
	}
	public void setPstId(String pstId) {
		this.pstId = pstId;
	}
	public String getPrntCmmntId() {
		return prntCmmntId;
	}
	public void setPrntCmmntId(String prntCmmntId) {
		this.prntCmmntId = prntCmmntId;
	}
	public String getLstModDt() {
		return lstModDt;
	}
	public void setLstModDt(String lstModDt) {
		this.lstModDt = lstModDt;
	}
	public String getCmmntCntnt() {
		return cmmntCntnt;
	}
	public void setCmmntCntnt(String cmmntCntnt) {
		this.cmmntCntnt = cmmntCntnt;
	}
	public int getIsDlt() {
		return isDlt;
	}
	public void setIsDlt(int isDlt) {
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
	public String getMbrNm() {
		return mbrNm;
	}
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}

}
