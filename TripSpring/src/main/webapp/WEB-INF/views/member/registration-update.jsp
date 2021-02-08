<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<jsp:include page="../inc/header.jsp"/>
    
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
<%
//세션값 가져오기
// String id = request.getParameter("id");
// 로그인한 사용자 => 세션값 가져오기
// String id="kim";
// String id = (String)session.getAttribute("id");
// if(id==null){
// 	response.sendRedirect("loginForm.jsp");
// }

// MemberDAO mdao 객체생성
// MemberDAO mdao = new MemberDAO();
// MemberBean bb = getMember(id) 메서드호출
// MemberBean bb = mdao.getMember(id);

// String address = bb.getAddress();
// String p1 = "/";
// String addArray0 = "";
// String addArray1 = "";
// String addArray2 = "";
//정규표현식의 문자가 아니라면 그냥 대입해도 상관없다
// if(address!=null){
// 	String[] addArray = address.split(p1);
// 	for( int i = 0; i < addArray.length; i++ ){
// 		addArray0=addArray[0];
// 		addArray1=addArray[1];
// 		addArray2=addArray[2];
// 	}
// }else{	
// }
%>

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
                        <h2>K-트립 <span>회원정보를 수정해주세요.</span></h2>
                        <p class="subheading">수정하시고 수정하기버튼을 클릭해주세요.</p>
                    </div>
 
                </div>
                <!--forms-right-icons-->
                <div class="col-md-6-1 col-sm-12">
                    <div class="form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                                <h3>회원정보수정</h3>
                                <p>수정할 회원정보를 입력해주세요.</p>
                            </div>
                            <div class="form-top-right">
                                <i class="fa fa-pencil"></i>
                            </div>
                        </div>
                        <div class="form-bottom">
                            <form role="form" action='<c:url value="/member/update"/>' method="post" class="login-form">
                            <fieldset>
                            	<label>ID</label>
                             	<input type="text" name="id" class="form-control form-control_plus" value="${mb.id}" aria-describedby="basic-addon1" readonly>
                                <label>비밀번호</label>
                                <input type="password" name="pass" class="form-control form-control_plus" placeholder="비밀번호" aria-describedby="basic-addon1">
                                <label>비밀번호 재입력</label>
                                <input type="password" name="pass2" class="form-control form-control_plus" placeholder="비밀번호 재입력" aria-describedby="basic-addon1">
                           	 	<label>이름</label>
                                <input type="text" name="name" class="form-control form-control_plus" value="${mb.name}" aria-describedby="basic-addon1">
								<label>Email</label>
                                <input type="email" name="email" class="form-control form-control_plus" value="${mb.email}" aria-describedby="basic-addon1">
                                <label>Email 재입력</label>
                                <input type="email" name="email2" class="form-control form-control_plus" value="${mb.email}" aria-describedby="basic-addon1">
                                
                                <label>주소</label><br>
                                <input type="text"  name="postcode" id="sample4_postcode" value="${addArray0}" class="form-control form-control_plus_s" >
								<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="bu_gray_l bu_gray_l_r"><br>
								<input type="text" name="roadAddress"  id="sample4_roadAddress" value="${addArray1}" class="form-control form-control_plus" readonly>
								<span id="guide" style="color:#999;display:none"></span>
								<input type="text" name="detailAddress" id="sample4_detailAddress" value="${addArray2}" class="form-control form-control_plus">
                                
                                <label>전화번호</label>
                                <input type="text" name="phone" class="form-control form-control_plus" value="${mb.phone}" aria-describedby="basic-addon1">
                                <label>모바일</label>
                                <input type="text" name="mobile" class="form-control form-control_plus" value="${mb.mobile}" aria-describedby="basic-addon1">
                                <button type="submit" class="btn">수정하기</button>
                                <button type="button" class="btn" onclick="location.href='<c:url value="/member/delete"/>'">탈퇴하기</button>
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