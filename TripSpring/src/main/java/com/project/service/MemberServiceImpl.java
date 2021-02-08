package com.project.service;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.dao.MemberDAO;
import com.project.domain.MemberBean;

@Service
public class MemberServiceImpl implements MemberService{
	@Inject
	private MemberDAO memberDAO;
	
	@Override
	public void insertMember(MemberBean mb) {
		//가입날짜
		mb.setDate(new Timestamp(System.currentTimeMillis()));
		memberDAO.insertMember(mb);
	}

	@Override
	public MemberBean userCheck(MemberBean mb) {
		return memberDAO.userCheck(mb);
	}

	@Override
	public MemberBean getMember(String id) {
		return memberDAO.getMember(id);
	}

	@Override
	public void updateMember(MemberBean mb) {
		memberDAO.updateMember(mb);
	}

	@Override
	public void deleteMember(MemberBean mb) {
		memberDAO.deleteMember(mb);
	}
}
