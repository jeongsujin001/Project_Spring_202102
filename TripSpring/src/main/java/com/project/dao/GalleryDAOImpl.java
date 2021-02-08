package com.project.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.project.domain.GalleryBean;
import com.project.domain.PageBean;


@Repository
public class GalleryDAOImpl implements GalleryDAO{
	@Inject
	private SqlSession sqlSession;
	private static final String namespace="com.project.mapper.GalleryMapper";
	
	@Override
	public List<GalleryBean> getGalleryList(PageBean pbBean) {
		return sqlSession.selectList(namespace+".getGalleryList",pbBean);
	}

	@Override
	public List<GalleryBean> getMainGalleryList() {
		return sqlSession.selectList(namespace+".getMainGalleryList");
	}
	
	@Override
	public Integer getGalleryCount() {
		return sqlSession.selectOne(namespace+".getGalleryCount");
	}

	@Override
	public void insertGallery(GalleryBean gb) {
		sqlSession.insert(namespace+".insertGallery",gb);
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
	public GalleryBean getGallery(int num) {
		return sqlSession.selectOne(namespace+".getGallery", num);
	}

	@Override
	public GalleryBean numCheck(GalleryBean gb) {
		return sqlSession.selectOne(namespace+".numCheck", gb);
	}

	@Override
	public void updateGallery(GalleryBean gb) {
		sqlSession.update(namespace+".updateGallery", gb);
	}

	@Override
	public void deleteGallery(GalleryBean gb) {
		sqlSession.delete(namespace+".deleteGallery",gb);
	}

//	@Override
//	public void updateRe_seq(GalleryBean bb) {
//		sqlSession.update(namespace+".updateRe_seq",bb);
//	}
//
//	@Override
//	public void fupdateGallery(GalleryBean bb) {
//		sqlSession.update(namespace+".fupdateGallery",bb);
//	}

}
