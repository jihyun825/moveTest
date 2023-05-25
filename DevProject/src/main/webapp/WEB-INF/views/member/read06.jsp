<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Read06</title>
<style type="text/css">
p{
font-weight: bold;}
</style>
</head>
<body>
	<h3>READ06 Result</h3>
	
		<p>user.address</p>
		user.userId : ${user.userId}<br>
		user.password : ${user.password}<br>
		user.userName : ${user.userName}<br>
		user.email : ${user.email}<br>
		user.birthday : ${user.birthday}<br>
		user.gender : ${user.gender}<br>
		user.hobby : ${user.hobby}
		
		<p>hobbyArray:</p>
		<c:forEach items="${user.hobbyArray }" var="hobby">
			-<c:out value="${hobby}"/>
		</c:forEach>
		<p>hobbyList:</p>
		<c:forEach items="${user.hobbyList }" var="hobby">
			-<c:out value="${hobby}"/>
		</c:forEach><br>
		user.foreigner : ${user.foreigner}<br>
		user.developer : ${user.developer}<br>
		user.nationality : ${user.nationality}<br>
		user.address.postCode : ${user.address.postCode }<br>
		user.address.location : ${user.address.location }<br>
		
		<p>cardList :</p>
		<c:forEach items="${user.cardList }" var="card">
			<c:out value="${card.no } ${card.validMonth }"/>
		</c:forEach>
		<p>carList :</p>
		<c:forEach items="${user.carList }" var="car">
			<c:out value="${car}"/>
		</c:forEach><br>
		
		user.introduction : ${user.introduction}<br>
		user.dateOfBirth : ${user.dateOfBirth}<br>
		
</body>
</html>