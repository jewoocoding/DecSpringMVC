<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지사항 - 검색</title>
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
	<h1>공지사항 게시판</h1>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일자</th>
			<th>첨부파일</th>
		</tr>
		<c:forEach items="${searchList }" var="notice" varStatus="i">
			<tr>
				<td>${i.count }</td>
				<td><a href="/notice/detail?noticeNo=${notice.noticeNo }">${notice.noticeSubject }</a></td>
				<td>${notice.noticeWriter }</td>
				<td>${notice.noticeDate }</td>
				<c:if test="${not empty notice.noticeFilename}">
					<td>O</td>			
				</c:if>
				<c:if test="${empty notice.noticeFilename}">
					<td>X</td>			
				</c:if>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
				<c:if test="${startNavi ne 1 }">
					<a href="/notice/search?page=${startNavi-1 }&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}">이전</a>				
				</c:if>
				<c:forEach begin="${startNavi }" end="${endNavi }" var="p">
					<a href="/notice/search?page=${p }&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}">${p }</a>
				</c:forEach>
				<c:if test="${endNavi ne maxPage }">
					<a href="/notice/search?page=${endNavi+1 }&searchCondition=${searchCondition}&searchKeyword=${searchKeyword}">다음</a>				
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<form action="/notice/search" method="get">
					<select name="searchCondition">
						<!-- select태그에서 선택한 option값은 사이값이 아니라 value값이 전송됨 -->
						<option value="all" <c:if test="${searchCondition eq 'all' }">selected</c:if>>전체</option>
						<option value="writer" <c:if test="${searchCondition eq 'writer' }">selected</c:if>>작성자</option>
						<option value="title" <c:if test="${searchCondition eq 'title' }">selected</c:if>>제목</option>
						<option value="content" <c:if test="${searchCondition eq 'content' }">selected</c:if>>내용</option>
					</select>
					<input type="text" name="searchKeyword" placeholder="검색어를 입력하세요" value="${searchKeyword }">
					<input type="submit" value="검색">
				</form>
			</td>
			<td>
				<!-- 
					javascript없이 버튼으로 하는 방식
					<form action="/notice/insert" method="get">
						<input type="submit" value="글쓰기">
					</form>
				 -->
				<!-- javascript를 이용해서 페이지 이동하기 -->
				<button onclick="showInsertForm();">글쓰기</button>
				<!--  
					javascript없이 a태그로 하는 방식 
					<a href="/notice/insert">글쓰기</a>
				-->
			</td>
		</tr>
	</table>	
	<script type="text/javascript">
		function showInsertForm() {
			location.href = "/notice/insert";
		}
	</script>
</body>
</html>