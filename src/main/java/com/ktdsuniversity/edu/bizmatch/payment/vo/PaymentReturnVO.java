package com.ktdsuniversity.edu.bizmatch.payment.vo;

/**
 * 프로젝트 완료시 계약금 다시 돌려줘야 할 때 사용하는 객체.
 * @author jeong-uijin
 */
public class PaymentReturnVO {
	private String account; // 돈을 돌려줘야하는 상대방의 계좌정보.
	private int amount; // 돌려줄 돈의 금액. 
	private String bizAccount; // 우리 계좌 정보...
	
	
	public String getBizAccount() {
		return bizAccount;
	}
	public void setBizAccount(String bizAccount) {
		this.bizAccount = bizAccount;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
