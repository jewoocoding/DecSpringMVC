<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>자유게시판 - 리스트</title>
	<style>
		table {
			border: 1px solid black;
			border-collapse: collapse;
		}
		th, td {
			border: 1px solid rgb(110,102,102);
		}
	</style>
</head>
<body>
	<h1>자유게시판</h1>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일자</th>
			<th>첨부파일</th>
		</tr>
		<!-- 
		<c:if test="${empty bList }">
			없어요
		</c:if>
		<c:if test="${not empty bList}">
			있어요
		</c:if>
		-->
		<c:choose>
			<c:when test="${empty bList }">
				<tr>
					<td colspan="5" align="center">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<c:forEach items="${bList }" var="board" varStatus="i">
						<td>${board.boardNo }</td>
						<td>${board.boardTitle }</td>
						<td>${board.boardWriter }</td>
						<td>${board.boardDate }</td>
						<td>${board.boardFilename }</td>			
					</c:forEach>			
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>