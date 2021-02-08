package com.project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.project.domain.DownloadBean;
import com.project.domain.PageBean;


@Repository
public class DownloadDAOImpl implements DownloadDAO{
	@Inject
	private SqlSession sqlSession;
	private static final String namespace="com.project.mapper.DownloadMapper";
	
	@Override
	public List<DownloadBean> getDownloadList(PageBean pbBean) {
		return sqlSession.selectList(namespace+".getDownloadList",pbBean);
	}

	@Override
	public Integer getDownloadCount() {
		return sqlSession.selectOne(namespace+".getDownloadCount");
	}

	@Override
	public void insertDownload(DownloadBean db) {
		sqlSession.insert(namespace+".insertDownload",db);
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
	public DownloadBean getDownload(int num) {
		return sqlSession.selectOne(namespace+".getDownload", num);
	}

	@Override
	public DownloadBean numCheck(DownloadBean db) {
		return sqlSession.selectOne(namespace+".numCheck", db);
	}

	@Override
	public void updateDownload(DownloadBean db) {
		sqlSession.update(namespace+".updateDownload", db);
	}

	@Override
	public void deleteDownload(DownloadBean db) {
		sqlSession.delete(namespace+".deleteDownload",db);
	}

	@Override
	public String likeChk(int num,String id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("num", num);
		paramMap.put("id", id);
		
		return sqlSession.selectOne(namespace+".likeChk", paramMap);
	}

	@Override
	public void insertLike(int num,String id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("num", num);
		paramMap.put("id", id);
		
		sqlSession.insert(namespace+".insertLike",paramMap);
	}

	@Override
	public void deleteLike(int num,String id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("num", num);
		paramMap.put("id", id);
		
		sqlSession.insert(namespace+".deleteLike",paramMap);
	}
	
	@Override
	public int likeCount(int num) {
		return sqlSession.selectOne(namespace+".likeCount", num);
	}

//	@Override
//	public void updateRe_seq(DownloadBean bb) {
//		sqlSession.update(namespace+".updateRe_seq",bb);
//	}
//
//	@Override
//	public void fupdateDownload(DownloadBean bb) {
//		sqlSession.update(namespace+".fupdateDownload",bb);
//	}

}
