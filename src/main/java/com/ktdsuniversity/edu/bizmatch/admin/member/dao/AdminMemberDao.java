package com.ktdsuniversity.edu.bizmatch.admin.member.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.member.vo.MemberVO;

public interface AdminMemberDao {
	
	public String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.admin.member.dao.AdminMemberDao";
	
	/**
	 * 여러명의 회원의 가입 상태를 활성화 상태로 업데이트 시켜주는 쿼리문을 호출하는 메서드.
	 * @param email
	 * @return
	 */
	public int updateMemberSttActive(List<String> email);
	
	/**
	 * 회원을 회원 목록에서 지우는 쿼리문을 호출하는 메서드.
	 * @param email
	 * @return
	 */
	public int deleteMember(List<String> email);
	
	/**
	 * 이메일을 통해 회원 정보를 조회한다.
	 * @param email
	 * @return
	 */
	public MemberVO selectOneMember(String email);
	
	/**
	 * 전체 회원의 정보를 조회하는 쿼리문을 호출하는 메서드.
	 * @return
	 */
	public List<MemberVO> selectMemberList();
}
