package com.ktdsuniversity.edu.bizmatch.common.email.service;

import com.ktdsuniversity.edu.bizmatch.common.email.vo.UserEmailAuthNumVO;

public interface EmailService {
	
	/**
	 * 회원가입에 거절되었다는 이메일을 보내는 메서드.
	 * @param email
	 */
	public void sendEmailForAlertFailSignUp(String email);
	/**
	 * 회원가입 완료됨을 알려주는 이메일을 보내는 메서드.
	 * @param email
	 */
	public void sendEmailForAlertSignUp(String email);
	/**
	 * 비밀번호 찾는 이메일을 보내는 메서드.
	 * @param email : 사용자의 이메일.
	 */
	public void sendEmailForFindPwd(String email);
	
	/**
	 * 회원가입 인증번호 이메일을 보내는 메서드.
	 * @param email : 사용자의 이메일.
	 * @return : 이메일 내용.
	 */
	public String sendEmail(String email);
	
	/**
	 * 이메일 인증번호 데이터베이스에 저장하는 메서드.
	 * @param authNum : 인증번호.
	 * @return : 인증번호 일치 여부.
	 */
	public boolean insertTempEmailAuthNum(UserEmailAuthNumVO userEmailAuthNumVO);
	
	/**
	 * 임시번호가 일치하는지 검사하는 메서드.
	 * @param authNum
	 * @return
	 */
	public boolean isSameTempEmailAuthNum(UserEmailAuthNumVO userEmailAuthNumVO);
	
	/**
	 * 회원가입이 끝나면 사용한 임시번호 테이블에서 지우는 메서드.
	 * @param authNum
	 * @return
	 */
	public void deleteTempEmailAuthNum(String email);

}
