<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>Hello SpringMVC</h1>
	<!-- 로그인이 되면 로그인한 사람이 사용할 수 있는 메뉴 -->
	<c:if test="${memberId ne null && memberId ne ''}">
		${sessionScope.memberId }님이 로그인 하셨네요! ${memberName }님 환영합니다!
		<a href="/member/detail">마이페이지</a>
		<a href="/member/logout">로그아웃</a>
	</c:if>
	
	<!-- 로그인 되지 않았으면 로그인 폼을 출력 -->
	<c:if test="${memberId eq null || memberId eq '' }">
		<form action="/member/login" method="post">
			ID : <input type="text" name="memberId">
			PW : <input type="password" name="memberPw">
			<input type="submit" value="로그인">
		</form>	
	</c:if>
</body>
</html>