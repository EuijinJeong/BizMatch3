package com.ktdsuniversity.edu.bizmatch.admin.member.service;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

public interface AdminMemberService {

	/**
	 * 회원가입 시 회원의 정보를 업데이트 해주는 메서드. 
	 * @param email
	 * @return
	 */
	public boolean updateMemberSignupStt(List<String> email);

	/**
	 * 회원가입 거절을 수행하는 메서드.
	 * @param email
	 * @return
	 */
	public boolean updateMemberSignupSttToRefuse(List<String> email);
	
	public List<MemberVO> getNotAssignedMemberList();
}
