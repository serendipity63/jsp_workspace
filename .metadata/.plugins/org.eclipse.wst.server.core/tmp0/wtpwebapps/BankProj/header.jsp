<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
*{margin:0 auto;}
a{text-decoration:none;}
.outerDiv{
width:100%;
height:100px;
background-color:orange;

}
 </style>
<div class="outerDiv">
	<i><h1 style="text-align:center;"> kosta bank</h1></i><br>
	<div class= "innerDiv">
	<div style="float:left; margin-right:10px;">
		<a href="main?page=makeaccount">계좌개설 </a> &nbsp;&nbsp;
		<a href="main?page=deposit">입금 </a> &nbsp;&nbsp;
		<a href="main?page=withdraw">출금 </a> &nbsp;&nbsp;
		<a href="main?page=accountinfoform">계좌조회 </a> &nbsp;&nbsp;
		<a href="main?page=allaccountinfo">전체계좌조회 </a>&nbsp;&nbsp;
	</div>
	<div style="float:right; margin-right:10px;">
		<a href="#">로그인 </a> &nbsp;&nbsp; 
		<a href="#">회원가입 </a> &nbsp;&nbsp;
	</div>
	</div>
</div>