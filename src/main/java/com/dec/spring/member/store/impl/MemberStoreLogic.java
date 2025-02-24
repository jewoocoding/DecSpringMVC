package com.dec.spring.member.store.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dec.spring.member.controller.dto.JoinRequest;
import com.dec.spring.member.controller.dto.LoginRequest;
import com.dec.spring.member.controller.dto.ModifyRequest;
import com.dec.spring.member.domain.MemberVO;
import com.dec.spring.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{

	@Override
	public int insertMember(SqlSession session, JoinRequest member) {
		int result = session.insert("MemberMapper.insertMember",member);
		return result;
	}

	@Override
	public int updateMember(SqlSession session, ModifyRequest member) {
		return session.update("MemberMapper.updateMember",member);
	}

	@Override
	public int deleteMember(SqlSession session, String memberId) {
		return session.delete("MemberMapper.deleteMember",memberId);
	}

	@Override
	public MemberVO selectOneByLogin(SqlSession session, LoginRequest memberLogin) {
		return session.selectOne("MemberMapper.selectOneByLogin",memberLogin);
	}

	@Override
	public MemberVO selectOneById(SqlSession session, String memberId) {
		return session.selectOne("MemberMapper.selectOneById",memberId);
	}

}
