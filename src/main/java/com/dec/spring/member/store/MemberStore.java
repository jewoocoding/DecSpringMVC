package com.dec.spring.member.store;

import org.apache.ibatis.session.SqlSession;

import com.dec.spring.member.domain.MemberVO;

public interface MemberStore {
	
	/**
	 * 회원 정보 등록 Store
	 * @param SqlSession
	 * @param MemberVO
	 * @return int
	 */
	int insertMember(SqlSession session, MemberVO member);
	
	/**
	 * 회원 정보 수정 Store
	 * @param SqlSession
	 * @param MemberVO
	 * @return int
	 */
	int updateMember(SqlSession session, MemberVO member);
	
	/**
	 * 회원 정보 삭제 Store
	 * @param SqlSession
	 * @param String
	 * @return int
	 */
	int deleteMember(SqlSession session, String memberId);
	
	/**
	 * 회원 로그인 Store
	 * @param SqlSession
	 * @param MemberVO
	 * @return MemberVO
	 */
	MemberVO selectOneByLogin(SqlSession session, MemberVO member);
	
	/**
	 * 회원 아이디로 조회 Store
	 * @param SqlSession
	 * @param String
	 * @return MemberVO
	 */
	MemberVO selectOneById(SqlSession session, String memberId);
}
