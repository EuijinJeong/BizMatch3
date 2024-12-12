package com.ktdsuniversity.edu.bizmatch.member.vo;

public class MemberCompanyModifyVO {
	private String cmpnyId; // 회사 아이디.
	private String cmpnyNm; // 회사 이름.
	private String cmpnySiteUrl; // 회사 사이트 주소.
	private String cmpnyAddr; // 회사 건물 주소.
	private String cmpnyIntr; // 기업 마이페이지 소개글.
	private String cmpnyAccuntNum; // 기업 계좌번호. 
	private String mjrId; // 관심 산업군 대분류 아이디.
	private String smjrId; // 관심 산업군 중분류 아이디.
//	private List<MbrPrmStkVO> mbrPrmStkList; // 회사 주요 스킬 리스트.

	public String getCmpnyIntr() {
		return cmpnyIntr;
	}

	public void setCmpnyIntr(String cmpnyIntr) {
		this.cmpnyIntr = cmpnyIntr;
	}

	public String getCmpnyAccuntNum() {
		return cmpnyAccuntNum;
	}

	public void setCmpnyAccuntNum(String cmpnyAccuntNum) {
		this.cmpnyAccuntNum = cmpnyAccuntNum;
	}

	public String getCmpnyId() {
		return cmpnyId;
	}

	public void setCmpnyId(String cmpnyId) {
		this.cmpnyId = cmpnyId;
	}

	public String getCmpnyNm() {
		return cmpnyNm;
	}

	public void setCmpnyNm(String cmpnyNm) {
		this.cmpnyNm = cmpnyNm;
	}

	public String getCmpnySiteUrl() {
		return cmpnySiteUrl;
	}

	public void setCmpnySiteUrl(String cmpnySiteUrl) {
		this.cmpnySiteUrl = cmpnySiteUrl;
	}

	public String getCmpnyAddr() {
		return cmpnyAddr;
	}

	public void setCmpnyAddr(String cmpnyAddr) {
		this.cmpnyAddr = cmpnyAddr;
	}
//
//	public List<MbrPrmStkVO> getMbrPrmStkList() {
//		return mbrPrmStkList;
//	}
//
//	public void setMbrPrmStkList(List<MbrPrmStkVO> mbrPrmStkList) {
//		this.mbrPrmStkList = mbrPrmStkList;
//	}

	public String getMjrId() {
		return mjrId;
	}

	public void setMjrId(String mjrId) {
		this.mjrId = mjrId;
	}

	public String getSmjrId() {
		return smjrId;
	}

	public void setSmjrId(String smjrId) {
		this.smjrId = smjrId;
	}

}
