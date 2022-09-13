<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join.jsp</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./script/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		// 필수 입력 대상들에 이벤트 걸어 걸어
		// id=join인 대상에 -> submit 이벤트
		$('#join').submit(function(){
// 			alert("전송 잘 되나 테서터");
			if($('.id').val()==""){
				//class=id인 대상의 -> value값이 -> 공백이랑 같냐?
				alert('아이디를 입력해주세요');
				$('.id').focus(); // id 칸에 커서 깜빡이게
				return false; // submit 하지 마라~~ 막아주고
			} // if
			
			// pw = pw2 일치해야 하니까
			if($('.pw').val()!=$('.pw2').val()){
				alert('비밀번호가 일치하지 않습니다. 다시 입력 바람');
				$('.pw2').focus();
				return false;
			}
			
			if($('.pw').val()==""){
				alert('비번을 입력해주세요');
				$('.pw').focus();
				return false;
			}
			
			if($('.name').val()==""){
				alert('이름을 입력해주세요');
				$('.name').focus();
				return false;
			}
			
			if($('.email').val()==""){
				alert('이메일을 입력해주세요');
				$('.email').focus();
				return false;
			}
			
			if($('.email').val()!=$('.email2').val()){
				alert('이메일이 일치하지 않습니다. 다시 입력 바람');
				$('.email2').focus();
				return false;
			}// if
			
		});// submit
		
		// 아이디 중복 체크 제어 (class=idDiv)
		// class=dup 버튼 대상-> 클릭했을 때  -> ajax로
		$('.dup').click(function(){
// 			alert('잘 되남 테스트');
			$.ajax({
				url:'./MemberIdCheck.me', // 가상주소로~~~~~~~~~~~~
				data:{ 'id':$('.id').val() },
				success:function(rData){
					$('.idDiv').html(rData)
				} // class=id의 value값 데이터를
					// idCheck.jsp로 들고 가서 중복 체크 해보고
					// 결과값 rData로 들고 와서 idDiv에 덮어 쓰겠다~~(html)
				
			});// ajax
			
		});// click
		
	});// ready

</script>
</head>
<body>
	<div id="wrap">
		<!-- 헤더 들어가는 곳 (inc/top.jsp 파일로 뗐음!!) -->
		<jsp:include page="../inc/top.jsp"/>
		<!-- 헤더 들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 본문메인이미지 -->
		<div id="sub_img_member"></div>
		<!-- 본문메인이미지 -->
		<!-- 왼쪽메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="#">Join us</a></li>
				<li><a href="#">Privacy policy</a></li>
			</ul>
		</nav>
		<!-- 왼쪽메뉴 -->
		<!-- 본문내용 -->
		<article>
			<h1>Join Us</h1>
			<form action="./MemberInsertAction.me" id="join" method="post">
				<fieldset>
					<!-- 필수 사항 -->
					<legend>Basic Info</legend>
					<label>User ID</label> <input type="text" name="id" class="id">
					<input type="button" value="dup. check" class="dup"><br>
					<!-- id 중복 체크 결과 나타나는 곳~~~ -->
					<label></label>
					<div class="idDiv" style="border: 1px solid black"></div><br>
					<label>Password</label> <input type="password" name="pw" class="pw"><br>
					<label>Retype Password</label> <input type="password" name="pw2" class="pw2"><br>
					<label>Name</label> <input type="text" name="name" class="name"><br>
					<label>E-Mail</label> <input type="email" name="email" class="email"><br>
					<label>Retype E-Mail</label> <input type="email" name="email2" class="email2"><br>
				</fieldset>

				<fieldset>
					<!-- 선택 사항 -->
					<legend>Optional</legend>
					<label>Address</label> <input type="text" name="address"><br>
					<label>Phone Number</label> <input type="text" name="phone"><br>
					<label>Mobile Phone Number</label> <input type="text" name="mobile"><br>
				</fieldset>
				<div class="clear"></div>
				<div id="buttons">
					<input type="submit" value="Submit" class="submit"> 
					<input type="reset" value="Cancel" class="cancel">
				</div>
			</form>
		</article>
		<!-- 본문내용 -->
		<!-- 본문들어가는 곳 -->

		<div class="clear"></div>
		<!-- 푸터들어가는 곳 (inc/bottom.jsp 파일로 뗐음) -->
		<jsp:include page="../inc/bottom.jsp"/>
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>