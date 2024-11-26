package com.ktdsuniversity.edu.bizmatch.common.exceptions.payment;

public class PaymentException extends RuntimeException{

	private static final long serialVersionUID = -4907953931477615052L;
	
	public PaymentException(String message) {
		super(message);
	}
}
