package com.ktdsuniversity.edu.bizmatch.common.exceptions.member;

import com.ktdsuniversity.edu.bizmatch.member.vo.MemberCompanySignUpVO;

public class SignUpCompanyException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -869830785792309242L;
	
	private MemberCompanySignUpVO memberCompanySignUpVO;
	
	public SignUpCompanyException(String message , MemberCompanySignUpVO memberCompanySignUpVO) {
		super(message);
		this.memberCompanySignUpVO = memberCompanySignUpVO;
	}
	public MemberCompanySignUpVO getMemberCompanySignUpVO() {
		return this.memberCompanySignUpVO;
	}

}
