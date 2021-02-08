<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<jsp:include page="../inc/header.jsp"/>

<script type="text/javascript">
function check() {
	if(document.form.subject.value==""){
		alert("제목을 입력하세요.");
		document.form.subject.focus();
		return false;
	}	

	if(document.form.content.value==""){
		alert("본문을 입력하세요.");
		document.form.content.focus();
		return false;
	}
	
	if(document.form.pass.value==""){
		alert("패스워드를 입력하세요.");
		document.form.pass.focus();
		return false;
	}
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
        <div class="page_info">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-sm-8 col-xs-6">
                        <h4>여행이야기</h4>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6" style="text-align:right;">Home<span class="sep"> 	/ </span><span class="current"> 가입인사</span></div>
                </div>
            </div>
        </div>

        </div>
    </section>


    <!-- 게시판 -->
<article>
<form name="form" action='<c:url value="/board/write"/>' method="post" onsubmit="return check()">
<table class="table_normal">
		<colgroup>
			<col width="15%">
			<col width="*">
			<col width="15%">
			<col width="20%">
		</colgroup>
		<tbody>

			<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" name="subject" class="form-control" style="width:99%;"></td>
			</tr>
			<tr>

				<th>글쓴이</th>
				<td colspan="3">${sessionScope.id }<input type="hidden" name="name" value="${sessionScope.id }" class="form-control" style="width:50%;"><!-- <input type="text" name="name" class="form-control" name="subject" style="width:50%;"> --></td>
				<!-- <th>지역</th>
				<td><input type="radio"name="map" value="국내"> 국내      <input type="radio" name="map" value="해외" style="margin-left:10px;"> 해외</td> -->
			</tr>
			<tr>
				<td colspan="4" class="tableTBox"><textarea name="content" rows="20"  class="form-control" style="width:100%;"></textarea></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pass" class="form-control" style="width:50%;"></td>
				<td colspan="2" style="text-align:right;">			
				<input type="button" value="글목록" class="bu_gray_l" onclick="location.href='board.jsp'">
				<input type="submit" value="글쓰기" class="bu_gray_g">
				</td>
			</tr>
		</tbody>
	</table>


</form>	


</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
    
<jsp:include page="../inc/footer.jsp"/>

</body>

</html>