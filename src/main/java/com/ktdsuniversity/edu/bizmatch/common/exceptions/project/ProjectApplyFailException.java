package com.ktdsuniversity.edu.bizmatch.common.exceptions.project;

/**
 * 프로젝트를 지원하다가 생긴 에러를 처리하는 예외 핸들러.
 * @author jeong-uijin
 */
public class ProjectApplyFailException extends RuntimeException {

	private static final long serialVersionUID = 4957251086330914808L;

	public ProjectApplyFailException(String message, Object object) {
		super(message);
	}
}
