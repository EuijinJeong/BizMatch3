package com.ktdsuniversity.edu.bizmatch.member.vo;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.common.skills.vo.MbrPrmStkVO;

public class MemberFreelancerModifyVO {
	private String emilAddr;
	private String mbrIntr;
	private String mjrId;
	private String smjrId;
	private String accntNum;

	private List<MbrPrmStkVO> mbrPrmStkList;

	public String getEmilAddr() {
		return emilAddr;
	}

	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}

	public String getMbrIntr() {
		return mbrIntr;
	}

	public void setMbrIntr(String mbrIntr) {
		this.mbrIntr = mbrIntr;
	}

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

	public List<MbrPrmStkVO> getMbrPrmStkList() {
		return mbrPrmStkList;
	}

	public void setMbrPrmStkList(List<MbrPrmStkVO> mbrPrmStkList) {
		this.mbrPrmStkList = mbrPrmStkList;
	}

	public String getAccntNum() {
		return accntNum;
	}

	public void setAccntNum(String accntNum) {
		this.accntNum = accntNum;
	}

}
