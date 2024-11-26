package com.ktdsuniversity.edu.bizmatch.comment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.bizmatch.comment.dao.CommentDao;
import com.ktdsuniversity.edu.bizmatch.comment.service.CommentService;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentModifyVO;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentVO;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.common.vo.PaginationVO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;
	
	@Override
	public boolean updateDeleteState(String id) {
		int result = commentDao.deleteOneComment(id);
		return result>0;
	}

	
	
	@Override
	public List<CommentVO> getComment(PaginationVO paginationVO, String id) {
		List<CommentVO> comments ;
		
		if (paginationVO == null) {
			comments = commentDao.selectComment(id);
			
		} else {
			paginationVO.setSearchIdParam(id);
	
			comments = commentDao.selectForPagination(paginationVO);
		}

		
		return comments;
	}

	@Override
	public int getCommentCount(String id) {
		int commentCount = commentDao.selectCommentCount(id);
		return commentCount;
	}

	@Override
	public boolean createNewComment(CommentWriteVO commentWriteVO) {
		int reuslt = commentDao.insertNewComment(commentWriteVO);
		return reuslt>0;
	}

	@Override
	public boolean modifyComment(CommentModifyVO commentModifyVO) {
		int reuslt = commentDao.updateOneComment(commentModifyVO);
		return reuslt>0;
	}



	@Override
	public List<CommentVO> getAllComment(String id) {
		List<CommentVO> comments = commentDao.selectComment(id);;
		return comments;
	}

	

}
