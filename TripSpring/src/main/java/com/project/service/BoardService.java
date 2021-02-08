package com.project.service;

import java.util.List;

import com.project.domain.BoardBean;
import com.project.domain.PageBean;

public interface BoardService {
	public List<BoardBean> getBoardList(PageBean pbBean);
	
	public Integer getBoardCount();
	
	public void insertBoard(BoardBean bb);
	
	public void updateReadcount(int num);
	
	public BoardBean getBoard(int num);
	
	public BoardBean numCheck(BoardBean bb);
	public void updateBoard(BoardBean bb);
	
	public void deleteBoard(BoardBean bb);
	
	public void updateCcount(int comment_bnum, int amount);
	
//	public void reInsertBoard(BoardBean bb);
//	
//	public void fupdateBoard(BoardBean bb);
	
	
	
}
