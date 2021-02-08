package com.project.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.project.domain.CommentBean;
import com.project.domain.PageBean;

@Repository
public class CommentDAOImpl implements CommentDAO{
	@Inject
	private SqlSession sqlSession;
	private static final String namespace="com.project.mapper.CommentMapper";
	
	@Override
	public List<CommentBean> getCommentList(int num) {
		return sqlSession.selectList(namespace+".getCommentList",num);
	}

//	@Override
//	public Integer getCommentCount() {
//		return sqlSession.selectOne(namespace+".getCommentCount");
//	}

	@Override
	public void insertComment(CommentBean cb) {
		sqlSession.insert(namespace+".insertComment",cb);
	}

	@Override
	public Integer getMaxNum(CommentBean cb) {
		return sqlSession.selectOne(namespace+".getMaxNum",cb);
	}

//	@Override
//	public void updateReadcount(int num) {
//		sqlSession.update(namespace+".updateReadcount",num);
//	}
//
//	@Override
//	public CommentBean getComment(int num) {
//		return sqlSession.selectOne(namespace+".getComment", num);
//	}
//
//	@Override
//	public CommentBean numCheck(CommentBean cb) {
//		return sqlSession.selectOne(namespace+".numCheck", cb);
//	}

	@Override
	public void updateComment(CommentBean cb) {
		sqlSession.update(namespace+".updateComment", cb);
	}

	@Override
	public void deleteComment(CommentBean cb) {
		sqlSession.delete(namespace+".deleteComment",cb);
	}

	@Override
	public int getCommentCount(int num) {
		return sqlSession.selectOne(namespace+".getCommentCount",num);
	}

//	@Override
//	public void updateRe_seq(CommentBean cb) {
//		sqlSession.update(namespace+".updateRe_seq",cb);
//	}
//
//	@Override
//	public void fupdateComment(CommentBean cb) {
//		sqlSession.update(namespace+".fupdateComment",cb);
//	}

}
