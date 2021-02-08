package com.project.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.project.domain.GCommentBean;

@Repository
public class GCommentDAOImpl implements GCommentDAO{
	@Inject
	private SqlSession sqlSession;
	private static final String namespace="com.project.mapper.GCommentMapper";
	
	@Override
	public List<GCommentBean> getCommentList(int num) {
		return sqlSession.selectList(namespace+".getCommentList",num);
	}

	@Override
	public void insertComment(GCommentBean cb) {
		sqlSession.insert(namespace+".insertComment",cb);
	}

	@Override
	public Integer getMaxNum(GCommentBean cb) {
		return sqlSession.selectOne(namespace+".getMaxNum",cb);
	}

	@Override
	public void updateComment(GCommentBean cb) {
		sqlSession.update(namespace+".updateComment", cb);
	}

	@Override
	public void deleteComment(GCommentBean cb) {
		sqlSession.delete(namespace+".deleteComment",cb);
	}

	@Override
	public int getCommentCount(int num) {
		return sqlSession.selectOne(namespace+".getCommentCount",num);
	}

}
