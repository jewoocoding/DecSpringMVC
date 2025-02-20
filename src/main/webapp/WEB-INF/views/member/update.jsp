<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 - 회원정보수정</title>
</head>
<body>
	<h1>회원정보 수정</h1>
	<form action="/member/update" method="post">
		<input type="hidden" name="memberId" value="${member.memberId }"> <br>
		PW : <input type="password" name="memberPw" value="${member.memberPw }"> <br>
		EMAIL : <input type="text" name="memberEmail" value="${member.memberEmail }"> <br>
		PHONE : <input type="text" name="memberPhone" value="${member.memberPhone }"> <br>
		ADDRESS : <input type="text" name="memberAddress" value="${member.memberAddress }"> <br>
		<input type="submit" value="수정">
	</form>
</body>
</html>