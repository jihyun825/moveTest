<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registerModelArrtibuteForm</title>
</head>
<body>	
	<h3>3. @ModelAttibute</h3>
	<hr/>
	
	<p>1) 기본 자료형은 매개변수로 선언했을때 전달되는지 확인합니다.</p>
	<form action="/modelattribute/register01" method="post">
		userId : <input type="text" name = "userId" value="hongkd"><Br>
		password : <input type="text" name = "password" value="12345"><Br>
		<input type="submit" value = "register01"><br>
	</form>
	
		<p>2) 기본 자료형인 매개변수에 @modelAttribute어노테이션을 지정하여 데이터를 전달한다.</p>
	<form action="/modelattribute/register02" method="post">
		userId : <input type="text" name = "userId" value="hongkd"><Br>
		password : <input type="text" name = "password" value="12345"><Br>
		<input type="submit" value = "register02"><br>
	</form>
	
	<p>3)자바빈즈 규칙에 맞는 객체는 매개변수로 선언하면 기본적으로 전달된다.</p>
		<form action="/modelattribute/register03" method="post">
			userId : <input type="text" name = "userId" value="hongkd"><Br>
			password : <input type="text" name = "password" value="12345"><Br>
			<input type="submit" value = "register03"><br>
		</form>
</body>
</html>