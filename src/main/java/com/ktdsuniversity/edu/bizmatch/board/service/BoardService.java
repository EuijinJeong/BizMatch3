package com.ktdsuniversity.edu.bizmatch.board.service;

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

public interface BoardService {
	
	public List<BoardVO> getBoardList(BoardSearchVO BoardsearchVO);
	public int getBoardCount();
	
	public BoardVO getOneBoard(String id);
	
	public boolean createNewPost(BoardWriteVO boardWirteVO);
	public boolean doModifyPost(BoardModifyVO modifyVO);
	
	public boolean doIncreaseViews(String id);
	public boolean doDeletePost(String id);
	
	public List<BoardVO> getForPagination(BoardPaginationVO boardPaginationVO,BoardSearchVO BoardsearchVO);
	
	public List<BoardCommentVO> getAllBoardComment(String id);
	public List<BoardCommentVO> getPaginationComment(BoardCommentPaginationVO boardCommentPaginationVO, String id);
	
	public boolean createBoardComment (BoardCommentWriteVO boardCommentWriteVO);
	public boolean modifyBoardComment (BoardModifyCommentVO boardModifyCommentVO);
	public boolean fixDeleteState (String id) ;
	
}
