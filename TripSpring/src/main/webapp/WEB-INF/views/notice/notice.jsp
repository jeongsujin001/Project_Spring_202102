<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<jsp:include page="../inc/header.jsp"/>

<style type="text/css">
.pagination {margin: 20px auto; text-align: center; display: block;}
</style>
<style type="text/css">
table.table_normal tr:hover{
	background: #f1f1f1;
	cursor: pointer;
	cursor: hand;
}
</style>

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
                        <h4>공지사항</h4>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6" style="text-align:right;">Home<span class="sep"> 	/ </span><span class="current"> 공지사항</span></div>
                </div>
            </div>
        </div>

        </div>
    </section>


    <!-- 게시판 -->
<article>



<table class="table_normal">
		<colgroup>
			<col width="8%" class="mobileHide">
			<col width="*">
			<col width="12%">
			<col width="16%" class="mobileHide">
			<col width="8%" class="mobileHide">
		</colgroup>
		<thead>
			<tr>
				<th scope="col" class="line0 mobileHide">번호</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col" class="mobileHide">작성일</th>
				<th scope="col" class="mobileHide">조회수</th>
			</tr>
		</thead>
		<tbody>
	
<c:forEach var="bb" items="${noticeList }">
			<tr class="bgcol01" onclick="location.href='<c:url value="/notice/content?num=${bb.num}"/>'">
					<td class="line0 ac mobileHide">
					<img src='<c:url value="/resources/img/notice.gif"/>'></td>
					<td>
						${bb.subject}</td>
					<td class="line0 ac mobileHide">
						${bb.name}</td>
					<td class="line0 ac mobileHide">
						<fmt:formatDate value="${bb.date}" type="both" pattern="yyyy-MM-dd"/></td>
					<td class="line0 ac mobileHide">
						${bb.readcount}</td>
				</tr>
</c:forEach>
		
			</tbody>
	</table>

<!-- <div class="pagination"> -->
<!-- 	        <strong>1</strong>&nbsp;<a class="num" href="?pageIndex=2" onclick="doList(2);return false; ">2</a>&nbsp;<a class="num" href="?pageIndex=3" onclick="doList(3);return false; ">3</a>&nbsp; -->
<!-- </div> -->

<div class="table_bo">
	<div class="table_button">
		<input type="button" value="글목록" class="bu_gray_l" onclick="location.href='notice.jsp'">
			
	<c:if test="${! empty sessionScope.id }">
		<c:if test="${sessionScope.id eq 'admin'}">
			<input type="button" value="글쓰기" class="bu_gray_g" onclick="location.href='notice-insert.jsp'">
		</c:if>
	</c:if>	
		
	</div>



</div>
<div class="clear-fix"></div>


<div class="pagination">
<c:if test="${pbBean.startPage > pbBean.pageBlock}">
	<a class="num" href='<c:url value="/board/list?pageNum=${pbBean.startPage - pbBean.pageBlock}"/>'>이전</a>
</c:if>
<c:forEach var="i" begin="${pbBean.startPage}" end="${pbBean.endPage}" step="1">
	<a class="num" href='<c:url value="/board/list?pageNum=${i}"/>'>${i}</a>
</c:forEach>
<c:if test="${pbBean.endPage < pbBean.pageCount}">
	<a class="num" href='<c:url value="/board/list?pageNum=${pbBean.startPage + pbBean.pageBlock}"/>'>다음</a>
</c:if>
</div>


</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
    
<jsp:include page="../inc/footer.jsp"/>

</body>

</html>