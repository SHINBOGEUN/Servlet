<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@page import="java.io.*"%>
	<%
	File dir = new File("c://upload");  //폴더 경로 연결
	
	File[] files = dir.listFiles(); //파일명을 다 가져온다.
	
	
	for (int i = 0; i < files.length; i++) {  
		//getName을 쓰면 파일명만 가져온다.
		out.println("<a href=/download.do?filename=" + files[i].getName().replaceAll(" ", "+") + ">" + files[i].getName()+ "</a><br>");
	}
	%>

</body>
</html>