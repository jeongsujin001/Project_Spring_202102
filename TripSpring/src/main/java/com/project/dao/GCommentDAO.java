package com.project.dao;

import java.util.List;

import com.project.domain.GCommentBean;
import com.project.domain.PageBean;

public interface GCommentDAO {
	public List<GCommentBean> getCommentList(int num);
	
	public void insertComment(GCommentBean cb);
	
	public Integer getMaxNum(GCommentBean cb);
	
	public void updateComment(GCommentBean cb);
	
	public void deleteComment(GCommentBean cb);

	public int getCommentCount(int num);

}
