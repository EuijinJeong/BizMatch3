package com.ktdsuniversity.edu.bizmatch.common.exceptions.member;

public class SessionNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7429015770658593722L;

	public SessionNotFoundException(String message) {
		super(message);
	}
}
