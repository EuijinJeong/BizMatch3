 package com.ktdsuniversity.edu.bizmatch.member.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.bizmatch.common.category.vo.CategoryVO;

public class MemberCompanySignUpVO {
	// memberCompanySignUpVO
	private String cmpId;
	private String mbrNm; //이용자명.
	private String emilAddr; // 이메일 주소.
	private String pwd; // 비밀번호.
	private String confirmPwd; // 비밀번호 확인.
	private String cmpnyBrn; // 사업자번호.
	private Integer cmpnyEmplyCnt; // 직원 수.
	private String cmpnyNm; // 회사 이름.
	private String mbrPhnNum; //이용자 전화번호 
	private Address address;
	private String cmpnyPhnNum; // 회사 전화번호.
	private String cmpnySiteUrl; // 회사 사이트 주소.
	private String cmpnyBizCtgry; // 개인사업자(1) / 법인사업자(0)
	private CategoryVO cmpnyIndstrId; // 회사 사업 분야 아이디(fk).
	private CategoryVO cmpnyIntrstdIndstrId; // 회사의 관심 산업 분야 아이디.
	private int mbrCtgry; // 기업회원인지 일반 회원인지 회원 유형.
	private String agreeOne; //첫 번째 이용 약관 동의 
	private String agreeTwo; //두 번째 이용 약관 동의
	private String agreeThree; //세 번째 이용 약관 동의
	private String salt;
	private int cmpnyRp; // 기업 대표자 여부
	private List<MultipartFile> fileList; //이용자 첨부파일
	private String emilAddrCnfrmNmbr; //인증번호확인

	
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
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	public String getCmpnyBrn() {
		return cmpnyBrn;
	}
	public void setCmpnyBrn(String cmpnyBrn) {
		this.cmpnyBrn = cmpnyBrn;
	}
	public Integer getCmpnyEmplyCnt() {
		return cmpnyEmplyCnt;
	}
	public void setCmpnyEmplyCnt(Integer cmpnyEmplyCnt) {
		this.cmpnyEmplyCnt = cmpnyEmplyCnt;
	}
	public String getCmpnyNm() {
		return cmpnyNm;
	}
	public void setCmpnyNm(String cmpnyNm) {
		this.cmpnyNm = cmpnyNm;
	}
	public String getMbrPhnNum() {
		return mbrPhnNum;
	}
	public void setMbrPhnNum(String mbrPhnNum) {
		this.mbrPhnNum = mbrPhnNum;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
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
	public String getCmpnyBizCtgry() {
		return cmpnyBizCtgry;
	}
	public void setCmpnyBizCtgry(String cmpnyBizCtgry) {
		this.cmpnyBizCtgry = cmpnyBizCtgry;
	}
	public CategoryVO getCmpnyIndstrId() {
		return cmpnyIndstrId;
	}
	public void setCmpnyIndstrId(CategoryVO cmpnyIndstrId) {
		this.cmpnyIndstrId = cmpnyIndstrId;
	}
	public CategoryVO getCmpnyIntrstdIndstrId() {
		return cmpnyIntrstdIndstrId;
	}
	public void setCmpnyIntrstdIndstrId(CategoryVO cmpnyIntrstdIndstrId) {
		this.cmpnyIntrstdIndstrId = cmpnyIntrstdIndstrId;
	}
	public int getMbrCtgry() {
		return mbrCtgry;
	}
	public void setMbrCtgry(int mbrCtgry) {
		this.mbrCtgry = mbrCtgry;
	}
	public String getAgreeOne() {
		return agreeOne;
	}
	public void setAgreeOne(String agreeOne) {
		this.agreeOne = agreeOne;
	}
	public String getAgreeTwo() {
		return agreeTwo;
	}
	public void setAgreeTwo(String agreeTwo) {
		this.agreeTwo = agreeTwo;
	}
	public String getAgreeThree() {
		return agreeThree;
	}
	public void setAgreeThree(String agreeThree) {
		this.agreeThree = agreeThree;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public List<MultipartFile> getFileList() {
		return fileList;
	}
	public void setFileList(List<MultipartFile> fileList) {
		this.fileList = fileList;
	}
	public String getEmilAddrCnfrmNmbr() {
		return emilAddrCnfrmNmbr;
	}
	public void setEmilAddrCnfrmNmbr(String emilAddrCnfrmNmbr) {
		this.emilAddrCnfrmNmbr = emilAddrCnfrmNmbr;
	}
	// getters and setters.
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("this.mbrNm"+this.mbrNm+"\n");
		sb.append("this.emilAddr"+this.emilAddr+"\n");
		sb.append(this.cmpnyIndstrId.getMjrId() + this.cmpnyIndstrId.getSmjrId() + "\n");
		sb.append("this.pwd"+this.pwd+"\n");
		sb.append("this.confirmPwd"+this.confirmPwd+"\n");
		sb.append("this.emilAddrCnfrmNmbr"+this.emilAddrCnfrmNmbr+"\n");
		sb.append("this.cmpnyBrn"+this.cmpnyBrn+"\n");
		sb.append("this.cmpnyEmplyCnt"+this.cmpnyEmplyCnt+"\n");
		sb.append("this.mbrPhnNum"+this.mbrPhnNum+"\n");
		sb.append("this.cmpnyNm"+this.cmpnyNm+"\n");
		sb.append("this.getAddress.getpostcode"+this.getAddress().getPostcode() + " "+this.getAddress().getAddr());
		sb.append("this.cmpnyPhnNum"+this.cmpnyPhnNum+"\n");
		sb.append("this.cmpnySiteUrl"+this.cmpnySiteUrl+"\n");
		sb.append("this.cmpnyBizCtgry"+this.cmpnyBizCtgry+"\n");
		sb.append("this.cmpnyIndstrId"+this.cmpnyIndstrId+"\n");
		sb.append("this.mbrCtgry"+this.mbrCtgry+"\n");
		sb.append("this.agreeOne"+this.agreeOne+"\n");
		sb.append("this.agreeTwo"+this.agreeTwo+"\n");
		sb.append("this.agreeThree"+this.agreeThree);
		return sb.toString();
	}
	public String getCmpId() {
		return cmpId;
	}
	public void setCmpId(String cmpId) {
		this.cmpId = cmpId;
	}
}
