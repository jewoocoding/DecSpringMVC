package com.dec.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dec.spring.member.domain.MemberVO;
import com.dec.spring.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService; // 필드 의존성 주입
	
	// 테스트 메인
	@RequestMapping(value="/main")
	public String showMain() {
		// request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
		return "main";
	}
	
	// 회원 로그인
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String memberLogin(@RequestParam("memberId") String memberId,
							@RequestParam("memberPw") String memberPw
							, HttpSession session, Model model) {
		try {
			MemberVO member = new MemberVO(memberId, memberPw);
			member = mService.selectOneByLogin(member);
			if(member != null) {
				// 로그인 성공 후 세션에 정보 저장
				// HttpSession session = request.getSession();
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberName", member.getMemberName());
				return "redirect:/";
			}else {
				// 로그인 실패시 에러페이지로 이동
				model.addAttribute("errorMsg","존재하지 않는 정보입니다.");
				return "common/error";
			}
		} catch (Exception e) {
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}

	// 회원 로그아웃
	@RequestMapping(value="/member/logout", method=RequestMethod.GET)
	public String memberLogout(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	// 회원가입 페이지로 이동
	@RequestMapping(value="/member/insert",method=RequestMethod.GET)
	public String memberInsertForm() {
		return "member/insert";
	}
	
	// 회원가입
	@RequestMapping(value="/member/insert", method=RequestMethod.POST)
	public String memberInsert(
			@RequestParam("memberId") String memberId,
			@RequestParam("memberPw") String memberPw,
			@RequestParam("memberName") String memberName,
			@RequestParam("memberAge") int memberAge,
			@RequestParam("memberGender") String memberGender,
			@RequestParam("memberEmail") String memberEmail,
			@RequestParam("memberPhone") String memberPhone,
			@RequestParam("memberAddress") String memberAddress,
			HttpServletRequest request, HttpServletResponse response) {
//		String memberId = request.getParameter("memberId");
//		String memberPw = request.getParameter("memberPw");
//		String memberName = request.getParameter("memberName");
//		int memberAge = Integer.parseInt(request.getParameter("memberAge"));
//		String memberGender = request.getParameter("memberGender");
//		String memberEmail = request.getParameter("memberEmail");
//		String memberPhone = request.getParameter("memberPhone");
//		String memberAddress = request.getParameter("memberAddress");
		
		MemberVO member = new MemberVO(memberId, memberPw, memberName, memberAge, memberGender, memberEmail, memberPhone, memberAddress);
		// MemberServiceImpl mService = new MemberServiceImpl(); -> 강한 결합
		// MemberService mService = new MemberServiceImpl(); -> 의존성 주입X
		int result = mService.insertMember(member);
		
		if(result > 0) {
			// 성공시 메인페이지(로그인 페이지)로 이동
			// response.sendRedirect("/");
			return "redirect:/";
		}else {
			// 실패시 에러페이지로 이동
			// request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request,response);
			return "common/error";
		}
	}
	
	// 마이페이지로 이동
	@RequestMapping(value="/member/detail", method=RequestMethod.GET)
	public String memberMyPage(HttpSession session, Model model) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			MemberVO member = mService.selectOneById(memberId);
			if(member != null) {
				// request.setAttribute("member",member);
				model.addAttribute("member", member);
				model.addAttribute("errorMsg","존재하지 않는 정보입니다.");
				// request.getRequestDispatcher("/WEB-INF/views/member/detail.jsp").forward(request,response);
				return "member/detail";
			}else {
				return "common/error";
			}
		} catch (Exception e) {
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
		
	}

	// 회원정보 수정페이지로 이동
	@RequestMapping(value="/member/update", method = RequestMethod.GET)
	public String memberUpdateForm(HttpSession session, Model model) {
		try {
			// 세션에서 아이디 가져오기
			String memberId = (String)session.getAttribute("memberId");
			MemberVO member = mService.selectOneById(memberId);
			if(member != null) {
				model.addAttribute("member",member);
				return "member/update";
			}else {
				// 데이터 없으면 에러페이지로 이동
				model.addAttribute("errorMsg","서비스가 완료되지 않았습니다.");
				return "common/error";
			}
		} catch (Exception e) {
			// 예외 발생하면 에러페이지로 이동
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}

	// 회원정보 수정
	@RequestMapping(value="/member/update", method = RequestMethod.POST)
	public String memberUpdate(@RequestParam("memberId") String memberId,
								@RequestParam("memberPw") String memberPw,
								@RequestParam("memberEmail") String memberEmail,
								@RequestParam("memberPhone") String memberPhone,
								@RequestParam("memberAddress") String memberAddress,
								Model model) {
		try {
			MemberVO member = new MemberVO(memberId, memberPw, memberEmail, memberPhone, memberAddress);
			int result = mService.updateMember(member);
			if(result > 0 ) {
				// 마이페이지로 이동
				return "redirect:/member/detail";
			}else {
				// 오류페이지로 이동
				model.addAttribute("errorMsg","서비스가 완료되지 않았습니다.");
				return "common/error";
			}
		} catch (Exception e) {
			// 콘솔에 에러로그 출력하도록 함.
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}

	// 회원 탈퇴
	@RequestMapping(value="/member/delete")
	public String memberDelete(HttpSession session, Model model) {
		// 회원 탈퇴 진행하고 로그아웃해서 메인으로 이동하도록 해야함.
		String memberId = (String)session.getAttribute("memberId");
		try {
			int result = mService.deleteMember(memberId);
			if(result > 0) {
				// 로그아웃
				return "redirect:/member/logout";
			}else {
				// 에러페이지로 이동
				model.addAttribute("errorMsg","서비스가 완료되지 않았습니다.");
				return "common/error";
			}
		} catch (Exception e) {
			// 예외 발생시 에러페이지로 이동
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
		
	}
}
