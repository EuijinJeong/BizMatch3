package com.ktdsuniversity.edu.bizmatch.member.vo;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.common.skills.vo.MbrPrmStkVO;
import com.ktdsuniversity.edu.bizmatch.project.vo.ProjectVO;

public class MemberVO {

	private String mbrNm;
	private String emilAddr;
	private String pwd;
	private String mbrPhnNum;
	private String brthDt;
	private String addr;
	private int mbrStt;
	private String sgnupDt;
	private int mbrCtgry;
	private int pnlty;
	private int isQt;
	private String qtDt;
	private String accntNum;
	private String accntVldDt;
	private String cmpId;
	private String salt;
	private int lgnFailCnt;
	private String ltstLgnFailDt;
	private String ltstLgnIp;
	private String ltstLgnSccssDt;
	private String mjrId;
	private String smjrId;
	private int cmpnyRp;
	private String mbrIntr;
	private CompanyVO companyVO;
	private List<MbrPrmStkVO> mbrPrmStkList;
	private MbrPrmStkVO mbrPrmStkVO;
	private ProjectVO projectVO;
	
	
	public int getLgnFailCnt() {
		return lgnFailCnt;
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

	public String getMbrIntr() {
		return mbrIntr;
	}

	public void setMbrIntr(String mbrIntr) {
		this.mbrIntr = mbrIntr;
	}

	public void setLgnFailCnt(int lgnFailCnt) {
		this.lgnFailCnt = lgnFailCnt;
	}

	public int getCmpnyRp() {
		return cmpnyRp;
	}

	public void setCmpnyRp(int cmpnyRp) {
		this.cmpnyRp = cmpnyRp;
	}

	public String getMbrNm() {
		return mbrNm;
	}

	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}

	public String getEmilAddr() {
		return emilAddr;
	}

	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMbrPhnNum() {
		return mbrPhnNum;
	}

	public void setMbrPhnNum(String mbrPhnNum) {
		this.mbrPhnNum = mbrPhnNum;
	}

	public String getBrthDt() {
		return brthDt;
	}

	public void setBrthDt(String brthDt) {
		this.brthDt = brthDt;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getMbrStt() {
		return mbrStt;
	}

	public void setMbrStt(int mbrStt) {
		this.mbrStt = mbrStt;
	}

	public String getSgnupDt() {
		return sgnupDt;
	}

	public void setSgnupDt(String sgnupDt) {
		this.sgnupDt = sgnupDt;
	}

	public int getPnlty() {
		return pnlty;
	}

	public void setPnlty(int pnlty) {
		this.pnlty = pnlty;
	}

	public int getIsQt() {
		return isQt;
	}

	public void setIsQt(int isQt) {
		this.isQt = isQt;
	}

	public String getQtDt() {
		return qtDt;
	}

	public void setQtDt(String qtDt) {
		this.qtDt = qtDt;
	}

	public String getAccntNum() {
		return accntNum;
	}

	public void setAccntNum(String accntNum) {
		this.accntNum = accntNum;
	}

	public String getAccntVldDt() {
		return accntVldDt;
	}

	public void setAccntVldDt(String accntVldDt) {
		this.accntVldDt = accntVldDt;
	}

	public String getCmpId() {
		return cmpId;
	}

	public void setCmpId(String cmpId) {
		this.cmpId = cmpId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getLtstLgnFailDt() {
		return ltstLgnFailDt;
	}

	public void setLtstLgnFailDt(String ltstLgnFailDt) {
		this.ltstLgnFailDt = ltstLgnFailDt;
	}

	public String getLtstLgnIp() {
		return ltstLgnIp;
	}

	public void setLtstLgnIp(String ltstLgnIp) {
		this.ltstLgnIp = ltstLgnIp;
	}

	public String getLtstLgnSccssDt() {
		return ltstLgnSccssDt;
	}

	public void setLtstLgnSccssDt(String ltstLgnSccssDt) {
		this.ltstLgnSccssDt = ltstLgnSccssDt;
	}

	public int getMbrCtgry() {
		return mbrCtgry;
	}

	public void setMbrCtgry(int mbrCtgry) {
		this.mbrCtgry = mbrCtgry;
	}

	public CompanyVO getCompanyVO() {
		return companyVO;
	}

	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}

	public List<MbrPrmStkVO> getMbrPrmStkList() {
		return mbrPrmStkList;
	}

	public MbrPrmStkVO getMbrPrmStkVO() {
		return mbrPrmStkVO;
	}

	public void setMbrPrmStkVO(MbrPrmStkVO mbrPrmStkVO) {
		this.mbrPrmStkVO = mbrPrmStkVO;
	}

	public void setMbrPrmStkList(List<MbrPrmStkVO> mbrPrmStkList) {
		this.mbrPrmStkList = mbrPrmStkList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("this.email" + this.emilAddr + "\n");
		sb.append("this.cmpId" + this.cmpId + "\n");
		sb.append("this.mbrNm" + this.mbrNm + "\n");
		sb.append("this.pwd" + this.pwd + "\n");

		return sb.toString();
	}

	public ProjectVO getProjectVO() {
		return projectVO;
	}

	public void setProjectVO(ProjectVO projectVO) {
		this.projectVO = projectVO;
	}

}
