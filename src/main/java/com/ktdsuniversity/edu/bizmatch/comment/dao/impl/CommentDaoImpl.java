package com.ktdsuniversity.edu.bizmatch.comment.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.bizmatch.comment.dao.CommentDao;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentModifyVO;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentVO;
import com.ktdsuniversity.edu.bizmatch.comment.vo.CommentWriteVO;
import com.ktdsuniversity.edu.bizmatch.common.vo.PaginationVO;

@Repository
public class CommentDaoImpl extends SqlSessionDaoSupport implements CommentDao{


	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	
	@Override
	public int selectCommentCount(String id) {
		return getSqlSession().selectOne(NameSpace + ".selectCommentCount" , id);
	}

	@Override
	public List<CommentVO> selectComment(String id) {
		return  getSqlSession().selectList(NameSpace + ".selectComment" , id);
	}

	@Override
	public int insertNewComment(CommentWriteVO commentWriteVO) {
		return getSqlSession().insert(NameSpace+".insertNewComment", commentWriteVO);
	}

	@Override
	public int deleteOneComment(String id) {
		return getSqlSession().update(NameSpace + ".deleteOneComment", id);
	}

	@Override
	public int updateOneComment(CommentModifyVO commentModifyVO) {
		return getSqlSession().update(NameSpace + ".updateOneComment", commentModifyVO);
	}


	@Override
	public List<CommentVO> selectForPagination(PaginationVO paginationVO) {
		
		return getSqlSession().selectList(NameSpace+".selectForPagination" , paginationVO);
	}

}
