<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
request.setAttribute("nickname","소니");
/*request.setAttribute("test","Request Test"); request 잘 안쓴다 */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>${sessionScope.test}  </h3>
<h3>${requestScope.nickname}  </h3>
<h3>${param.name}  </h3>

</body>
</html>