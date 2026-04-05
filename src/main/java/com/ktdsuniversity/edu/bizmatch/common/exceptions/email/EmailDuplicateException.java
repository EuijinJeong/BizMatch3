package com.ktdsuniversity.edu.bizmatch.common.exceptions.email;

public class EmailDuplicateException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7209734998883513824L;
	
	public EmailDuplicateException(String message) {
		super(message);
	}

}
