 package com.ktdsuniversity.edu.bizmatch.member.vo;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.common.skills.vo.MbrPrmStkVO;

/**
 * 이 클래스는 기업 정보에 관한 VO 클래스입니다.
 * 데이터베이스 테이블 CMPNY_INFO와 동일합니다.
 * 
 * @author jeong-uijin
 */
public class CompanyVO {
	private String cmpnyId;
	private String cmpnyNm;
	private String cmpnyBizCtgry;
	private String mjrId;
	private String cmpnyAddr;
	private String cmpnyPhnNum;
	private String cmpnySiteUrl;
	private int cmpnyEmplyCnt;
	private MemberVO memberVO;
	private String cmpnyBrn;
	private String smjrId;
	private String cmpnyIntr;
	private String cmpnyAccuuntNum;
	private String compnyLkIndstrMjrNm;
	private String compnyLkIndstrSmjrNm;
	
	private List<MbrPrmStkVO> mbrPrmStkVOList;
	
	
	
	
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public String getCompnyLkIndstrMjrNm() {
		return compnyLkIndstrMjrNm;
	}
	public void setCompnyLkIndstrMjrNm(String compnyLkIndstrMjrNm) {
		this.compnyLkIndstrMjrNm = compnyLkIndstrMjrNm;
	}
	public String getCompnyLkIndstrSmjrNm() {
		return compnyLkIndstrSmjrNm;
	}
	public void setCompnyLkIndstrSmjrNm(String compnyLkIndstrSmjrNm) {
		this.compnyLkIndstrSmjrNm = compnyLkIndstrSmjrNm;
	}
	public List<MbrPrmStkVO> getMbrPrmStkVOList() {
		return mbrPrmStkVOList;
	}
	public void setMbrPrmStkVOList(List<MbrPrmStkVO> mbrPrmStkVOList) {
		this.mbrPrmStkVOList = mbrPrmStkVOList;
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
	public String getCmpnyBizCtgry() {
		return cmpnyBizCtgry;
	}
	public void setCmpnyBizCtgry(String cmpnyBizCtgry) {
		this.cmpnyBizCtgry = cmpnyBizCtgry;
	}
	public String getMjrId() {
		return mjrId;
	}
	public void setMjrId(String mjrId) {
		this.mjrId = mjrId;
	}
	public String getCmpnyAddr() {
		return cmpnyAddr;
	}
	public void setCmpnyAddr(String cmpnyAddr) {
		cmpnyAddr = cmpnyAddr.trim();
		this.cmpnyAddr = cmpnyAddr;
	}
	public String getCmpnyPhnNum() {
		return cmpnyPhnNum;
	}
	public void setCmpnyPhnNum(String cmpnyPhnNum) {
		this.cmpnyPhnNum = cmpnyPhnNum;
	}
	public String getCmpnySiteUrl() {
		return cmpnySiteUrl;
	}
	public void setCmpnySiteUrl(String cmpnySiteUrl) {
		this.cmpnySiteUrl = cmpnySiteUrl;
	}
	public int getCmpnyEmplyCnt() {
		return cmpnyEmplyCnt;
	}
	public void setCmpnyEmplyCnt(int cmpnyEmplyCnt) {
		this.cmpnyEmplyCnt = cmpnyEmplyCnt;
	}
	public String getCmpnyBrn() {
		return cmpnyBrn;
	}
	public void setCmpnyBrn(String cmpnyBrn) {
		this.cmpnyBrn = cmpnyBrn;
	}
	public String getSmjrId() {
		return smjrId;
	}
	public void setSmjrId(String smjrId) {
		this.smjrId = smjrId;
	}
	public String getCmpnyIntr() {
		return cmpnyIntr;
	}
	public void setCmpnyIntr(String cmpnyIntr) {
		this.cmpnyIntr = cmpnyIntr;
	}
	public String getCmpnyAccuuntNum() {
		return cmpnyAccuuntNum;
	}
	public void setCmpnyAccuuntNum(String cmpnyAccuuntNum) {
		this.cmpnyAccuuntNum = cmpnyAccuuntNum;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("회사이름:"+cmpnyNm+"\n");
		sb.append("회사계좌:"+cmpnyAccuuntNum+"\n");
		sb.append("회사아이디:"+cmpnyId+"\n");
		sb.append("회사소개글:"+cmpnyIntr+"\n");
		return sb.toString();
	}
}