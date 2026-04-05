package com.ktdsuniversity.edu.bizmatch.board.vo;

public class BoardWriteVO {

	
	private String athrId;
	private int pstCtgry; //게시글 카테고리 (공지글 / 문의글)
	private String pstNm; //게시글 제목
	private String pstCntnt; //게시글 내용
	private int isPstOpn; //공개 비공개 여부
	
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
	
	public int getIsPstOpn() {
		return isPstOpn;
	}
	public void setIsPstOpn(int isPstOpn) {
		this.isPstOpn = isPstOpn;
	}
}
