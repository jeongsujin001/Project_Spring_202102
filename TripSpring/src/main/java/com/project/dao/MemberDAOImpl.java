package com.project.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.project.domain.MemberBean;


@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="com.project.mapper.MemberMapper";
	
	@Override
	public void insertMember(MemberBean mb) {
		sqlSession.insert(namespace+".insertMember",mb);
	}

	@Override
	public MemberBean userCheck(MemberBean mb) {
		return sqlSession.selectOne(namespace+".userCheck", mb);
	}

	@Override
	public MemberBean getMember(String id) {
		return sqlSession.selectOne(namespace+".getMember", id);
	}
	
	@Override
	public void updateMember(MemberBean mb) {
		sqlSession.update(namespace+".updateMember",mb);
	}

	@Override
	public void deleteMember(MemberBean mb) {
		sqlSession.delete(namespace+".deleteMember",mb);
	}
}
