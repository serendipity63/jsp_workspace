<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>${sessionScope.id}님환영합니다.</h3>
	<table border="1">
		<tr>
			<th>아이디</th>
			<td>${user.id}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${user.name}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${user.address}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${user.email}</td>
		</tr>
	</table>
</body>
</html>