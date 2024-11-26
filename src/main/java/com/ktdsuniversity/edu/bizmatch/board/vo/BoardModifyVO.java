package com.ktdsuniversity.edu.bizmatch.board.vo;

public class BoardModifyVO {

	private int pstCtgry ;	
	private String pstNm    ;
	private String pstCntnt ;
	private int isPstOpn;
	private String pstId;
	
	public String getPstId() {
		return pstId;
	}
	public void setPstId(String pstId) {
		this.pstId = pstId;
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
	public int getIsPstOpn() {
		return isPstOpn;
	}
	public void setIsPstOpn(int isPstOpn) {
		this.isPstOpn = isPstOpn;
	}

}
