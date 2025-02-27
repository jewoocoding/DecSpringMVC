package com.dec.spring.member.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dec.spring.member.controller.dto.JoinRequest;
import com.dec.spring.member.controller.dto.LoginRequest;
import com.dec.spring.member.controller.dto.ModifyRequest;
import com.dec.spring.member.domain.MemberVO;
import com.dec.spring.member.service.MemberService;
import com.dec.spring.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberStore mStore;
	@Autowired
	private SqlSession session;
	
	@Override
	public int insertMember(JoinRequest member) {
		// MemberStoreLogic mStore = new MemberStoreLogic(); -> 강한결합
		// MemberStore mStore = new MemberStoreLogic(); -> 의존성 주입X
		int result = mStore.insertMember(session, member);
		return result;
	}

	@Override
	public int updateMember(ModifyRequest member) {
		return mStore.updateMember(session, member);
	}

	@Override
	public int deleteMember(String memberId) {
		return mStore.deleteMember(session, memberId);
	}

	@Override
	public MemberVO selectOneByLogin(LoginRequest memberLogin) {
		MemberVO result = mStore.selectOneByLogin(session, memberLogin);
		return result;
	}

	@Override
	public MemberVO selectOneById(String memberId) {
		return mStore.selectOneById(session, memberId);
	}

}
