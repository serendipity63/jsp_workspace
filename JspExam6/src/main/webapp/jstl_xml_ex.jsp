<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<x:parse var="xmldata">
<students>
	<student>
		<name>이재성</name>
		<age>31</age>
		<gender>남</gender>
		<phone>010-3456-1123</phone>
	</student>
	<student>
		<name>손흥민</name>
		<age>31</age>
		<gender>남</gender>
		<phone>010-4545-1123</phone>
	</student>
	<student>
		<name>김민재</name>
		<age>28</age>
		<gender>남</gender>
		<phone>010-4564-1123</phone>
	</student>
</students>
</x:parse>

<table border="1">
<tr>
<td>이름</td><td>나이</td><td>성별</td><td>전화번호</td>
</tr>
<x:forEach select="$xmldata//student">
<tr>
	<td><x:out select="./name"/></td>
	<td><x:out select="./age"/></td>
	<td><x:out select="./gender"/></td>
	<td><x:out select="./phone"/></td>
</tr>
</x:forEach>
</table>


</body>
</html>