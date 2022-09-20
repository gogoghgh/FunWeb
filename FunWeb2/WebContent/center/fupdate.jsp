<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update.jsp</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
</head>
<body>
	<div id="wrap">
		<!-- 헤더들어가는 곳 (inc/top.jsp 파일로 뗐음!!) -->
		<jsp:include page="../inc/top.jsp"/>
		<!-- 헤더들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 메인이미지 -->
		<div id="sub_img_center"></div>
		<!-- 메인이미지 -->

		<!-- 왼쪽메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="#">Notice</a></li>
				<li><a href="#">Public News</a></li>
				<li><a href="#">Driver Download</a></li>
				<li><a href="#">Service Policy</a></li>
			</ul>
		</nav>
		<!-- 왼쪽메뉴 -->

		<!-- 게시판 -->
		<article>
			<h1>File Notice Update</h1>
			<form action="./FileBoardUpdatePro.bo?bno=${dto.bno }" method="post" enctype="multipart/form-data">
					<input type="hidden" name="bno" value="${dto.bno }">
					<input type="hidden" name="pass" value="1234">  
					<!-- 비번 안 적어도 되니까!!! 왜냐면,, 로그인 한 상태잖아 !!!! 아하 걍 그래서 임의로 1234로 정해놓은ㄱ ㅓ구나 헐 소름 -->
				<table id="notice">
					<tr>
						<td>글쓴이</td>
						<td><input type="text" name="name" readonly value="${dto.name }"> </td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="subject" value="${dto.subject }"> </td>
					</tr>
					<tr>
						<td>파일</td>
						<td><input type="file" name="file"> ${dto.file } (value값 없어서 여기 적은거!! 이해 부탁ㅎ)
							<input type="hidden" name="oldfile" value="${dto.file }"> 
								<!-- 새롭게 첨부할 파일 없으면, 기존 파일 유지해야 하니까 기존 파일 들고 가도록 -->
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea rows="10" cols="20" name="content" value="${dto.content }"></textarea></td>
					</tr>
				</table>
				
				<div id="table_search">
					<input type="submit" value="글 수정" class="btn">
				</div>
				
			</form>
			<div class="clear"></div>
			<div id="page_control"></div>
		</article>
		<!-- 게시판 -->
		
		<!-- 본문들어가는 곳 -->
		<div class="clear"></div>
		
		<!-- 푸터들어가는 곳 (inc/bottom.jsp 파일로 뗐음) -->
		<jsp:include page="../inc/bottom.jsp"/>
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>