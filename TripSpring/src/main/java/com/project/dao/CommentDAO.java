package com.project.dao;

import java.util.List;

import com.project.domain.CommentBean;
import com.project.domain.PageBean;

public interface CommentDAO {
	public List<CommentBean> getCommentList(int num);
	
//	public Integer getCommentCount();
	
	public void insertComment(CommentBean cb);
	
	public Integer getMaxNum(CommentBean cb);
	
//	public void updateReadcount(int num);
	
//	public CommentBean getComment(int num);
//	
//	public CommentBean numCheck(CommentBean cb);
	
	public void updateComment(CommentBean cb);
	
	public void deleteComment(CommentBean cb);

	public int getCommentCount(int num);
	
//	public void updateRe_seq(CommentBean cb);
//	
//	public void fupdateComment(CommentBean cb);
	
}
