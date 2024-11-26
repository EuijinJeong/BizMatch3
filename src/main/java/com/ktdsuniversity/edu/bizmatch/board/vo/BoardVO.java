package com.ktdsuniversity.edu.bizmatch.board.vo;

public class BoardVO {
	private String pstId    ;
	private String athrId   ;
	private int pstCtgry ;
	private String pstNm    ;
	private String pstCntnt ;
	private String lstModDt;
	private int isDlt    ;
	private String dltDt    ;
	private int pstHt    ;
	private int isPstOpn;
	private String mbrNm;
	
	public String getMbrNm() {
		return mbrNm;
	}
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}
	
	// private String athrNm;
	
	public String getPstId() {
		return pstId;
	}
	public void setPstId(String pstId) {
		this.pstId = pstId;
	}
	public String getAthrId() {
		return athrId;
	}
	public void setAthrId(String athrId) {
		this.athrId = athrId;
	}
	public int getPstCtgry() {
		return pstCtgry;
	}
	public void setPstCtgry(int pstCtgry) {
		this.pstCtgry = pstCtgry;
	}
	public String getPstNm() {
		return pstNm;
	}
	public void setPstNm(String pstNm) {
		this.pstNm = pstNm;
	}
	public String getPstCntnt() {
		return pstCntnt;
	}
	public void setPstCntnt(String pstCntnt) {
		this.pstCntnt = pstCntnt;
	}
	public String getLstModDt() {
		return lstModDt;
	}
	public void setLstModDt(String lstModDt) {
		this.lstModDt = lstModDt;
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
	public int getPstHt() {
		return pstHt;
	}
	public void setPstHt(int pstHt) {
		this.pstHt = pstHt;
	}
	public int getIsPstOpn() {
		return isPstOpn;
	}
	public void setIsPstOpn(int isPstOpn) {
		this.isPstOpn = isPstOpn;
	}
}