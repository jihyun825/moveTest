<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>4.표현언어</h3>
	<p>3)empty연산자 이용한 방법</p>
	<table border="1" style = "border-collapse: collapse;">
		<tr>
			<td>\${empty member }</td>
			<td>${empty member}</td>
		</tr>
		<tr>
			<td>\${empty member.userId }</td>
			<td>${empty member.userId }</td>
		</tr>
	</table>
</body>
</html>