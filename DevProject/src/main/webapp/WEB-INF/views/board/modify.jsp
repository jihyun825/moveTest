<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Modify</title>
</head>
<body>
	<h3>Modify</h3>
	<form action="/board/post" method="post">
		<button type="submit" name="modify"> Params ����(post?modify)</button>
	</form>
	
	<a href="/board/get?list"> params ����(get?list)</a>
</body>
</html>