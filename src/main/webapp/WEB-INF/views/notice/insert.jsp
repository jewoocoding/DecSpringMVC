<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지사항 - 등록</title>
</head>
<body>
	<h1>공지사항 등록</h1>
	<form action="/notice/insert" method="post" enctype="multipart/form-data">
		제목: <input type="text" name="noticeSubject"> <br>
		내용: <textarea rows="5" cols="60" name="noticeContent"></textarea> <br>
		첨부파일: <input type="file" name="uploadFile"> <br> 
		<button type="submit">등록</button>
	</form>
</body>
</html>