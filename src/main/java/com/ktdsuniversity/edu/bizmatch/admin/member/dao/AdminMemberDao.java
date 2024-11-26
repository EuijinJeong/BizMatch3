package com.ktdsuniversity.edu.bizmatch.admin.member.dao;

import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

public interface AdminMemberDao {
	
	public String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.admin.member.dao.AdminMemberDao";
	
	/**
	 * 심사중인 회원의 가입 상태를 활성화 상태로 업데이트 시켜준다.
	 * @return
	 */
	public int updateOneMemberStt(String email);
	
	/**
	 * 한 회원의 모든 정보를 조회하는 쿼리문을 호출하는 메서드.
	 * @param email
	 * @return
	 */
	public MemberVO selectOneMember(String email);
}
