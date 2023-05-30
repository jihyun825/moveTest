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
	<p>JSTL태그들의 example:::c:set</p>
	<c:set value="${member.userId }" var="memberId"/>
	<table border="1" style="border-collapse: collapse;">
		<tr>
			<td>member.userId</td>
			<td>${memberId}</td>
		</tr>
	</table>
	
	<hr/>
	<c:set var="memId">${member.userId }</c:set>
	<table border="1" style="border-collapse: collapse;">
		<tr>
			<td>member.userId</td>
			<td>${memId}</td>
		</tr>
	</table>
</body>
</html>