package com.ktdsuniversity.edu.bizmatch.member.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MemberPortfolioVO {
	// memberPortfolioVO
	
	private String mbrPrtflId; // 회원 포트폴리오 아이디 값.
	private String mbrPrtflTtl; // 회원 포트폴리오 제목.
	private String mbrPrtflText; // 회원 포트폴리오 소개글.
	private String emilAddr; // 회원의 이메일 주소.
	private List<MultipartFile> attList; // 포트폴리오 첨부자료 리스트.
	private List<MemberPortfolioAttVO> attVOs; // 포트폴리오 암호화한 정보가 들어있는 객체 리스트.
	
	// getter and setter
	public String getMbrPrtflId() {
		return mbrPrtflId;
	}
	public void setMbrPrtflId(String mbrPrtflId) {
		this.mbrPrtflId = mbrPrtflId;
	}
	public String getMbrPrtflTtl() {
		return mbrPrtflTtl;
	}
	public void setMbrPrtflTtl(String mbrPrtflTtl) {
		this.mbrPrtflTtl = mbrPrtflTtl;
	}
	public String getMbrPrtflText() {
		return mbrPrtflText;
	}
	public void setMbrPrtflText(String mbrPrtflText) {
		this.mbrPrtflText = mbrPrtflText;
	}
	public String getEmilAddr() {
		return emilAddr;
	}
	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}	
	public List<MultipartFile> getAttList() {
		return attList;
	}
	public void setAttList(List<MultipartFile> attList) {
		this.attList = attList;
	}
	public List<MemberPortfolioAttVO> getAttVOs() {
		return attVOs;
	}
	public void setAttVOs(List<MemberPortfolioAttVO> attVOs) {
		this.attVOs = attVOs;
	}
}
