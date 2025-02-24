<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>스프링 - 회원가입</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="/member/insert" method="post">
		ID : <input type="text" name="memberId"> <br>
		PW : <input type="password" name="memberPw"> <br>
		NAME : <input type="text" name="memberName"> <br>
		AGE : <input type="number" name="memberAge"> <br>
		GENDER : <input type="text" name="memberGender"> <br>
		EMAIL : <input type="text" name="memberEmail"> <br>
		PHONE : <input type="text" name="memberPhone"> <br>
		ADDRESS : <input type="text" name="memberAddress"> <br>
		<input type="submit" value="가입">
	</form>
</body>
</html>