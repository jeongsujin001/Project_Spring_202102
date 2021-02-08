package com.project.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.project.domain.NoticeBean;
import com.project.domain.PageBean;

@Repository
public class NoticeDAOImpl implements NoticeDAO{
	@Inject
	private SqlSession sqlSession;
	private static final String namespace="com.project.mapper.NoticeMapper";
	
	@Override
	public List<NoticeBean> getNoticeList(PageBean pbBean) {
		return sqlSession.selectList(namespace+".getNoticeList",pbBean);
	}

	@Override
	public Integer getNoticeCount() {
		return sqlSession.selectOne(namespace+".getNoticeCount");
	}

	@Override
	public void insertNotice(NoticeBean nb) {
		sqlSession.insert(namespace+".insertNotice",nb);
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
	public NoticeBean getNotice(int num) {
		return sqlSession.selectOne(namespace+".getNotice", num);
	}

	@Override
	public NoticeBean numCheck(NoticeBean nb) {
		return sqlSession.selectOne(namespace+".numCheck", nb);
	}

	@Override
	public void updateNotice(NoticeBean nb) {
		sqlSession.update(namespace+".updateNotice", nb);
	}

	@Override
	public void deleteNotice(NoticeBean nb) {
		sqlSession.delete(namespace+".deleteNotice",nb);
	}

//	@Override
//	public void updateRe_seq(NoticeBean nb) {
//		sqlSession.update(namespace+".updateRe_seq",nb);
//	}
//
//	@Override
//	public void fupdateNotice(NoticeBean nb) {
//		sqlSession.update(namespace+".fupdateNotice",nb);
//	}

}
