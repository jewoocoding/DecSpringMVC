package com.dec.spring.member.controller.dto;

public class LoginRequest {
	// DTO(Data Transfer Object)
	private String memberId;
	private String memberPw;
	
	public LoginRequest() {}
	
	public LoginRequest(String memberId, String memberPw) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	@Override
	public String toString() {
		return "LoginRequest [memberId=" + memberId + ", memberPw=" + memberPw + "]";
	}

}
