package com.ktdsuniversity.edu.bizmatch.member.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.bizmatch.common.category.vo.CategoryVO;

public class MemberSignUpVO {
	// memberSignUpVO
	private String mbrNm;
	private String mbrPhnNum; // 회원 전화번호.
	private String emilAddr; // 이메일주소.
	private String pwd; // 비번.
	private String confirmPwd; // 비밀번호 확인
	private Address addr; // 회원 주소.
	private String brthDt; // 생년월일.
	private int mbrStt;
	private List<MultipartFile> fileList; // 사용자가 입력한 파일.
	private int mbrCtgry; // 기업회원인지 일반 회원인지 회원 유형. 
	private String salt; // 소금.
	private String agree1; // 이용약관 동의 1
	private String agree2; // 이용약관 동의 2
	private String agree3; // 이용약관 동의 3
	private String mjrId; // 프리랜서의 주요 산업 아이디.
	private String smjrId; // 프리랜서의 주요 산업 아이디 중분류.
	private CategoryVO lkInSdstrId; // 프리랜서의 관심 산업 정보.
	private boolean isSameAuthNum; // 인증번호 동의 여부.
	private String emilAddrCnfrmNmbr; // 이메일 인증번호 변수.
	
	public int getMbrStt() {
		return mbrStt;
	}
	public void setMbrStt(int mbrStt) {
		this.mbrStt = mbrStt;
	}
	public String getMbrNm() {
		return mbrNm;
	}
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}
	public String getMbrPhnNum() {
		return mbrPhnNum;
	}
	public void setMbrPhnNum(String mbrPhnNum) {
		this.mbrPhnNum = mbrPhnNum;
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
	public Address getAddr() {
		return addr;
	}
	public void setAddr(Address addr) {
		this.addr = addr;
	}
	public String getBrthDt() {
		return brthDt;
	}
	public void setBrthDt(String brthDt) {
		this.brthDt = brthDt;
	}
	public List<MultipartFile> getFileList() {
		return fileList;
	}
	public void setFileList(List<MultipartFile> fileList) {
		this.fileList = fileList;
	}
	public int getMbrCtgry() {
		return mbrCtgry;
	}
	public void setMbrCtgry(int mbrCtgry) {
		this.mbrCtgry = mbrCtgry;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getAgree1() {
		return agree1;
	}
	public void setAgree1(String agree1) {
		this.agree1 = agree1;
	}
	public String getAgree2() {
		return agree2;
	}
	public void setAgree2(String agree2) {
		this.agree2 = agree2;
	}
	public String getAgree3() {
		return agree3;
	}
	public void setAgree3(String agree3) {
		this.agree3 = agree3;
	}
	public boolean isSameAuthNum() {
		return isSameAuthNum;
	}
	public void setSameAuthNum(boolean isSameAuthNum) {
		this.isSameAuthNum = isSameAuthNum;
	}
	public String getEmilAddrCnfrmNmbr() {
		return emilAddrCnfrmNmbr;
	}
	public void setEmilAddrCnfrmNmbr(String emilAddrCnfrmNmbr) {
		this.emilAddrCnfrmNmbr = emilAddrCnfrmNmbr;
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
	public CategoryVO getLkInSdstrId() {
		return lkInSdstrId;
	}
	public void setLkInSdstrId(CategoryVO lkInSdstrId) {
		this.lkInSdstrId = lkInSdstrId;
	}
}
