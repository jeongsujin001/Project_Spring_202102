package com.project.service;

import java.util.List;

import com.project.domain.CommentBean;
import com.project.domain.PageBean;

public interface CommentService {
	public List<CommentBean> getCommentList(int num);
	
//	public Integer getCommentCount();
	
	public void insertComment(CommentBean cb);
	
	public void insertReComment(CommentBean cb);
	
//	public void updateReadcount(int num);
//	
//	public CommentBean getComment(int num);
//	
//	public CommentBean numCheck(CommentBean cb);
	
	public void updateComment(CommentBean cb);
	
	public void deleteComment(CommentBean cb);

	public int getCommentCount(int num);
	
//	public void reInsertComment(CommentBean cb);
//	
//	public void fupdateComment(CommentBean cb);
	
	
	
}
