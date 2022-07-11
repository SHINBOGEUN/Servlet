<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의정보</title>

<%@include file="/resources/includes/link.jsp" %>
<link rel="stylesheet" href="resources/css/common.css">

</head>
	<%@page import="com.korea.dto.MemberDTO" %>
	<%
		MemberDTO dto = (MemberDTO)request.getAttribute("dto");
	%>
<body>
	<div class="contanier-md" id="wrapper" style="width:80%; margin:100px auto;">
		<!-- TopMenu -->
		<%@include file="/resources/includes/topmenu.jsp" %>
		<!-- Nav -->
		<%@include file="/resources/includes/nav.jsp" %>
		<!-- MainContents -->
		<div id="maincontents" style="border: 1px solid gray; margin-top:15px;">
		<h3 align=center style="margin-top:30px;">회원정보</h3>
		
			<form action="/MemberUpdate.do" method="post">
				<table class="table w-75 table-striped" style="margin:100px auto; text-align:center;">
					<tr>
						<td>Email</td>
						<td><input name="email" value="<%=dto.getEmail() %>" disabled></td> <!-- disabled 비활성화 -->
					</tr>
					<tr>
						<td>Addr1</td>
						<td><input name="addr1" value="<%=dto.getAddr1() %>"></td>
					</tr>
					<tr>
						<td>Addr2</td>
						<td><input name="addr2" value="<%=dto.getAddr2() %>"></td>
					</tr>
					<tr>
						<td>password</td>
						<td><input type="password" name="pwd" value="<%=dto.getPwd() %>"></td>
					</tr>
					<tr>
						<td >
							<input type="submit" value="정보수정" class="btn btn-outline-dark w-100">
						</td>
						<td>
							<a class="btn btn-outline-dark w-100">메인이동</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- Footer -->
	</div>
</body>
</html>