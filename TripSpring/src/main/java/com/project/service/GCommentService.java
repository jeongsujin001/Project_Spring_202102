package com.project.service;

import java.util.List;

import com.project.domain.GCommentBean;
import com.project.domain.PageBean;

public interface GCommentService {
	public List<GCommentBean> getCommentList(int num);
	
	public void insertComment(GCommentBean cb);
	
	public void insertReComment(GCommentBean cb);
		
	public void updateComment(GCommentBean cb);
	
	public void deleteComment(GCommentBean cb);

	public int getCommentCount(int num);

}
