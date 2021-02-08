package com.project.service;

import java.util.List;

import com.project.domain.GalleryBean;
import com.project.domain.PageBean;

public interface GalleryService {
	public List<GalleryBean> getGalleryList(PageBean pbBean);
	
	public List<GalleryBean> getMainGalleryList();
	
	public Integer getGalleryCount();
	
	public void insertGallery(GalleryBean gb);
	
	public void updateReadcount(int num);
	
	public GalleryBean getGallery(int num);
	
	public GalleryBean numCheck(GalleryBean bb);
	public void updateGallery(GalleryBean bb);
	
	public void deleteGallery(GalleryBean bb);
	
//	public void reInsertGallery(GalleryBean bb);
//	
//	public void fupdateGallery(GalleryBean bb);
	
}
