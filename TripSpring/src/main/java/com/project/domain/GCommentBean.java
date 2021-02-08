package com.project.domain;

import java.sql.Timestamp;

public class GCommentBean {
	private String comment_id;
	private String comment_content ;
	private int comment_num;
	private int comment_bnum;
	private Timestamp comment_date;
	private int comment_lev;
	private int comment_ord;
	private int comment_del;
	
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public int getComment_bnum() {
		return comment_bnum;
	}
	public void setComment_bnum(int comment_bnum) {
		this.comment_bnum = comment_bnum;
	}
	public Timestamp getComment_date() {
		return comment_date;
	}
	public void setComment_date(Timestamp comment_date) {
		this.comment_date = comment_date;
	}
	public int getComment_lev() {
		return comment_lev;
	}
	public void setComment_lev(int comment_lev) {
		this.comment_lev = comment_lev;
	}
	public int getComment_ord() {
		return comment_ord;
	}
	public void setComment_ord(int comment_ord) {
		this.comment_ord = comment_ord;
	}
	public int getComment_del() {
		return comment_del;
	}
	public void setComment_del(int comment_del) {
		this.comment_del = comment_del;
	}	
}
