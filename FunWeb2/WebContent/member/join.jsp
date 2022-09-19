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
					
					
<!-- API 갖다 쓰기 -->
<label>PostCode</label>
<input type="text" id="sample6_postcode" placeholder="우편번호" name="postcode">
<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>

<label>Address</label>
<input type="text" id="sample6_address" placeholder="주소" name="address"><br>
<label></label>
<input type="text" id="sample6_detailAddress" placeholder="상세주소" name="address2">
<input type="text" id="sample6_extraAddress" placeholder="참고항목" name="address3"> <br>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
					
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