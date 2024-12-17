package com.ktdsuniversity.edu.bizmatch.board.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.bizmatch.board.dao.BoardDao;
import com.ktdsuniversity.edu.bizmatch.board.service.BoardService;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardCommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardModifyCommentVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardModifyVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardVO;
import com.ktdsuniversity.edu.bizmatch.board.vo.BoardWriteVO;
import com.ktdsuniversity.edu.bizmatch.comment.web.CommentController;
import com.ktdsuniversity.edu.bizmatch.common.exceptions.board.BoardException;

@Service
public class BoardServiceImpl implements BoardService {
	public static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public List<BoardVO> getBoardList() {
		List<BoardVO> result = boardDao.selectBoardList();
		return result;
	}

	@Override
	public BoardVO getOneBoard(String id) {
		BoardVO result = boardDao.selectOneBoard(id);
		if(result == null) {
			throw new BoardException("서버상의 이유로 해당 게시글의 정보를 불러올 수 없습니다.");
		}
		
		List<BoardCommentVO> commentList = this.boardDao.selectAllBoardComment(id);
		result.setCommentList(commentList);
		
		return result;
	}

	@Override
	public boolean createNewPost(BoardWriteVO boardWirteVO) {
		return boardDao.insertPost(boardWirteVO) > 0;
	}

	@Override
	public boolean doModifyPost(BoardModifyVO modifyVO) {
		return boardDao.updateModifyPost(modifyVO)>0;
	}

	@Override
	public boolean doIncreaseViews(String id) {
		return boardDao.updateIncreaseViews(id) > 0;
	}

	@Override
	public boolean doDeletePost(String id) {
		return boardDao.updateDeletePost(id)>0;
	}

	// 이하 댓글 관련 서비스
	@Override
	public List<BoardCommentVO> getAllBoardComment(String id) {
		List<BoardCommentVO> result = boardDao.selectAllBoardComment(id);
		return result;
	}

	@Override
	public boolean createBoardComment(BoardCommentWriteVO boardCommentWriteVO) {
		return boardDao.insertBoardComment(boardCommentWriteVO) >0;
	}

	@Override
	public boolean modifyBoardComment(BoardModifyCommentVO boardModifyCommentVO) {
		return boardDao.updateBoardComment(boardModifyCommentVO)>0;
	}

	@Override
	public boolean fixDeleteState(String id) {
		return boardDao.updateDeleteState(id) >0;
	}

}
