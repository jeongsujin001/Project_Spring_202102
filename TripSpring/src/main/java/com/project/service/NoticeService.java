package com.project.service;

import java.util.List;

import com.project.domain.NoticeBean;
import com.project.domain.PageBean;

public interface NoticeService {
	public List<NoticeBean> getNoticeList(PageBean pbBean);
	
	public Integer getNoticeCount();
	
	public void insertNotice(NoticeBean nb);
	
	public void updateReadcount(int num);
	
	public NoticeBean getNotice(int num);
	
	public NoticeBean numCheck(NoticeBean nb);
	public void updateNotice(NoticeBean nb);
	
	public void deleteNotice(NoticeBean nb);
	
//	public void reInsertNotice(NoticeBean nb);
//	
//	public void fupdateNotice(NoticeBean nb);
	
	
	
}
