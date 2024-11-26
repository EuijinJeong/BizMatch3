package com.ktdsuniversity.edu.bizmatch.common.exceptions.project;

/**
 * 프로젝트를 삭제할 때 예외 발생 시 catch해주는 핸들러.
 * @author jeong-uijin
 */
public class ProjectDeleteException extends RuntimeException {

	private static final long serialVersionUID = -8799246614048388827L;

	public ProjectDeleteException(String message, String pjId) {
		super(message);
	}
}
