package com.ktdsuniversity.edu.bizmatch.project.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class WriteProjectVO {
	
	private String pjId; // 프로젝트 아이디.
	private String emilAddr; // 이메일주소.
	private String ordrId; // 
	private String indstrId; // 산업군 아이디
	private String indstrNm; // 산업군 명
	private String prmStkId; // 주요기술 아이디
	private String rgstrDt; // 프로젝트 등록 일자
	private String pjInstrId; // 프로젝트 산업분야 아이디
	private String pjTtl; // 프로젝트 제목
	private String strtDt; // 프로젝트 시작일
	private String endDt; // 프로젝트 종료일
	private String pjDesc; // 프로젝트 상세설명
	private Integer cntrctAccnt; // 프로젝트 입찰가격
	private String pjAttUrl; // 프로젝트 등록 첨부자료 원래이름
	private String pjAttNonread; // 첨부자료 난독화 파일이름
	private int pjRcrutCnt; // 프로젝트 모집인원
	private String pjRcrutStrtDt; // 프로젝트 모집 시작일
	private String pjRcrutEndDt; // 프로젝트 모집 종료일
	private int grntAmt; // 보증금
	private String accntNm; //계좌정보
	private String ip; // 아이피 정보.
	private List<MultipartFile> fileList; // 사용자가 입력한 파일
	private String firstIndstrId; // 상위 산업군 아이디
	private String secondIndstrId; // 상위 산업군 아이디
	
	public int getGrntAmt() {
		return grntAmt;
	}
	public void setGrntAmt(int grntAmt) {
		this.grntAmt = grntAmt;
	}
	public String getAccntNm() {
		return accntNm;
	}
	public void setAccntNm(String accntNm) {
		this.accntNm = accntNm;
	}
	public String getPjId() {
		return pjId;
	}
	public void setPjId(String pjId) {
		this.pjId = pjId;
	}
	public String getEmilAddr() {
		return emilAddr;
	}
	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}
	public String getOrdrId() {
		return ordrId;
	}
	public void setOrdrId(String ordrId) {
		this.ordrId = ordrId;
	}
	public String getIndstrId() {
		return indstrId;
	}
	public void setIndstrId(String indstrId) {
		this.indstrId = indstrId;
	}
	public String getIndstrNm() {
		return indstrNm;
	}
	public void setIndstrNm(String indstrNm) {
		this.indstrNm = indstrNm;
	}
	
	public String getRgstrDt() {
		return rgstrDt;
	}
	public void setRgstrDt(String rgstrDt) {
		this.rgstrDt = rgstrDt;
	}
	public String getPjInstrId() {
		return pjInstrId;
	}
	public void setPjInstrId(String pjInstrId) {
		this.pjInstrId = pjInstrId;
	}
	public String getPjTtl() {
		return pjTtl;
	}
	public void setPjTtl(String pjTtl) {
		this.pjTtl = pjTtl;
	}
	public String getStrtDt() {
		return strtDt;
	}
	public void setStrtDt(String strtDt) {
		this.strtDt = strtDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getPjDesc() {
		return pjDesc;
	}
	public void setPjDesc(String pjDesc) {
		this.pjDesc = pjDesc;
	}
	public Integer getCntrctAccnt() {
		return cntrctAccnt;
	}
	public void setCntrctAccnt(Integer cntrctAccnt) {
		this.cntrctAccnt = cntrctAccnt;
	}
	public String getPjAttUrl() {
		return pjAttUrl;
	}
	public void setPjAttUrl(String pjAttUrl) {
		this.pjAttUrl = pjAttUrl;
	}
	public String getPjAttNonread() {
		return pjAttNonread;
	}
	public void setPjAttNonread(String pjAttNonread) {
		this.pjAttNonread = pjAttNonread;
	}
	public int getPjRcrutCnt() {
		return pjRcrutCnt;
	}
	public void setPjRcrutCnt(int pjRcrutCnt) {
		this.pjRcrutCnt = pjRcrutCnt;
	}
	public String getPjRcrutStrtDt() {
		return pjRcrutStrtDt;
	}
	public void setPjRcrutStrtDt(String pjRcrutStrtDt) {
		this.pjRcrutStrtDt = pjRcrutStrtDt;
	}
	public String getPjRcrutEndDt() {
		return pjRcrutEndDt;
	}
	public void setPjRcrutEndDt(String pjRcrutEndDt) {
		this.pjRcrutEndDt = pjRcrutEndDt;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public List<MultipartFile> getFileList() {
		return fileList;
	}
	public void setFileList(List<MultipartFile> fileList) {
		this.fileList = fileList;
	}
	public String getPrmStkId() {
		return prmStkId;
	}
	public void setPrmStkId(String prmStkId) {
		this.prmStkId = prmStkId;
	}
	public String getFirstIndstrId() {
		return firstIndstrId;
	}
	public void setFirstIndstrId(String firstIndstrId) {
		this.firstIndstrId = firstIndstrId;
	}
	public String getSecondIndstrId() {
		return secondIndstrId;
	}
	public void setSecondIndstrId(String secondIndstrId) {
		this.secondIndstrId = secondIndstrId;
	}	
	
}