<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>스프링 - 마이페이지</title>
</head>
<body>
	<h1>마이페이지</h1>
	ID : <span>${member.memberId }</span> <br>
	NAME : <span>${member.memberName }</span> <br>
	AGE : <span>${member.memberAge }</span> <br>
	GENDER : <span>${member.memberGender }</span> <br>
	EMAIL : <span>${member.memberEmail }</span> <br>
	PHONE : <span>${member.memberPhone }</span> <br>
	ADDRESS : <span>${member.memberAddress }</span> <br>
	ENROLL-DATE : <span>${member.memberDate }</span> <br>
	<!-- 
		- javascript:void(0)
		a태그를 클릭했을 때 아무 동작도 하지 않도록 하는 방법, 
		페이지 이동이나 새로고침이 되지 않음.
		기본 링크 동작을 막을 때 사용함.
	-->
	<a href="/member/update">수정페이지로 이동</a> <a href="javascript:void(0)" onclick="deleteConfirm();">회원탈퇴</a> <br>
	<a href="/">메인으로 이동</a>
	
	<script>
		function deleteConfirm() {
			var result = confirm("정말 탈퇴하시겠습니까?");
			if(result) {
				// 멤버 정보 삭제
				// location.href="/member/delete";
				location.replace("/member/delete");
				// alert("탈퇴 완료되었습니다.");
			}
		}
	</script>
</body>
</html>