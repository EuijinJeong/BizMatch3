package com.ktdsuniversity.edu.bizmatch.common.exceptions.board;

import com.ktdsuniversity.edu.bizmatch.board.vo.BoardWriteVO;

public class BoardException extends RuntimeException {

	private static final long serialVersionUID = -973429567941032222L;
	private BoardWriteVO boardWriteVO;
	public BoardException(String message, BoardWriteVO boardWriteVO) {
		super(message);
		this.boardWriteVO = boardWriteVO;
	}
	public BoardWriteVO getBoardWriteVO() {
		return boardWriteVO;
	}
}
