package com.project.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.dao.DownloadDAO;
import com.project.domain.DownloadBean;
import com.project.domain.PageBean;


@Service
public class DownloadServiceImpl implements DownloadService{
	@Inject
	private DownloadDAO downloadDAO;
	
	@Override
	public List<DownloadBean> getDownloadList(PageBean pbBean) {
		// pageSize , pageNum 담아서 옴
		
		int currentPage=Integer.parseInt(pbBean.getPageNum());
		pbBean.setCurrentPage(currentPage);
		
		// 디비 startRow-1
		int startRow= (currentPage-1)*pbBean.getPageSize()+1-1;
		pbBean.setStartRow(startRow);
		
		
		return downloadDAO.getDownloadList(pbBean);
	}

	@Override
	public Integer getDownloadCount() {
		return downloadDAO.getDownloadCount();
	}

	@Override
	public void insertDownload(DownloadBean db) {
		if(downloadDAO.getMaxNum()!=null) {
			db.setNum(downloadDAO.getMaxNum()+1);
		}else {
			db.setNum(1);
		}		
		
		db.setDate(new Timestamp(System.currentTimeMillis()));
		db.setReadcount(0);
		
		downloadDAO.insertDownload(db);
	}

	@Override
	public void updateReadcount(int num) {
		downloadDAO.updateReadcount(num);
	}

	@Override
	public DownloadBean getDownload(int num) {
		return downloadDAO.getDownload(num);
	}

	@Override
	public DownloadBean numCheck(DownloadBean db) {
		return downloadDAO.numCheck(db);
	}

	@Override
	public void updateDownload(DownloadBean db) {
		downloadDAO.updateDownload(db);
	}

	@Override
	public void deleteDownload(DownloadBean db) {
		downloadDAO.deleteDownload(db);
	}

	@Override
	public int likeChk(int num,String id) {
		int check=0;
		String likeChk = downloadDAO.likeChk(num,id);
		
		if("y".equals(likeChk)){
			check=1;
		}else{
			check=0;
		}	
		return check;
	}

	@Override
	public void insertLike(int num,String id,int like) {
		if(like==1) {
			downloadDAO.deleteLike(num,id);
		}else {
			downloadDAO.insertLike(num,id);
		}		
		
	}

	@Override
	public int likeCount(int num) {
		return downloadDAO.likeCount(num);	
	}

//	@Override
//	public void reInsertDownload(DownloadBean bb) {
//		// num re_ref 그대로 re_lev re_seq name pass subject content 
//		
//		// max(num)+1
//		bb.setNum(downloadDAO.getMaxNum()+1);
//		// 답글 순서 재배치 re_seq +1
//		// update download set re_seq=re_seq+1 where re_ref=#{re_ref} and re_seq>#{re_seq}
//		downloadDAO.updateRe_seq(bb);
//
//		// date, readcount , re_lev+1,  re_seq+1
//		bb.setDate(new Timestamp(System.currentTimeMillis()));
//		bb.setReadcount(0);
//		bb.setRe_lev(bb.getRe_lev()+1);
//		bb.setRe_seq(bb.getRe_seq()+1);
//		// insert()
//		downloadDAO.insertDownload(bb);
//		
//	}
//
//	@Override
//	public void fupdateDownload(DownloadBean bb) {
//		downloadDAO.fupdateDownload(bb);
//	}
}
