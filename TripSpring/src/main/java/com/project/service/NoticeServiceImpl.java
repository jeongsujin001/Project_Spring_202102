package com.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.project.dao.NoticeDAO;
import com.project.domain.NoticeBean;
import com.project.domain.PageBean;


@Service
public class NoticeServiceImpl implements NoticeService{

	@Inject
	private NoticeDAO noticeDAO;
	
	@Override
	public List<NoticeBean> getNoticeList(PageBean pbBean) {
		// pageSize , pageNum 담아서 옴
		
		int currentPage=Integer.parseInt(pbBean.getPageNum());
		pbBean.setCurrentPage(currentPage);
		
		// 디비 startRow-1
		int startRow= (currentPage-1)*pbBean.getPageSize()+1-1;
		pbBean.setStartRow(startRow);
		
		
		return noticeDAO.getNoticeList(pbBean);
	}

	@Override
	public Integer getNoticeCount() {
		return noticeDAO.getNoticeCount();
	}

	@Override
	public void insertNotice(NoticeBean nb) {
		if(noticeDAO.getMaxNum()!=null) {
			nb.setNum(noticeDAO.getMaxNum()+1);
		}else {
			nb.setNum(1);
		}
			
		nb.setDate(new Timestamp(System.currentTimeMillis()));
		nb.setReadcount(0);
		
		noticeDAO.insertNotice(nb);
	}

	@Override
	public void updateReadcount(int num) {
		noticeDAO.updateReadcount(num);
	}

	@Override
	public NoticeBean getNotice(int num) {
		return noticeDAO.getNotice(num);
	}

	@Override
	public NoticeBean numCheck(NoticeBean nb) {
		return noticeDAO.numCheck(nb);
	}

	@Override
	public void updateNotice(NoticeBean nb) {
		noticeDAO.updateNotice(nb);
	}

	@Override
	public void deleteNotice(NoticeBean nb) {
		noticeDAO.deleteNotice(nb);
	}

//	@Override
//	public void reInsertNotice(NoticeBean nb) {
//		// num re_ref 그대로 re_lev re_seq name pass subject content 
//		
//		// max(num)+1
//		nb.setNum(noticeDAO.getMaxNum()+1);
//		// 답글 순서 재배치 re_seq +1
//		// update notice set re_seq=re_seq+1 where re_ref=#{re_ref} and re_seq>#{re_seq}
//		noticeDAO.updateRe_seq(nb);
//
//		// date, readcount , re_lev+1,  re_seq+1
//		nb.setDate(new Timestamp(System.currentTimeMillis()));
//		nb.setReadcount(0);
//		nb.setRe_lev(nb.getRe_lev()+1);
//		nb.setRe_seq(nb.getRe_seq()+1);
//		// insert()
//		noticeDAO.insertNotice(nb);
//		
//	}
//
//	@Override
//	public void fupdateNotice(NoticeBean nb) {
//		noticeDAO.fupdateNotice(nb);
//	}

}
