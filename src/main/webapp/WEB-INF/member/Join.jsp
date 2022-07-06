<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/resources/includes/link.jsp"%>
<link rel="stylesheet" href="resources/css/common.css">
</head>
<body>
	<div class = container-md style = "width:60%; margin:200px auto; text-align:center">
		<h1 class= "mb-5">회원가입</h1>
		<form action = "/MemberJoin.do" method ="post">
			<input type = email name = email placeholder = "email@example.com" class = "form-control m-3">
			<input type = password name  = pwd placeholder = "Enter Password" class = "form-control m-3">
			<input name = addr1 placeholder = "Enter address1" class = "form-control m-3"> 
			<input name = addr2 placeholder = "Enter address2" class = "form-control m-3">
			<input type = submit value = 가입하기 class = "btn btn-primary w-100 m-3"> 
			<input type = reset value = RESET class = "btn btn-primary w-100 m-3">
			<a href = "/" class = "btn btn-primary w-100 m-3">이전</a>
			<input type = "hidden" name ="flag" value = true>
		</form>
	</div>
</body>
</html>