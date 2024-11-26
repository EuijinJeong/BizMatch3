package com.ktdsuniversity.edu.bizmatch.board.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentPaginationVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardModifyCommentVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardModifyVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardPaginationVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardSearchVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardWriteVO;

public interface BoardDao {

	static String NAMESPACE = "com.ktdsuniversity.edu.bizmatch.board.dao.BoardDao";
	
	// 게시글 관련 DAO
	public List<BoardVO> selectBoardList(BoardSearchVO boardSearchVO);
	public int selectBoardCount( );
	public BoardVO selectOneBoard(String id);
	
	public int insertPost(BoardWriteVO boardWirteVO);
	public int updateModifyPost(BoardModifyVO modifyVO);
	
	public int updateIncreaseViews(String id);
	public int updateDeletePost(String id);
	
	public List<BoardVO> selectForPagination (BoardPaginationVO boardPaginationVO);
	
	
	// 댓글 관련 DAO
	public List<BoardCommentVO> selectAllBoardComment(String id);
	public List<BoardCommentVO> selectPaginationComment(BoardCommentPaginationVO boardCommentPaginationVO);
	
	public int insertBoardComment (BoardCommentWriteVO boardCommentWriteVO);
	public int updateBoardComment (BoardModifyCommentVO boardModifyCommentVO);
	public int updateDeleteState (String id) ;
}
