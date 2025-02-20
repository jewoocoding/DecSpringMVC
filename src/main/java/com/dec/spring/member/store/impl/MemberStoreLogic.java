package com.dec.spring.member.store.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dec.spring.member.domain.MemberVO;
import com.dec.spring.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{

	@Override
	public int insertMember(SqlSession session, MemberVO member) {
		int result = session.insert("MemberMapper.insertMember",member);
		return result;
	}

	@Override
	public int updateMember(SqlSession session, MemberVO member) {
		return session.update("MemberMapper.updateMember",member);
	}

	@Override
	public int deleteMember(SqlSession session, String memberId) {
		return session.delete("MemberMapper.deleteMember",memberId);
	}

	@Override
	public MemberVO selectOneByLogin(SqlSession session, MemberVO member) {
		return session.selectOne("MemberMapper.selectOneByLogin",member);
	}

	@Override
	public MemberVO selectOneById(SqlSession session, String memberId) {
		return session.selectOne("MemberMapper.selectOneById",memberId);
	}

}
