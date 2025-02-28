<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>자유게시판 - 수정</title>
</head>
<body>
	<h1>게시글 수정</h1>
	<fieldset>
		<legend>게시글 수정</legend>
		<form action="/board/update" method="post" enctype="multipart/form-data">
			<input type="hidden" name="boardNo" value="${board.boardNo }">
			<input type="hidden" name="boardWriter" value="${board.boardWriter }">
			<input type="hidden" name="boardFilename" value="${board.boardFilename }">
			<input type="hidden" name="boardFileRename" value="${board.boardFileRename }">
			<input type="hidden" name="boardFilepath" value="${board.boardFilepath }">
			제목 : <input type="text" name="boardTitle" value='${board.boardTitle }'> <br>
			작성자 : <span>${board.boardWriter }</span> <br>
			내용 : <textarea rows="4" cols="50" name="boardContent">${board.boardContent }</textarea> <br>
			첨부파일 : <span><a href="../..${board.boardFilepath }">${board.boardFilename }</a></span>
			<input type="file" name="reloadFile"> <br>
			<button>수정</button>
		</form>
	</fieldset>
</body>
</html>