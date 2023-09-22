<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dto.Account"%>
<%
	Account acc = (Account) request.getAttribute("acc");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	margin: 0 auto;
}

.container {
	padding: 10px;
	border: 1px solid;
	width: 280px;
}

.header {
	height: 40px;
}

.row {
	height: 30px;
}

.title {
	width: 70px;
	float: left;
	font-weight: bold;
}

.input {
	float: left;
}

input[type='submit'] {
	font-weight: bold;
	width: 120px;
	background-color: lightgrey;
}
</style>
</head>
<body>
<% pageContext.include("header.jsp"); %>

	<center>
		<div class="header">
			<h3>계좌조회</h3>
		</div>
		<div class="container" id='result'>
			<div class="row">
				<div class="title">계좌번호</div>
				<div class="input">
					<input type="text" name="id" id='id' disabled value= <%=acc.getId() %>>
				</div>
			</div>
			<div class="row">
				<div class="title">이름</div>
				<div class="input">
					<input type="text" name="name" id='name' disabled value= <%=acc.getName() %>>
				</div>
			</div>
			<div class="row">
				<div class="title">잔액</div>
				<div class="input">
					<input type="text" name="money" id='money' disabled value= <%=acc.getBalance() %>>
				</div>
			</div>
			<div class="row">
				<div class="title">종류</div>
				<div class="input">
					<input type="text" name="type" id='type' disabled value=<%=acc.getType() %>>
				</div>
			</div>
			<div class="row">
				<div class="title">등급</div>
				<div class="input">
					<input type="text" name="grade" id='grade' disabled value<%=acc.getGrade() %>>
				</div>
			</div>
		</div>
	</center>
</body>
</html>