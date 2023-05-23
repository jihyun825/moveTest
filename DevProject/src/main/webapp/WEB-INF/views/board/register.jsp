<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Register</title>
</head>
<body>
	<h3>Register</h3>
	
	<form action="/board/post" method="post">
		<button type="submit" name="register"> params 매핑(post?register)</button>
	</form>
	
	<a href="/board/get?list"> params 매핑(get?list)</a>
</body>
</html>