package com.ktdsuniversity.edu.bizmatch.board.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardModifyCommentVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardModifyVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardWriteVO;

public interface BoardDao {

	static String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.board.dao.BoardDao";
	
	/**
	 * 전체 게시글의 목록을 조회하는 쿼리문을 호출하는 메서드.
	 * @return
	 */
	public List<BoardVO> selectBoardList();
	
	/**
	 * 특정 게시글을 가져오는 쿼리문을 호출하는 메서드.
	 * @param id
	 * @return
	 */
	public BoardVO selectOneBoard(String id);
	
	/**
	 * 새로운 게시글을 추가하는 메서드.
	 * @param boardWirteVO
	 * @return
	 */
	public int insertPost(BoardWriteVO boardWirteVO);

	/**
	 * 특정 게시글을 수정하는 메서드.
	 * @param modifyVO
	 * @return
	 */
	public int updateModifyPost(BoardModifyVO modifyVO);
	
	/**
	 * 조회수를 증가시키는 쿼리문을 호출하는 메서드.
	 * @param id
	 * @return
	 */
	public int updateIncreaseViews(String id);
	
	/**
	 * 게시글을 삭제 상태로 업데이트해주는 쿼리문을 호출하는 메서드.
	 * @param id
	 * @return
	 */
	public int updateDeletePost(String id);
	
//	public List<BoardVO> selectForPagination (BoardPaginationVO boardPaginationVO);
	
	/**
	 * 특정 게시물에 존재하는 모든 댓글을 조회하는 쿼리문을 호출하는 메서드.
	 * @param id
	 * @return
	 */
	public List<BoardCommentVO> selectAllBoardComment(String id);

//	public List<BoardCommentVO> selectPaginationComment(BoardCommentPaginationVO boardCommentPaginationVO);
	
	/**
	 * 댓글을 추가하는 쿼리문을 호출하는 메서드.
	 * @param boardCommentWriteVO
	 * @return
	 */
	public int insertBoardComment (BoardCommentWriteVO boardCommentWriteVO);
	
	/**
	 * 댓글을 수정하는 쿼리문을 호출하는 메서드.
	 * @param boardModifyCommentVO
	 * @return
	 */
	public int updateBoardComment (BoardModifyCommentVO boardModifyCommentVO);
	
	/**
	 * 댓글을 삭제 상태로 업데이트하는 쿼리문을 호출하는 메서드.
	 * @param id
	 * @return
	 */
	public int updateDeleteState (String id);
}
