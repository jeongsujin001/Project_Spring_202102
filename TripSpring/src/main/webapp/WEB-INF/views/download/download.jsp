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
                        <h4>자료실</h4>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6" style="text-align:right;">Home<span class="sep"> 	/ </span><span class="current"> 자료실</span></div>
                </div>
            </div>
        </div>
    </section>

    <section id="portfolio">
        <div class="container">
            <div class="row">
                <div class="section-heading text-center">
                    <div class="col-md-12 col-xs-12">
                        <h1>유용한<span>자료를 다운로드 하세요</span></h1>
                        <p class="subheading">유용한 정보가 가득한 정보를 다운로드 하세요.</p>
                    </div>
                </div>
            </div>
          
			<div class="top_recom_wrap">
			<!-- recom_list_wrap -->
				<div class="recom_list_wrap">
					<ul class="recom_list pop_box">
					<c:forEach var="bb" items="${downloadList }">
						<li>
							<a href='<c:url value="/download/content?num=${bb.num}"/>'>
								<p class="exp_tag">${bb.tag}</p>
								<p class="photo"><img alt="" src='<c:url value="/resources/upload/${bb.file }"/>' width="188" height="125"></p>
								<p class="exp_wrap">
									<span class="name">${bb.subject}</span>
									<span class="txt">${bb.content}</span>
									<span class="price"><fmt:formatDate value="${bb.date}" type="both" pattern="yyyy-MM-dd"/></span> 
								</p>
							</a>
						</li>
					</c:forEach>
					</ul>
				</div><!-- /recom_list_wrap -->
			</div><!-- /top_recom_wrap -->


			<div class="table_bo">
				<div class="table_button">
					<input type="button" value="글목록" class="bu_gray_l" onclick="location.href='<c:url value="download"/>'">				
					<c:if test="${! empty sessionScope.id }">
						<c:if test="${sessionScope.id eq 'admin'}">
						<input type="button" value="글쓰기" class="bu_gray_g" onclick="location.href='<c:url value="/download/write"/>'">
						</c:if>
					</c:if>	
				</div>
			</div><!-- /table_bo -->
			<div class="clear-fix"></div>


			<div class="pagination">
				<c:if test="${pbBean.startPage > pbBean.pageBlock}">
					<a class="num" href='<c:url value="/gallery/list?pageNum=${pbBean.startPage - pbBean.pageBlock}"/>'>이전</a>
				</c:if>
				<c:forEach var="i" begin="${pbBean.startPage}" end="${pbBean.endPage}" step="1">
					<a class="num" href='<c:url value="/gallery/list?pageNum=${i}"/>'>${i}</a>
				</c:forEach>
				<c:if test="${pbBean.endPage < pbBean.pageCount}">
					<a class="num" href='<c:url value="/gallery/list?pageNum=${pbBean.startPage + pbBean.pageBlock}"/>'>다음</a>
				</c:if>
			</div><!-- /pagination -->


        </div><!-- /container -->
    </section>
    
    
<jsp:include page="../inc/footer.jsp"/>

</body>

</html>