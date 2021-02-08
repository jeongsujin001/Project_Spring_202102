package com.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.project.dao.GCommentDAO;
import com.project.domain.GCommentBean;
import com.project.domain.PageBean;


@Service
public class GCommentServiceImpl implements GCommentService{

	@Inject
	private GCommentDAO gcommentDAO;
	
	@Override
	public List<GCommentBean> getCommentList(int num) {		
		return gcommentDAO.getCommentList(num);
	}

	@Override
	public void insertComment(GCommentBean cb) {
		if(gcommentDAO.getMaxNum(cb)!=null) {
			cb.setComment_num(gcommentDAO.getMaxNum(cb)+1);
			cb.setComment_ord(gcommentDAO.getMaxNum(cb)+1);
		}else {
			cb.setComment_num(1);
			cb.setComment_ord(1);
		}
		
		cb.setComment_date(new Timestamp(System.currentTimeMillis()));
		cb.setComment_lev(0);
		cb.setComment_del(0);
		
		gcommentDAO.insertComment(cb);
		
	}

	@Override
	public void insertReComment(GCommentBean cb) {
		cb.setComment_ord(cb.getComment_num());
			
		if(gcommentDAO.getMaxNum(cb)!=null) {
			cb.setComment_num(gcommentDAO.getMaxNum(cb)+1);
		}else {
			cb.setComment_num(1);
		}
		
		cb.setComment_date(new Timestamp(System.currentTimeMillis()));
				
		cb.setComment_lev(1);
		cb.setComment_del(0);
		
		gcommentDAO.insertComment(cb);
		
	}	

	@Override
	public void updateComment(GCommentBean cb) {
		cb.setComment_date(new Timestamp(System.currentTimeMillis()));
		
		gcommentDAO.updateComment(cb);
	}

	@Override
	public void deleteComment(GCommentBean cb) {
		gcommentDAO.deleteComment(cb);
	}

	@Override
	public int getCommentCount(int num) {
		return gcommentDAO.getCommentCount(num);
	}

}
