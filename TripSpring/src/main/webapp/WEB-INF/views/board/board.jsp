<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<jsp:include page="../inc/header.jsp"/>

<script type="text/javascript">
function loginCheck() {
		alert("회원전용입니다.");
		return false;
}
</script>
<style type="text/css">
table.table_normal tr:hover{
	background: #f1f1f1;
	cursor: pointer;
	cursor: hand;
}

.pagination {margin: 20px auto; text-align: center; display: block;}
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
                        <h4>여행이야기</h4>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6" style="text-align:right;">Home<span class="sep"> 	/ </span><span class="current"> 여행이야기</span></div>
                </div>
            </div>
        </div>

        </div>
    </section>


    <!-- 게시판 -->
<article>


<p style="text-align: right;">전체게시글수:<%//=count%></p>
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
	
<c:forEach var="bb" items="${boardList }">
			<tr class="bgcol01" onclick="location.href='<c:url value="/board/content?num=${bb.num}"/>'">
					<td class="line0 ac mobileHide">
						${bb.num}</td>
					<td>
						${bb.subject}
						
						<c:if test="${bb.ccount > 0}">
							<span><b>&nbsp;[${bb.ccount}]</b></span>
 						</c:if>
					</td>
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


<div class="table_bo">
	<div class="table_button">
					<input type="button" value="글목록" class="bu_gray_l" onclick="location.href='<c:url value="/board/list"/>'">
					<c:if test="${! empty sessionScope.id }">
						<input type="button" value="글쓰기" class="bu_gray_g" onclick="location.href='<c:url value="/board/write"/>'">
					</c:if>	
					<c:if test="${ empty sessionScope.id }">	
						<input type="button" value="글쓰기" class="bu_gray_g" onclick="return loginCheck()">
					</c:if>	
		
	</div>

	<div class="table_search">
	<form action="boardSearch.jsp" method="post">
		<input type="text" name="search" class="input_box">
		<input type="submit" value="search" class="bu_white_l">
	</form>
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