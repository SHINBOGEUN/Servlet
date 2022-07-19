<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@include file="/resources/includes/link.jsp"%>
<link rel="stylesheet" href="/resources/css/common.css">

</head>
<body>

	<div class="container-md" id=wrapper style="margin: 100px auto;">
		<!-- TopMenu -->
		<%@include file="/resources/includes/topmenu.jsp"%>

		<!-- NAV -->
		<%@include file="/resources/includes/nav.jsp"%>

		<!-- MainContents -->
		<div id=maincontents style="margin-top: 15px;">
			w
			<!-- PagePath -->
			<div>
				<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item"><a href="#">Board</a></li>
						<li class="breadcrumb-item active" aria-current="page">Read</li>
					</ol>
				</nav>
			</div>
			<h1>글내용</h1>
			<%@page import="com.korea.dto.*"%>
			<%
			BoardDTO dto = (BoardDTO) request.getAttribute("dto");
			String nowPage = (String) request.getAttribute("nowPage");

			String[] filelist = null;
			String[] filesize = null;
			if (dto.getFilename() != null) {
				filelist = dto.getFilename().split(";");
				filesize = dto.getFilesize().split(";");
			}
			%>
			<form action="" method="post">
				<input name="title" class="form-control mb-3 w-50"
					value="<%=dto.getTitle()%>"> <input name="writer"
					class="form-control mb-3 w-50" value="<%=dto.getWriter()%>">


				<textarea name="content" class="form-control mb-3 w-50"
					style="height: 500px;"><%=dto.getContent()%></textarea>


				<input type=submit value="글수정" class="btn btn-primary"> <a
					href="#" class="btn btn-primary">리스트</a> <a href="#"
					class="btn btn-primary">글삭제</a>
				<button type="button" class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#staticBackdrop">첨부파일 보기</button>
			</form>
			<!-- Modal -->
			<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
				data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div
					class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="staticBackdropLabel">첨부파일 리스트</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<!-- 첨부파일  -->
							<%@page import="java.net.URLEncoder"%>
							<%
							if (filelist != null) {
								for (int i = 0; i < filelist.length; i++) {

									String tmpfilename = filelist[i].substring(0, filelist[i].lastIndexOf("_"));
									tmpfilename += filelist[i].substring(filelist[i].lastIndexOf("."), filelist[i].length());

									filelist[i] = URLEncoder.encode(filelist[i], "utf-8").replaceAll("\\+", "%20");
									out.println("<a href=/Board/download.do?filename=" + filelist[i] + ">" + tmpfilename + "(" + filesize[i]
									+ " byte)</a><br>");
								}
							} else {
								out.println("파일 없음");
							}
							%>

						</div>
						<div class="modal-footer">
							<a id="downall" class="btn btn-primary" href="#">모두받기(NoZIP)</a>
							<a class="btn btn-primary" href="/Board/downloadAll.do">모두받기(ZIP)</a>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">닫기</button>
						</div>

						<form name = "multiform">
							<%
							for (int i = 0; i < filelist.length; i++) {
							%>
							<input type=hidden name=file value=<%=filelist[i]%>>
							<%
							}
							%>
						</form>
						
						<script>
						    $(document).ready(function () {
						        form = document.multiform;
						        var iFrameCnt = 0;
						        $('#downall').click(function (event){
						            for (i = 0; i < form.childElementCount; i++) {
						                fileName = form[i].value;
						                var url = "/Board/download.do?filename=" + fileName;
						                fnCreateIframe(iFrameCnt);
						                $("iframe[name=" + iFrameCnt + "]").attr("src", url);
						                iFrameCnt++;
						                fnSleep(1000);
						            }
						        });
						        fnCreateIframe = function (name){
						            var frm = $('<iframe name = "' + name + '" style = " display: none;"></iframe>');
						            frm.appendTo("body");
						        }
						        fnSleep = function (delay) {
						            var start = new Date().getTime();
						            while (start + delay > new Date().getTime());
						        };
						    });
						</script>
					</div>
				</div>
			</div>

		</div>

		<!-- Footer -->


	</div>


</body>
</html>