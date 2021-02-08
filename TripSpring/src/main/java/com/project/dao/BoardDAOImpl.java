package com.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.project.domain.BoardBean;
import com.project.domain.PageBean;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Inject
	private SqlSession sqlSession;
	private static final String namespace="com.project.mapper.BoardMapper";
	
	@Override
	public List<BoardBean> getBoardList(PageBean pbBean) {
		return sqlSession.selectList(namespace+".getBoardList",pbBean);
	}

	@Override
	public Integer getBoardCount() {
		return sqlSession.selectOne(namespace+".getBoardCount");
	}

	@Override
	public void insertBoard(BoardBean bb) {
		sqlSession.insert(namespace+".insertBoard",bb);
	}

	@Override
	public Integer getMaxNum() {
		return sqlSession.selectOne(namespace+".getMaxNum");
	}

	@Override
	public void updateReadcount(int num) {
		sqlSession.update(namespace+".updateReadcount",num);
	}

	@Override
	public BoardBean getBoard(int num) {
		return sqlSession.selectOne(namespace+".getBoard", num);
	}

	@Override
	public BoardBean numCheck(BoardBean bb) {
		return sqlSession.selectOne(namespace+".numCheck", bb);
	}

	@Override
	public void updateBoard(BoardBean bb) {
		sqlSession.update(namespace+".updateBoard", bb);
	}

	@Override
	public void deleteBoard(BoardBean bb) {
		sqlSession.delete(namespace+".deleteBoard",bb);
	}

	@Override
	public void updateCcount(int comment_bnum,int amount) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("comment_bnum", comment_bnum);
		paramMap.put("amount", amount);
		
		sqlSession.update(namespace+".updateCcount",paramMap);	
	}

//	@Override
//	public void updateRe_seq(BoardBean bb) {
//		sqlSession.update(namespace+".updateRe_seq",bb);
//	}
//
//	@Override
//	public void fupdateBoard(BoardBean bb) {
//		sqlSession.update(namespace+".fupdateBoard",bb);
//	}

}
