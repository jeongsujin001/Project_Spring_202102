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
.portfolio-content .date{float:left; color:#888 !important; font-size: 12px;}
.portfolio-content .gobtn{float:right; font-weight: 800 !important;}
.portfolio-content .g-detail{padding-bottom:10px;}

.portfolio-content .g-content{
	overflow:hidden; 
	text-overflow:ellipsis;
	white-space:nowrap;
}
</style>
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
                        <h4>후기갤러리</h4>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6" style="text-align:right;">Home<span class="sep"> / </span><span class="current">후기갤러리</span></div>
                </div>
            </div>
        </div>

        </div>
    </section>


	<!-- 사진을 뿌리고, 링크는 사진이 있는 게시글로 이동하기 -->
    <section id="portfolio">
        <div class="container">
            <div class="row">
                <div class="section-heading text-center">
                    <div class="col-md-12 col-xs-12">
                        <h1>여행사진<span>을 업로드 해주세요!</span></h1>
                        <p class="subheading">여행갔었던 사진을 올려주세요. 한주에 가장 좋아요가 많은 사진에는 스타벅스 기프티콘이 수여됩니다.</p>
                    </div>
                </div>
            </div>
            <div class="row"> 
            
            
	<c:forEach var="gb" items="${galleryList }">
                 <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 portfolio-item">
                    <div class="portfolio-one">
                        <div class="portfolio-head">
                            <div class="portfolio-img">
                            	<a class="" href='<c:url value="/gallery/content?num=${gb.num}"/>'><img alt="" src='<c:url value="/resources/upload/${gb.file }"/>'></a>             
                            </div>
                        </div>
                        <!-- End portfolio-head -->
                        <div class="portfolio-content">
                            <h5 class="title">${gb.subject} </h5>
                            <p class="g-content">${gb.content}</p>
                            <p class="g-detail"><span class="date"><fmt:formatDate value="${gb.date}" type="both" pattern="yyyy-MM-dd"/></span><a href='<c:url value="/gallery/content?num=${gb.num}"/>'><span class="gobtn">바로가기</span></a></p>                      
                        </div>
                        <!-- End portfolio-content -->
                    </div>
                    <!-- End portfolio-item -->
                </div>  
	</c:forEach>
	
	                      
            </div><!-- row END -->
            
            
            <div class="table_bo">
				<div class="table_button">			
					<input type="button" value="글목록" class="bu_gray_l" onclick="location.href='<c:url value="/gallery/list"/>'">
					<c:if test="${! empty sessionScope.id }">
						<input type="button" value="글쓰기" class="bu_gray_g" onclick="location.href='<c:url value="/gallery/write"/>'">
					</c:if>	
					<c:if test="${ empty sessionScope.id }">	
						<input type="button" value="글쓰기" class="bu_gray_g" onclick="return loginCheck()">
					</c:if>	

				</div>

				<div class="table_search">
				<form action="gallerySearch.jsp" method="post">
					<input type="text" name="search" class="input_box">
					<input type="submit" value="search" class="bu_white_l">
				</form>
				</div>
			</div>		
					
				
        </div><!-- container END -->
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
		</div><!-- pagination END -->

    </section>

<jsp:include page="../inc/footer.jsp"/>



</body>

</html>