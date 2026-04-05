package com.ktdsuniversity.edu.bizmatch.project.review.vo;

public class DeleteReviewVO {

	private String rvwId;

	private String pjId;

	/**
	 * 리뷰 삭제일
	 */
	private String dltDt;

	private int isDlt;

	private String emilAddr;

	public String getRvwId() {
		return rvwId;
	}

	public void setRvwId(String rvwId) {
		this.rvwId = rvwId;
	}

	public String getPjId() {
		return pjId;
	}

	public void setPjId(String pjId) {
		this.pjId = pjId;
	}

	public String getDltDt() {
		return dltDt;
	}

	public void setDltDt(String dltDt) {
		this.dltDt = dltDt;
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

}
