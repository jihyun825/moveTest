<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AjaxRegisterForm</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h1>9.Ajax방식 요청처리</h1>
	
	<h3>Ajax방식 요청처리</h3>
	<hr/>
	
	<form>
		<p>userId : <input type="text" name="userId" value="hongkd" id="userId">
		<p>password : <input type="text" name="password" value="1234" id="password">
	</form>
	
	<p>1)URL 경로상의 경로 변수값을 @PathVariable 어노테이션을 지정하여 문자열 매개변수로 처리한다.</p>
	<button id="registerBtn01">registerBtn01</button>
	
	
	<p>2)URL 경로상의 여러개의 경로 변수값을 @PathVariable 어노테이션을 지정하여 여러개의 문자열 매개변수로 처리한다.</p>
	<button id="registerBtn02">registerBtn01</button>
	
	<p>3)객체타입의 JSon요청데이터 @RequestBody어노테이션을 지정하여 자바빈즈 매개변수로 처리한다.</p>
	<button id="registerBtn03">registerBtn03</button>
	
	<p>4)객체타입의 JSon요청데이터는 문자열 매개변수로 처리할 수 없다.</p>
	<button id="registerBtn04">registerBtn04</button>
	
	<p>5)요청 URL에 쿼리파라미터를 붙여서 전달하면 문자열 매개변수로 처리한다.</p>
	<button id="registerBtn05">registerBtn05</button>
	
	<p>6)JSON요청 데이터를 @Pathvariable 어노테이션을 지정한 문자열 매개변수와 @RequestBody 어노테이션을 지정한 자바빈즈 매개변수로 처리한다.</p>
	<button id="registerBtn06">registerBtn06</button>
	
	<p>7)객체배열타입의 JSON요청 데이터를 자바빈즈 요소를 가진 리스트 컬렉션 매개변수에 @RequestBody어노테이션을 지정하여 처리한다.</p>
	<button id="registerBtn07">registerBtn07</button>
	
	<p>8)중첩된 객체타입의JSON 요청 데이터를 @RequestBody 어노테이션을 지정하여 중첩된 자바빈즈 매개변수로 처리한다..</p>
	<button id="registerBtn08">registerBtn08</button>
	
	<p>9)중첩된 객체타입의 JSON 요청 데이터를 @RequestBody어노테이션을 지정하여 중첩된 자바빈즈 매개변수로 처리한다.</p>
	<button id="registerBtn09">registerBtn09</button>
	
	
	
	
	
	
</body>
<script type="text/javascript">
	$(function(){
	// URL 경로상의 경로 변수값을 @PathVariable 어노테이션을 지정하여 문자열 매개변수로 처리한다.
		$('#registerBtn01').on('click',function(){
			$.get("/ajax/register/hongkd",function(result){
				console.log("result: "+result);
				if(result === "SUCCESS"){
					alert(result);
				}
			});
		});
	
		$('#registerBtn02').on('click',function(){
			
			var userId = $('#userId').val();
			var password = $('#password').val();
			var userObject = {
					userId : userId,
					password : password
			}
			
			$.ajax({
				type: "post",
				url : "/ajax/register/"+userId + "/" + password,
				data : JSON.stringify(userObject),
				contentType : "application/json; charset = utf-8",
				success : function(result){
					console.log(result);
					if(result === "SUCCESS"){
						alert(result);
					}
				}
				
			});
			
		});
		$('#registerBtn03').on('click',function(){
			
			var userId = $('#userId').val();
			var password = $('#password').val();
			var userObject = {
					userId : userId,
					password : password
			}
			$.ajax({
				type:'post',
				url : '/ajax/register03',
				data : JSON.stringify(userObject),
				contentType:"application/json; charset = utf-8",
				success : function(result){
					console.log(result);
					if(result==='SUCCESS'){
						alert(result);
					}
				}
			});
		});
		
		$('#registerBtn04').on('click',function(){
			var userId = $('#userId').val();
			var password = $('#password').val();
			var userObject = {
					userId : userId,
					password : password
			}
			$.ajax({
				type: 'post',
				url : '/ajax/register04',
				data : JSON.stringify(userObject),
				contentType:"application/json; charset = utf-8",
				success : function(result){
					console.log(result);
					if(result==='SUCCESS'){
						alert(result);
					}
				}
				
			})
			
		});
		
		$('#registerBtn05').on('click',function(){
			var userId = $('#userId').val();
			var password = $('#password').val();
			var userObject = {
					userId : userId,
					password : password
			}
			$.ajax({
				type: 'post',
				url : '/ajax/register05?userId='+userId,
				data : JSON.stringify(userObject),
				contentType:"application/json; charset = utf-8",
				success : function(result){
					console.log(result);
					if(result==='SUCCESS'){
						alert(result);
					}
				}
				
			})
			
		});
		
		$('#registerBtn06').on('click',function(){
			var userId = $('#userId').val();
			var password = $('#password').val();
			var userObject = {
					userId : userId,
					password : password
			}
			$.ajax({
				type: 'post',
				url : '/ajax/register06/'+userId,
				data : JSON.stringify(userObject),
				contentType:"application/json; charset = utf-8",
				success : function(result){
					console.log(result);
					if(result==='SUCCESS'){
						alert(result);
					}
				}
				
			})
			
		});
		
		$('#registerBtn07').on('click',function(){
			var userObjectArray = [
					{userId :"name01",password : "pw1"},
					{userId :"name02",password : "pw2"}
			];
			$.ajax({
				type: 'post',
				url : '/ajax/register07',
				data : JSON.stringify(userObjectArray),
				contentType:"application/json; charset = utf-8",
				success : function(result){
					console.log(result);
					if(result==='SUCCESS'){
						alert(result);
					}
				}
			});
		});
		//8.중첩된 객체타입의 JSON 요청 데이터를 @RequestBody어노테이션을 지정하여 중첩된 자바빈즈 매개변수로 처리한다.
		$('#registerBtn08').on('click',function(){
			var userId = $('#userId').val();
			var password = $('#password').val();
			var userObject = {
					userId : userId,
					password : password,
					address : {
						postCode:"010908",
						location : "Daejeon"
					}
			}
			$.ajax({
				type: 'post',
				url : '/ajax/register08',
				data : JSON.stringify(userObject),
				contentType:"application/json; charset = utf-8",
				success : function(result){
					console.log(result);
					if(result==='SUCCESS'){
						alert(result);
					}
				}
			});
		});
		//중첩된 객체타입의 JSON 요청 데이터를 @RequestBody어노테이션을 지정하여 중첩된 자바빈즈 매개변수로 처리한다.
		$('#registerBtn09').on('click',function(){
			var userId = $('#userId').val();
			var password = $('#password').val();
			var userObject = {
					userId : userId,
					password : password,
					cardList : [
						{no: "12345",validMonth:"2022-01-18"},
						{no: "56789",validMonth:"2023-05-25"}
					]
			};
			$.ajax({
				type: 'post',
				url : '/ajax/register09',
				data : JSON.stringify(userObject),
				contentType:"application/json; charset = utf-8",
				success : function(result){
					console.log(result);
					if(result==='SUCCESS'){
						alert(result);
					}
				}
			});
		});
		
	});
	
</script>
</html>