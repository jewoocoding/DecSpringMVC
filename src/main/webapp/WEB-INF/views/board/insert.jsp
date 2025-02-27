<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
	<title>자유게시판 - 작성</title>
</head>
<body>
	<h1>게시글 등록</h1>
	<form action="/board/insert" method="post" enctype="multipart/form-data">
		제목 : <input type="text" name="boardTitle" size="70"> <br>
		내용 : <textarea rows="4" cols="50" name="boardContent"></textarea> <br>
		첨부파일 : <input type="file" name="uploadFile"> <br>
		<button type="submit">등록</button>
	</form>
</body>
</html>