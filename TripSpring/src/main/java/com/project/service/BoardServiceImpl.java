package com.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.project.dao.BoardDAO;
import com.project.domain.BoardBean;
import com.project.domain.PageBean;


@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardBean> getBoardList(PageBean pbBean) {
		// pageSize , pageNum 담아서 옴
		
		int currentPage=Integer.parseInt(pbBean.getPageNum());
		pbBean.setCurrentPage(currentPage);
		
		// 디비 startRow-1
		int startRow= (currentPage-1)*pbBean.getPageSize()+1-1;
		pbBean.setStartRow(startRow);
		
		
		return boardDAO.getBoardList(pbBean);
	}

	@Override
	public Integer getBoardCount() {
		return boardDAO.getBoardCount();
	}

	@Override
	public void insertBoard(BoardBean bb) {
		if(boardDAO.getMaxNum()!=null) {
			bb.setNum(boardDAO.getMaxNum()+1);
			bb.setRe_ref(boardDAO.getMaxNum()+1);
		}else {
			bb.setNum(1);
			bb.setRe_ref(1);
		}
		
		bb.setRe_lev(0);
		bb.setRe_seq(0);
		bb.setCcount(0);
		
		bb.setDate(new Timestamp(System.currentTimeMillis()));
		bb.setReadcount(0);
		
		boardDAO.insertBoard(bb);
	}

	@Override
	public void updateReadcount(int num) {
		boardDAO.updateReadcount(num);
	}

	@Override
	public BoardBean getBoard(int num) {
		return boardDAO.getBoard(num);
	}

	@Override
	public BoardBean numCheck(BoardBean bb) {
		return boardDAO.numCheck(bb);
	}

	@Override
	public void updateBoard(BoardBean bb) {
		boardDAO.updateBoard(bb);
	}

	@Override
	public void deleteBoard(BoardBean bb) {
		boardDAO.deleteBoard(bb);
	}

	@Override
	public void updateCcount(int comment_bnum, int amount) {
		boardDAO.updateCcount(comment_bnum,amount);	
	}

//	@Override
//	public void reInsertBoard(BoardBean bb) {
//		// num re_ref 그대로 re_lev re_seq name pass subject content 
//		
//		// max(num)+1
//		bb.setNum(boardDAO.getMaxNum()+1);
//		// 답글 순서 재배치 re_seq +1
//		// update board set re_seq=re_seq+1 where re_ref=#{re_ref} and re_seq>#{re_seq}
//		boardDAO.updateRe_seq(bb);
//
//		// date, readcount , re_lev+1,  re_seq+1
//		bb.setDate(new Timestamp(System.currentTimeMillis()));
//		bb.setReadcount(0);
//		bb.setRe_lev(bb.getRe_lev()+1);
//		bb.setRe_seq(bb.getRe_seq()+1);
//		// insert()
//		boardDAO.insertBoard(bb);
//		
//	}
//
//	@Override
//	public void fupdateBoard(BoardBean bb) {
//		boardDAO.fupdateBoard(bb);
//	}

}
