<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>

<jsp:include page="../inc/header.jsp"/>

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
<form action='<c:url value="/board/delete"/>' method="post">
<input type="hidden" name="num" value="${num }">
<table class="table_normal" style="width:60%; margin:60px auto;">
		<colgroup>
			<col width="50%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pass" class="form-control" name="subject" style="width:90%;"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center; border-bottom: 1px solid #fff; padding-top:30px;"><input type="submit" value="글삭제" class="bu_gray_l"></td>
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