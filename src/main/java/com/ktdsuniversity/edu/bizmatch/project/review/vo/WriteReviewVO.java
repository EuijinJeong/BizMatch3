package com.ktdsuniversity.edu.bizmatch.project.review.vo;

public class WriteReviewVO {

	private String rvwCntnt;
	private String rvwDt;
	private double scr;
	private int isDlt;
	private String emilAddr;
	private String pjId;
	private int rvwRprtCnt;

	public String getRvwCntnt() {
		return rvwCntnt;
	}

	public void setRvwCntnt(String rvwCntnt) {
		this.rvwCntnt = rvwCntnt;
	}

	public String getRvwDt() {
		return rvwDt;
	}

	public void setRvwDt(String rvwDt) {
		this.rvwDt = rvwDt;
	}

	public double getScr() {
		return scr;
	}

	public void setScr(double scr) {
		this.scr = scr;
	}

	public int getIsDlt() {
		return isDlt;
	}

	public void setIsDlt(int isDlt) {
		this.isDlt = isDlt;
	}

	public String getEmilAddr() {
		return emilAddr;
	}

	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}

	public String getPjId() {
		return pjId;
	}

	public void setPjId(String pjId) {
		this.pjId = pjId;
	}

	public int getRvwRprtCnt() {
		return rvwRprtCnt;
	}

	public void setRvwRprtCnt(int rvwRprtCnt) {
		this.rvwRprtCnt = rvwRprtCnt;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.rvwRprtCnt+"\n");
		sb.append(this.scr+"\n");
		sb.append(this.rvwCntnt+"\n");
		return sb.toString();
	}

}
