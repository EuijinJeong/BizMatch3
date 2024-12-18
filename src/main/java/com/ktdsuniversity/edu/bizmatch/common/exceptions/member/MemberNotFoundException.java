package com.ktdsuniversity.edu.bizmatch.common.exceptions.member;

public class MemberNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4948290607422446841L;
	
	public MemberNotFoundException(String message) {
		super(message);
	}

}
