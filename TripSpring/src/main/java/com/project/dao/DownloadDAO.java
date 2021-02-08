package com.project.dao;

import java.util.List;

import com.project.domain.DownloadBean;
import com.project.domain.PageBean;

public interface DownloadDAO {
	public List<DownloadBean> getDownloadList(PageBean pbBean);
	
	public Integer getDownloadCount();
	
	public void insertDownload(DownloadBean db);
	
	public Integer getMaxNum();
	
	public void updateReadcount(int num);
	
	public DownloadBean getDownload(int num);
	
	public DownloadBean numCheck(DownloadBean db);
	public void updateDownload(DownloadBean db);
	
	public void deleteDownload(DownloadBean db);

	public String likeChk(int num,String id);

	public void insertLike(int num,String id);
	public void deleteLike(int num,String id);
	
	public int likeCount(int num);
	
//	public void updateRe_seq(DownloadBean bb);
//	
//	public void fupdateDownload(DownloadBean bb);
}
