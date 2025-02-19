package com.dec.spring.member.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dec.spring.member.domain.MemberVO;
import com.dec.spring.member.service.MemberService;
import com.dec.spring.member.store.MemberStore;
import com.dec.spring.member.store.impl.MemberStoreLogic;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberStore mStore;
	@Autowired
	private SqlSession session;
	
	@Override
	public int insertMember(MemberVO member) {
		// MemberStoreLogic mStore = new MemberStoreLogic(); -> 강한결합
		// MemberStore mStore = new MemberStoreLogic(); -> 의존성 주입X
		int result = mStore.insertMember(session, member);
		return result;
	}

	@Override
	public int updateMember(MemberVO member) {
		return 0;
	}

	@Override
	public int deleteMember(String memberId) {
		return 0;
	}

	@Override
	public MemberVO selectOneByLogin(MemberVO member) {
		MemberVO result = mStore.selectOneByLogin(session, member);
		return result;
	}

	@Override
	public MemberVO selectOneById(String memberId) {
		return null;
	}

}
