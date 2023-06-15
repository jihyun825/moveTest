<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<!-- 로그인을 하지않은 경우 -->

<sec:authorize access = "isAnonymous()">
	<a href="/login">로그인</a>
</sec:authorize>
<sec:authorize access = "isAuthenticated()">
	<a href="/logout">로그아웃</a>
</sec:authorize>

<hr><br>
<a href="/board/list">Board</a>
<a href="/notice/list">Notice</a>

</body>
</html>
