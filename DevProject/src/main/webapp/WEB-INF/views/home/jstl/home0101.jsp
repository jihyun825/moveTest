<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>7장 JSP</h3>
	<p>JSTL태그들의 example:::c:out</p>
	<p>1)escapeXml속성의  기본값은true이고, 특수문자를 변화한다.</p>
	<table border="1" style="border-collapse: collapse;">
		<tr>
			<td>member.userId</td>
			<td>${member.userId }</td>
		</tr>
		<tr>
			<td>member.userId</td>
			<td><c:out value="${member.userId}"/></td>
		</tr>
	
	</table>
</body>
</html>