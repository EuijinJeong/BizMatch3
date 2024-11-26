package com.ktdsuniversity.edu.bizmatch.admin.member.service;

public interface AdminMemberService {

	/**
	 * 회원의 회원가입 상태를 관리하는 메서드.
	 * @param email
	 * @return
	 */
	public boolean handleMemberSignUp(String email);
	
}
