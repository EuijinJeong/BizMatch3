package com.ktdsuniversity.edu.bizmatch.common.exceptions.project;

/**
 * 프로젝트 스크랩을 하다가 오류가 발생하는 예외 클래스.
 * @author jeong-uijin
 */
public class ProjectScrapException extends RuntimeException {

	private static final long serialVersionUID = 4807399755117485518L;

	public ProjectScrapException(String message, Object object) {
		super(message);
	}
}
