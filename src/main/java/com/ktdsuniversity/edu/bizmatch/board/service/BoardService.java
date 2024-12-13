package com.ktdsuniversity.edu.bizmatch.board.service;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardModifyCommentVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardModifyVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardWriteVO;

public interface BoardService {
	
	/**
	 * 모든 게시글의 리스트를 조회하는 메서드.
	 * @return
	 */
	public List<BoardVO> getBoardList();
	
	/**
	 * 하나의 게시글을 가져오는 메서드.
	 * @param id
	 * @return
	 */
	public BoardVO getOneBoard(String id);
	
	/**
	 * 새로운 게시글을 작성하는 메서드.
	 * @param boardWirteVO
	 * @return
	 */
	public boolean createNewPost(BoardWriteVO boardWirteVO);

	/**
	 * 특정 게시글을 수정하는 메서드.
	 * @param modifyVO
	 * @return
	 */
	public boolean doModifyPost(BoardModifyVO modifyVO);
	
	/**
	 * 게시글의 조회수를 증가시키는 메서드.
	 * @param id
	 * @return
	 */
	public boolean doIncreaseViews(String id);
	
	/**
	 * 특정 게시글을 삭제하는 메서드.
	 * @param id
	 * @return
	 */
	public boolean doDeletePost(String id);
	
	/**
	 * 특정 게시글에 달린 댓글 목록들을 가져오는 메서드.
	 * @param id
	 * @return
	 */
	public List<BoardCommentVO> getAllBoardComment(String id);
	
	/**
	 * 댓글을 작성하는 메서드.
	 * @param boardCommentWriteVO
	 * @return
	 */
	public boolean createBoardComment(BoardCommentWriteVO boardCommentWriteVO);

	/**
	 * 특정 댓글을 수정하는 메서드.
	 * @param boardModifyCommentVO
	 * @return
	 */
	public boolean modifyBoardComment (BoardModifyCommentVO boardModifyCommentVO);
	
	public boolean fixDeleteState (String id) ;
}
