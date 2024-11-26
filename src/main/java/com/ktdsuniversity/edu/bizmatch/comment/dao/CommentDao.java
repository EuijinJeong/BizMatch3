package com.ktdsuniversity.edu.bizmatch.comment.dao;

import java.util.List;

import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentModifyVO;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentVO;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.common.vo.PaginationVO;


public interface CommentDao {

	static String NameSpace="com.ktdsuniversity.edu.bizmatch.comment.dao.CommentDao";
	
	public int selectCommentCount(String id);
	public List<CommentVO> selectComment(String id);
	public  int insertNewComment(CommentWriteVO commentWriteVO);
	public int deleteOneComment(String id);
	public int updateOneComment(CommentModifyVO commentModifyVO);
	
	public List<CommentVO> selectForPagination(PaginationVO paginationVO);
}
