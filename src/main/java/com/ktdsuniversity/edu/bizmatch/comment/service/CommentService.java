package com.ktdsuniversity.edu.bizmatch.comment.service;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentModifyVO;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentVO;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.common.vo.PaginationVO;

public interface CommentService {

	
	public boolean updateDeleteState(String id);
	public List<CommentVO> getComment(PaginationVO paginationVO,String id);
	public int getCommentCount(String id);
	public boolean createNewComment(CommentWriteVO commentWriteVO);
	public boolean modifyComment(CommentModifyVO commentModifyVO);

	public List<CommentVO> getAllComment(String id);
	
}
