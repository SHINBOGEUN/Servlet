<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<!-- Multipart
클라이언트에서 서버로 페이지 요청시 업로드 파일을 포함하는 경우에 Post방식으로 body에 파일들을 나누어서 처리해야한다.
이때 여러requset에 나눠진 body들이 서버로 전돨되기 위해서 
multipart/form-data 옵션을 적용한다
 -->

	<form action="/upload1" method = "post" enctype = "multipart/form-data">
		upload's file  <input type = file name =test>
		<input type =submit value = upload>
	</form>
</body>
</html>