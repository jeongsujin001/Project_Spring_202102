<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<jsp:include page="../inc/header.jsp"/>
    
<script type="text/javascript">
// function openIdChk(){
// 	url="idCheckForm.jsp?id="+document.form.id.value;
// 	window.open(url,"chkForm","width=500,height=300,resizable=no,scrollbars=no")
// }

// function inputIdChk(){
// 	document.form.inDuplication.value = "0";
// }
function check() {
	if(document.form.id.value==""){
		alert("아이디를 입력하세요.");
		document.form.id.focus();
		return false;
	}	
	
	if(document.form.pass.value==""){
		alert("패스워드를 입력하세요.");
		document.form.pass.focus();
		return false;
	}
	
	if(document.form.pass2.value==""){
		alert("패스워드 확인을 입력하세요.");
		document.form.pass2.focus();
		return false;
	}
	
	if(document.form.pass.value!=document.form.pass2.value){
		alert("패스워드가 같지않습니다.");
		document.form.pass2.focus();
		return false;
	}	

	if(document.form.name.value==""){
		alert("이름을 입력하세요.");
		document.form.name.focus();
		return false;
	}	
	
	if(document.form.email.value==""){
		alert("메일주소를 입력하세요.");
		document.form.email.focus();
		return false;
	}

	if(document.form.email2.value==""){
		alert("메일주소 확인을 입력하세요.");
		document.form.email2.focus();
		return false;
	}

	if(document.form.email.value!=document.form.email2.value){
		alert("메일주소가 같지않습니다.");
		document.form.email2.focus();
		return false;
	}
	
	
	if(document.form.postcode.value==""){
		alert("주소를 입력하세요.");
		document.form.postcode.focus();
		return false;
	}

	if(document.form.detailAddress.value==""){
		alert("상세주소를 입력하세요.");
		document.form.detailAddress.focus();
		return false;
	}

	if(document.form.phone.value==""){
		alert("전화번호를 입력하세요.");
		document.form.phone.focus();
		return false;
	}
	
	var form = document.form;
	
	if(form.idDuplication.value == 0){
		alert("아이디 중복체크를 해주세요.");
		return false;
	}
}
</script>

<script src='<c:url value="/resources/script/jquery-3.5.1.js"/>'></script>
<script type="text/javascript">
	$(document).ready(function(){	
		//  class="dup" 버튼을 클릭했을때  
		//  id="myid"아이디 val() 가지고  dupCheck2.jsp
		//  디비연결  아이디중복 , 아이디사용가능 출력
		//  출력결과를 받아서  div id="re" html() 넣기
		
		$('.dup').click(function(){
			$.ajax('<c:url value="/member/idcheck"/>',{
				data:{id:$('#id').val()},
				success:function(rdata){
					if(rdata=="iddup"){
						rdata="아이디 중복";
						$('input[name=idDuplication]').attr('value',0);
					}else{
						rdata="아이디 사용가능";
						$('input[name=idDuplication]').attr('value',1);
					}
					$('.re').html(rdata);
				}
			});
		});
	});
</script>
    
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>


<body id="wrapper">

<jsp:include page="../inc/top_menu.jsp"/>

    <section id="top_banner">
        <div class="banner">
            <div class="inner text-center">
                <h2>일상을 여행으로!</h2>
            </div>
        </div>
    </section>



    <section id="login-reg">
        <div class="container">
            <!-- Top content -->
            <div class="row">
                <div class="col-md-6-1 col-sm-12 forms-right-icons">
                    <div class="section-heading">
                        <h2>K-트립 <span>회원가입해주세요.</span></h2>
                        <p class="subheading">회원가입하셔서 많은 정보와 혜택을 누리세요.</p>
                    </div>
                    <!-- <div class="row">
                        <div class="col-xs-2 icon"><i class="fa fa-laptop"></i></div>
                        <div class="col-xs-10 datablock">
                            <h4>100% 반응형</h4>
                            <p>K-트립은 반응형 웹사이트로 PC화면,테블릿,스마트폰 어디에서든 즐길수 있습니다.</p>
                        </div>
                    </div> -->
                    <!-- <div class="row">
                        <div class="col-xs-2 icon"><i class="fa fa-bullhorn"></i></div>
                        <div class="col-xs-10 datablock">
                            <h4>갖가지 기능</h4>
                            <p>K-트립으로 로그인을 하면, 사진을 업로드할수있으며, 갖가지 정보를 공유하고 얻을수있습니다. 자료실에는 여행가이드북을 다운로드 받을수있습니다.</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-2 icon"><i class="fa fa-support"></i></div>
                        <div class="col-xs-10 datablock">
                            <h4>고객 서비스센터</h4>
                            <p>K-트립은 고객편의를 최우선시하고있습니다. 이용시 문의사항이 있으면 언제든 연락해주시기 바랍니다.</p>
                        </div>
                    </div> -->

                </div>
                <!--forms-right-icons-->
                <div class="col-md-6-1 col-sm-12">
                    <div class="form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                                <h3>회원가입</h3>
                                <p>회원정보를 입력하여 회원가입을 해주세요.</p>
                            </div>
                            <div class="form-top-right">
                                <i class="fa fa-pencil"></i>
                            </div>
                        </div>
                        <div class="form-bottom">
                           <form name="form" action='<c:url value="/member/registration"/>' method="post" class="login-form" onsubmit="return check()">
                            <fieldset>
                            	<label>ID</label><br>
                             	<input type="text" name="id" id="id" class="form-control form-control_plus_s" placeholder="ID" aria-describedby="basic-addon1">
                             	<input type="button" onclick="openIdChk()" value="아이디 중복체크" class="bu_gray_l bu_gray_l_r dup"> <span class="re" style="color:#FF0000; font-weight: bold;"></span>
<!--                              	<div id="re"></div><br> -->
                             	<input type="hidden" name="idDuplication" value="0"><br>
                                <label>비밀번호</label>
                                <input type="password" name="pass" class="form-control form-control_plus" placeholder="비밀번호" aria-describedby="basic-addon1">
                                <label>비밀번호 재입력</label>
                                <input type="password" name="pass2" class="form-control form-control_plus" placeholder="비밀번호 재입력" aria-describedby="basic-addon1">
                           	 	<label>이름</label>
                                <input type="text" name="name" class="form-control form-control_plus" placeholder="이름" aria-describedby="basic-addon1">
								<label>Email</label>
                                <input type="email" name="email" class="form-control form-control_plus" placeholder="Email" aria-describedby="basic-addon1">
                                <label>Email 재입력</label>
                                <input type="email" name="email2" class="form-control form-control_plus" placeholder="Email 재입력" aria-describedby="basic-addon1">
                                
                                <label>주소</label><br>
                                <input type="text" name="postcode" id="sample4_postcode" placeholder="우편번호" class="form-control form-control_plus_s" >
								<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="bu_gray_l bu_gray_l_r"><br>
								<input type="text" name="roadAddress" id="sample4_roadAddress" placeholder="도로명주소" class="form-control form-control_plus" readonly>
								<span id="guide" style="color:#999;display:none"></span>
								<input type="text" name="detailAddress" id="sample4_detailAddress" placeholder="상세주소" class="form-control form-control_plus">
	
                                <!--  <label>주소</label>
                                <input type="text" name="address" class="form-control form-control_plus" placeholder="주소" aria-describedby="basic-addon1">-->
                                <label>전화번호</label>
                                <input type="text" name="phone" class="form-control form-control_plus" placeholder="전화번호" aria-describedby="basic-addon1">
                                <label>모바일</label>
                                <input type="text" name="mobile" class="form-control form-control_plus" placeholder="모바일" aria-describedby="basic-addon1">
                                <button type="submit" class="btn">회원가입하기</button> 
                                <!--<input type="submit" value="회원가입하기" class="btn">-->
							</fieldset>
                           </form>
                                 
                        </div>
                    </div>

                </div>
			</div>
		</div>
		
		

		
    </section>

<jsp:include page="../inc/footer.jsp"/>

</body>

</html>