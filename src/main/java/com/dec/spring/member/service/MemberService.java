package com.dec.spring.member.service;

import com.dec.spring.member.controller.dto.JoinRequest;
import com.dec.spring.member.controller.dto.LoginRequest;
import com.dec.spring.member.controller.dto.ModifyRequest;
import com.dec.spring.member.domain.MemberVO;

public interface MemberService {
	
	/**
	 * 회원 정보 등록 Service
	 * @param MemberVO
	 * @return int
	 */
	int insertMember(JoinRequest member);
	
	/**
	 * 회원 정보 수정 Service
	 * @param MemberVO
	 * @return int
	 */
	int updateMember(ModifyRequest member);
	
	/**
	 * 회원 정보 삭제 Service
	 * @param String
	 * @return int
	 */
	int deleteMember(String memberId);
	
	/**
	 * 회원 로그인 Service
	 * @param MemberVO
	 * @return MemberVO 
	 */
	MemberVO selectOneByLogin(LoginRequest memberLogin);
	
	/**
	 * 회원 아이디로 조회 Service
	 * @param String
	 * @return MemberVO
	 */
	MemberVO selectOneById(String memberId);
}
