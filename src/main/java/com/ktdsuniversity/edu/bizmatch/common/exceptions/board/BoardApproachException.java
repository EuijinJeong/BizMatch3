package com.ktdsuniversity.edu.bizmatch.common.exceptions.board;

/**
 * 접근 권한이 없는 게시글 또는 페이지를 볼 때 발생하는 예외를 처리해준다.
 * @author jeong-uijin
 */
public class BoardApproachException extends RuntimeException {

	private static final long serialVersionUID = 1883478316771331293L;

	public BoardApproachException(String message) {
		super(message);
	}
}
