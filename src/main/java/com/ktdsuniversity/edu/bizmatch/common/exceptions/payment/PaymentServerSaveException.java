package com.ktdsuniversity.edu.bizmatch.common.exceptions.payment;

/**
 * 결제 요청을 성공적으로 끝냈지만 서버에 결제 정보를 저장하는 도중,
 * 오류가 발생하면 예외를 처리해주는 핸들러이다.
 * @author jeong-uijin
 */
public class PaymentServerSaveException extends RuntimeException{

	private static final long serialVersionUID = 786997697290598420L;

	public PaymentServerSaveException(String message) {
		super(message);
	}
}
