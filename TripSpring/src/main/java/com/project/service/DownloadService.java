package com.project.service;

import java.util.List;

import com.project.domain.DownloadBean;
import com.project.domain.PageBean;

public interface DownloadService {
	public List<DownloadBean> getDownloadList(PageBean pbBean);
	
	public Integer getDownloadCount();
	
	public void insertDownload(DownloadBean db);
	
	public void updateReadcount(int num);
	
	public DownloadBean getDownload(int num);
	
	public DownloadBean numCheck(DownloadBean db);
	public void updateDownload(DownloadBean db);
	
	public void deleteDownload(DownloadBean db);

	public int likeChk(int num,String id);

	public void insertLike(int num,String id,int like);

	public int likeCount(int num);
	
//	public void reInsertDownload(DownloadBean bb);
//	
//	public void fupdateDownload(DownloadBean bb);
	
}
