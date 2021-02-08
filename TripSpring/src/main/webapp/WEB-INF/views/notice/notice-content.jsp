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
				<td>${nb.subject }</td>
				<th>작성일</th>
				<td>${nb.date }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${nb.name }</td>
				<th>조회수</th>
				<td>${nb.readcount }</td>
			</tr>
			<tr>
				<td colspan="4" class="tableTBox">
				${nb.content }
				</td>
			</tr>
		</tbody>
	</table>
	 <div id="commentArea" class="comment_area">
	</div><!-- commentArea -->
	
	<div class="buttonBox">
		<div class="buttonBoxR">
			

	<c:if test="${! empty sessionScope.id }">
		<c:if test="${sessionScope.id eq 'admin'}">
			<input type="button" value="글수정" class="bu_gray_l" onclick="location.href='<c:url value="/notice/update?num=${nb.num}"/>'">
			<input type="button" value="글삭제" class="bu_gray_l" onclick="location.href='<c:url value="/notice/delete?num=${nb.num}"/>'">
		</c:if>
	</c:if>	
		
		
		<input type="button" value="글목록" class="bu_gray_l" onclick="location.href='<c:url value="/notice/list"/>'">
		
	<c:if test="${! empty sessionScope.id }">
		<c:if test="${sessionScope.id eq 'admin'}">
			<input type="button" value="글쓰기" class="bu_gray_g" onclick="location.href='<c:url value="/notice/write"/>'">
		</c:if>
	</c:if>	


		
		</div>
	</div>
</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
    
<jsp:include page="../inc/footer.jsp"/>

</body>

</html>