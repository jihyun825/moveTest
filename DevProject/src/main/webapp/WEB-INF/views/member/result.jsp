<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Success</h1>
	
	<table border="">
				<tr>
					<td>유저ID</td>
					<td>${member.userId}</td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td>${member.password}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${member.userName}</td>
				</tr>
				<tr>
					<td>E-mail</td>
					<td>${member.email}</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td>${member.dateOfBirth}</td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<c:if test="${member.gender eq 'male' }">
							<c:out value="남자"/>
						</c:if>
						<c:if test="${member.gender eq 'female'}">
							<c:out value="여자"/>
						</c:if>
						<c:if test="${member.gender eq 'other'}">
							<c:out value="기타 어떠한것"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>개발자 여부</td>
					<td>
						<c:if test="${member.developer eq 'y' }">
							<c:out value="개발자"/>
						</c:if>
						<c:if test="${member.developer ne 'y' }">
							<c:out value="개발자아녀요"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>외국인 여부</td>
					<td>
						<c:if test="${member.foreigner }">
							<c:out value="외국인"/>
						</c:if>
						<c:if test="${member.foreigner eq false }">
							<c:out value="한국인"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>국적</td>
					<td>
						<c:forEach items="${member.nationality}" var="nation">
							<c:if test="${nation eq 'korea' }">
								<c:out value="대한민국"/>
							</c:if>
							<c:if test="${nation eq 'germany' }">
								<c:out value="독일"/>
							</c:if>
							<c:if test="${nation eq 'austrailia' }">
								<c:out value="호주"/>
							</c:if>
							<c:if test="${nation eq 'canada' }">
								<c:out value="캐나다"/>
							</c:if>
							<c:if test="${nation eq 'usa' }">
								<c:out value="미국"/>
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
				<td>소유차량</td>
				<td>
					<c:forEach items="${member.cars}" var="car">
							<c:out value="${car }"/>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>취미</td>
				<td>
					<c:forEach items="${member.hobby}" var="hobby">
						<c:if test="${hobby eq 'sports' }">
								<c:out value="운동"/>
						</c:if>
						<c:if test="${hobby eq 'music' }">
								<c:out value="음악"/>
						</c:if>
						<c:if test="${hobby eq 'movie' }">
								<c:out value="영화"/>
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td>${member.address.postCode }</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${member.address.location }</td>
			</tr>
			<tr>
				<td>카드1 - 번호</td>
				<td>
				<c:forEach items = "${member.cardList}" var="card" varStatus="status">
					<c:if test="${status.index eq 0 }">
						<c:out value="${card.no}"/>
					</c:if>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<td>카드1 - 유효년월</td>
				<td>
					<c:forEach items = "${member.cardList}" var="card" varStatus="status">
						<c:if test="${status.index eq 0 }">
							<c:out value="${card.validMonth}"/>
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>카드2 - 번호</td>
				<td>
				<c:forEach items = "${member.cardList}" var="card" varStatus="status">
					<c:if test="${status.index eq 1 }">
						<c:out value="${card.no}"/>
					</c:if>
				</c:forEach>
				</td>
			</tr>
			<tr>
					<td>카드2 - 유효년월</td>
					<td>
						<c:forEach items = "${member.cardList}" var="card" varStatus="status">
							<c:if test="${status.index eq 1 }">
								<c:out value="${card.validMonth}"/>
							</c:if>
						</c:forEach>
				</tr>
			<tr>
				<td>소개</td>
				<td>
					${member.introduction }
				</td>
			</tr>
			</table>
</body>
</html>