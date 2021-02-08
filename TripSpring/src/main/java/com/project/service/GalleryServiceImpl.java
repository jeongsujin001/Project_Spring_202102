package com.project.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.dao.GalleryDAO;
import com.project.domain.GalleryBean;
import com.project.domain.PageBean;


@Service
public class GalleryServiceImpl implements GalleryService{
	@Inject
	private GalleryDAO galleryDAO;
	
	@Override
	public List<GalleryBean> getGalleryList(PageBean pbBean) {
		// pageSize , pageNum 담아서 옴
		
		int currentPage=Integer.parseInt(pbBean.getPageNum());
		pbBean.setCurrentPage(currentPage);
		
		// 디비 startRow-1
		int startRow= (currentPage-1)*pbBean.getPageSize()+1-1;
		pbBean.setStartRow(startRow);
		
		
		return galleryDAO.getGalleryList(pbBean);
	}

	@Override
	public List<GalleryBean> getMainGalleryList() {
		return galleryDAO.getMainGalleryList();
	}
	
	@Override
	public Integer getGalleryCount() {
		return galleryDAO.getGalleryCount();
	}

	@Override
	public void insertGallery(GalleryBean gb) {
		if(galleryDAO.getMaxNum()!=null) {
			gb.setNum(galleryDAO.getMaxNum()+1);
		}else {
			gb.setNum(1);
		}		
		
		gb.setDate(new Timestamp(System.currentTimeMillis()));
		gb.setReadcount(0);
		
		galleryDAO.insertGallery(gb);
	}

	@Override
	public void updateReadcount(int num) {
		galleryDAO.updateReadcount(num);
	}

	@Override
	public GalleryBean getGallery(int num) {
		return galleryDAO.getGallery(num);
	}

	@Override
	public GalleryBean numCheck(GalleryBean bb) {
		return galleryDAO.numCheck(bb);
	}

	@Override
	public void updateGallery(GalleryBean bb) {
		galleryDAO.updateGallery(bb);
	}

	@Override
	public void deleteGallery(GalleryBean gb) {
		galleryDAO.deleteGallery(gb);
	}

//	@Override
//	public void reInsertGallery(GalleryBean bb) {
//		// num re_ref 그대로 re_lev re_seq name pass subject content 
//		
//		// max(num)+1
//		bb.setNum(galleryDAO.getMaxNum()+1);
//		// 답글 순서 재배치 re_seq +1
//		// update gallery set re_seq=re_seq+1 where re_ref=#{re_ref} and re_seq>#{re_seq}
//		galleryDAO.updateRe_seq(bb);
//
//		// date, readcount , re_lev+1,  re_seq+1
//		bb.setDate(new Timestamp(System.currentTimeMillis()));
//		bb.setReadcount(0);
//		bb.setRe_lev(bb.getRe_lev()+1);
//		bb.setRe_seq(bb.getRe_seq()+1);
//		// insert()
//		galleryDAO.insertGallery(bb);
//		
//	}
//
//	@Override
//	public void fupdateGallery(GalleryBean bb) {
//		galleryDAO.fupdateGallery(bb);
//	}
}
