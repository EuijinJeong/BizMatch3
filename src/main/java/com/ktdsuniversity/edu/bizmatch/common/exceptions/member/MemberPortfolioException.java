package com.ktdsuniversity.edu.bizmatch.common.exceptions.member;

/**
 * 회원의 포트폴리오 관하여 오류가 발생하는 것들을 catch해주는 컨트롤러.
 */
public class MemberPortfolioException extends RuntimeException {

	private static final long serialVersionUID = 3764136304270640122L;

	public MemberPortfolioException(String message) {
		super(message);
	}
}
