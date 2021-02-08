package com.project.service;

import com.project.domain.MemberBean;

public interface MemberService {
	public void insertMember(MemberBean mb);
	
	public MemberBean userCheck(MemberBean mb);
	
	public MemberBean getMember(String id);
	
	public void updateMember(MemberBean mb);
	
	public void deleteMember(MemberBean mb);
}
