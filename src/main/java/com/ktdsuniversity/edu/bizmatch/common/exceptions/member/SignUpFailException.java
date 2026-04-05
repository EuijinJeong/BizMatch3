package com.ktdsuniversity.edu.bizmatch.common.exceptions.member;

import com.ktdsuniversity.edu.bizmatch.member.vo.MemberSignUpVO;

public class SignUpFailException extends RuntimeException {

	private static final long serialVersionUID = 3596880578291996715L;
	
	private MemberSignUpVO memberSignUpVO;
	
	public SignUpFailException(String message, MemberSignUpVO memberSignUpVO) {
		super(message);
		this.memberSignUpVO = memberSignUpVO;
	}
	public MemberSignUpVO getMemberSignUpVO() {
		return memberSignUpVO;
	}
}
