package com.ktdsuniversity.edu.bizmatch.common.email.dao;

import com.ktdsuniversity.edu.bizmatch.common.email.vo.UserEmailAuthNumVO;

public interface EmailDao {
	public String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.common.email.dao.EmailDao";
	
	/**
	 * 사용자가 회원가입을 할 때 발급받는 인증번호를 저장하는 쿼리문 호출 메서드.
	 * 	insert 할 때 자동적으로 시간 추가해줌.
	 * 
	 * @param userEmailAuthNumVO
	 * @return : insert된 값으 컬럼의 개수.
	 * - 작성자: 정의진 -
	 */
	public int insertTempEmailAuthNum(UserEmailAuthNumVO userEmailAuthNumVO);
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public int selectMemberForSignUp(String email);
	/**
	 * 
	 * @param userEmailAuthNumVO
	 * @return
	 */
	public int selectTempEmailAuthNum(UserEmailAuthNumVO userEmailAuthNumVO);
	/**
	 * 
	 * @param email
	 * @return
	 */
	public int deleteTempEmailAuthNum(String email);
}
