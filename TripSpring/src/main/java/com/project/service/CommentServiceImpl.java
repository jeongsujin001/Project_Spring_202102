package com.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.project.dao.CommentDAO;
import com.project.domain.CommentBean;
import com.project.domain.PageBean;


@Service
public class CommentServiceImpl implements CommentService{

	@Inject
	private CommentDAO commentDAO;
	
	@Override
	public List<CommentBean> getCommentList(int num) {		
		return commentDAO.getCommentList(num);
	}

//	@Override
//	public Integer getCommentCount() {
//		return commentDAO.getCommentCount();
//	}

	@Override
	public void insertComment(CommentBean cb) {
		if(commentDAO.getMaxNum(cb)!=null) {
			cb.setComment_num(commentDAO.getMaxNum(cb)+1);
			cb.setComment_ord(commentDAO.getMaxNum(cb)+1);
		}else {
			cb.setComment_num(1);
			cb.setComment_ord(1);
		}
		
		cb.setComment_date(new Timestamp(System.currentTimeMillis()));
		cb.setComment_lev(0);
		cb.setComment_del(0);
		
		commentDAO.insertComment(cb);
		
	}

	@Override
	public void insertReComment(CommentBean cb) {
		cb.setComment_ord(cb.getComment_num());
			
		if(commentDAO.getMaxNum(cb)!=null) {
			cb.setComment_num(commentDAO.getMaxNum(cb)+1);
		}else {
			cb.setComment_num(1);
		}
		
		cb.setComment_date(new Timestamp(System.currentTimeMillis()));
				
		cb.setComment_lev(1);
		cb.setComment_del(0);
		
		commentDAO.insertComment(cb);
		
	}	
//	@Override
//	public void updateReadcount(int num) {
//		commentDAO.updateReadcount(num);
//	}
//
//	@Override
//	public CommentBean getComment(int num) {
//		return commentDAO.getComment(num);
//	}
//
//	@Override
//	public CommentBean numCheck(CommentBean cb) {
//		return commentDAO.numCheck(cb);
//	}

	@Override
	public void updateComment(CommentBean cb) {
		cb.setComment_date(new Timestamp(System.currentTimeMillis()));
		
		commentDAO.updateComment(cb);
	}

	@Override
	public void deleteComment(CommentBean cb) {
		commentDAO.deleteComment(cb);
	}

	@Override
	public int getCommentCount(int num) {
		return commentDAO.getCommentCount(num);
	}

//	@Override
//	public void reInsertComment(CommentBean cb) {
//		// num re_ref 그대로 re_lev re_seq name pass subject content 
//		
//		// max(num)+1
//		cb.setNum(commentDAO.getMaxNum()+1);
//		// 답글 순서 재배치 re_seq +1
//		// update comment set re_seq=re_seq+1 where re_ref=#{re_ref} and re_seq>#{re_seq}
//		commentDAO.updateRe_seq(cb);
//
//		// date, readcount , re_lev+1,  re_seq+1
//		cb.setDate(new Timestamp(System.currentTimeMillis()));
//		cb.setReadcount(0);
//		cb.setRe_lev(cb.getRe_lev()+1);
//		cb.setRe_seq(cb.getRe_seq()+1);
//		// insert()
//		commentDAO.insertComment(cb);
//		
//	}
//
//	@Override
//	public void fupdateComment(CommentBean cb) {
//		commentDAO.fupdateComment(cb);
//	}

}
