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
				<c:forEach items="${bList }" var="board" varStatus="i">
					<tr>
						<td>${board.boardNo }</td>
						<td> <a href="/board/detail/${board.boardNo }">${board.boardTitle }</a></td>
						<td>${board.boardWriter }</td>
						<td>${board.boardDate }</td>
						<td>${board.boardFilename }</td>			
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" align="center">
						<a href="/board/list?currentPage=1">맨처음</a>
						<c:if test="${startNavi ne 1 }"><a href="/board/list?currentPage=${startNavi-1 }">이전</a></c:if>
						<c:forEach begin="${startNavi }" end="${endNavi }" varStatus="i">
							<a href="/board/list?currentPage=${i.index }">${i.index}</a>
						</c:forEach>
						<c:if test="${endNavi ne maxPage }"><a href="/board/list?currentPage=${endNavi + 1 }">다음</a></c:if>
						<a href="/board/list?currentPage=${maxPage }">맨끝</a>						
					</td>
					<td>
						<button type="button">글쓰기</button>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>