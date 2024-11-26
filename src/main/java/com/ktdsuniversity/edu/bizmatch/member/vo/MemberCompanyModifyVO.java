package com.ktdsuniversity.edu.bizmatch.member.vo;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.common.skills.vo.MbrPrmStkVO;

public class MemberCompanyModifyVO {
	private String cmpnyId;
	private String cmpnyNm;
	private String cmpnySiteUrl;
	private String cmpnyAddr;
	private String cmpnyIntr;
	private String cmpnyAccuntNum;
	private String cmpnyBizCtgryId;
	private String cmpnyIndstrId;
	private String emilAddr;

	private List<MbrPrmStkVO> mbrPrmStkList;

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

	public List<MbrPrmStkVO> getMbrPrmStkList() {
		return mbrPrmStkList;
	}

	public void setMbrPrmStkList(List<MbrPrmStkVO> mbrPrmStkList) {
		this.mbrPrmStkList = mbrPrmStkList;
	}

	public String getCmpnyBizCtgryId() {
		return cmpnyBizCtgryId;
	}

	public void setCmpnyBizCtgryId(String cmpnyBizCtgryId) {
		this.cmpnyBizCtgryId = cmpnyBizCtgryId;
	}

	public String getCmpnyIndstrId() {
		return cmpnyIndstrId;
	}

	public void setCmpnyIndstrId(String cmpnyIndstrId) {
		this.cmpnyIndstrId = cmpnyIndstrId;
	}

	public String getEmilAddr() {
		return emilAddr;
	}

	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}

}
