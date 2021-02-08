package com.project.dao;

import java.util.List;

import com.project.domain.GalleryBean;
import com.project.domain.PageBean;

public interface GalleryDAO {
	public List<GalleryBean> getGalleryList(PageBean pbBean);
	
	public List<GalleryBean> getMainGalleryList();
	
	public Integer getGalleryCount();
	
	public void insertGallery(GalleryBean gb);
	
	public Integer getMaxNum();
	
	public void updateReadcount(int num);
	
	public GalleryBean getGallery(int num);
	
	public GalleryBean numCheck(GalleryBean gb);
	public void updateGallery(GalleryBean gb);
	
	public void deleteGallery(GalleryBean gb);
	
//	public void updateRe_seq(GalleryBean bb);
//	
//	public void fupdateGallery(GalleryBean bb);
}
