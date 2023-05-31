<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Spring form</h3>
	<p>모델에 Map타입의 데이터를 생성하여 추가한 후에 화면에 전달한다.</p>
	<form:form modelAttribute="member" method="post" action="/formtag/radio/result">
		<table>
			<tr>
				<td>성별</td>
				<td>
					<form:radiobutton path="gender" value="male" label="male"/>
					<form:radiobutton path="gender" value="female" label="female"/>
					<form:radiobutton path="gender" value="other" label="other"/>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>