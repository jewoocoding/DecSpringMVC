<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>자유게시판 - 상세</title>
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
	<h1>게시글 - 상세</h1>
	<table>
		<tr>
			<th>제목</th>
			<td>${board.boardTitle }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.boardWriter }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.boardContent }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<a href="../../${board.boardFilepath }" download>
					${board.boardFilename }
				</a>
			</td>
		</tr>
	</table>
	<div>
		<button type="button" id="modbtn" onclick="showModifyForm(${board.boardNo},${board.boardWriter });">수정하기</button>
		<button type="button" onclick="deleteConfirm(${board.boardNo},${board.boardWriter })">삭제하기</button>
		<button type="button" onclick="gotoNoticeList();" >목록으로</button>
		<button type="button" onclick="goBack();">뒤로가기</button>
	</div>
	<script>
		function showModifyForm(boardNo, boardWriter) {
			location.href = "/board/update?boardNo=" + boardNo;
		}
	
		function deleteConfirm(boardNo, boardWriter) {
			var answer = confirm("정말 삭제하시겠습니까?");
			if(answer){
				location.href = "/board/delete?boardNo="+boardNo;				
			}
		}
	
		function gotoNoticeList() {
			location.href = "/board/list";
		}
		function goBack() {
			history.go(-1);
		}
	</script>
</body>
</html>