package com.ktdsuniversity.edu.bizmatch.common.exceptions.member;

/**
 * 회원가입이 승낙이 안됐을 때 회원이 로그인을 시도할 때 발생하는 예외를 처리하는 핸들러.
 */
public class SignUpNotApprovedException extends RuntimeException{

	private static final long serialVersionUID = 3958202169254083910L;
	
	public SignUpNotApprovedException(String message) {
		super(message);
	}

}
