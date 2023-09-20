<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name="";
	String age="";
	String cookie= request.getHeader("Cookie");
	if(cookie!=null){
		Cookie[] cookies= request.getCookies();
		for(Cookie c : cookies) {
			if(c.getName().equals("name")){
				name=c.getValue();
			} else if(c.getName().equals("age")){
				age=c.getValue();
			}
				
		}
	}
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>이름 =<%= name %> </h2>
<h2>나이 =<%= age %> </h2>

</body>
</html>