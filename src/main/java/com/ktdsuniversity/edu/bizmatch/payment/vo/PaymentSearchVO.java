package com.ktdsuniversity.edu.bizmatch.payment.vo;

public class PaymentSearchVO {
	private String emilAddr; //검색하려는 사람의 이메일
	private String startDate; // 언제부터 시작한 결제내역
	private String companyName; // 내가 결제한 회사의 이름
	private String projectTitle; // 내가 결제한 프로젝트 제목
	private String paymentType; // 보증금(0) 결제인지 / 계약금(1) 결제인지
	
	public String getEmilAddr() {
		return emilAddr;
	}
	public void setEmilAddr(String emilAddr) {
		this.emilAddr = emilAddr;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
}
