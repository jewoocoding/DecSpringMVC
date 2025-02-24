<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지사항 - 상세</title>
	<style>
		table {
			border: 1px solid black;
			border-collapse: collapse;
		}
		th, td {
			border: 1px solid rgb(110, 102, 102);
		}
	</style>
</head>
<body>
	<h1>공지사항 - 상세</h1>
	<table>
		<tr>
			<th>제목</th>
			<td>${notice.noticeSubject }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${notice.noticeWriter }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${notice.noticeContent }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<a href="..${notice.noticeFilepath }" download>
					${notice.noticeFilename }
				</a>
			</td>
		</tr>
	</table>
	<div>
		<button type="button" id="modbtn" onclick="showModifyForm(${notice.noticeNo});">수정하기</button>
		<button type="button" onclick="deleteConfirm(${notice.noticeNo})">삭제하기</button>
		<button type="button" onclick="gotoNoticeList();" >목록으로</button>
		<button type="button" onclick="goBack();">뒤로가기</button>
	</div>
	<script>
		function showModifyForm(noticeNo) {
			location.href = "/notice/update?noticeNo=" + noticeNo;
		}
	
		function deleteConfirm(noticeNo) {
			var answer = confirm("정말 삭제하시겠습니까?");
			if(answer){
				location.href = "/notice/delete?noticeNo="+noticeNo;				
			}
		}
	
		function gotoNoticeList() {
			location.href = "/notice/list";
		}
		function goBack() {
			history.go(-1);
		}
	</script>
</body>
</html>