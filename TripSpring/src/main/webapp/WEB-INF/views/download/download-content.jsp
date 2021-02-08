<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>

<jsp:include page="../inc/header.jsp"/>

<script type="text/javascript">
$(document).ready(function(){
	  $(".tab ul li").click(function(){
		  $(".tab ul li").removeClass('on');
		  $(".tab .conBox").removeClass('on');
		  $(this).addClass('on');
		  $("#"+$(this).data('id')).addClass('on');
	  });  
		 
});

function loginCheck() {
		alert("회원전용입니다.");
		return false;
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
                        <h4>자료실</h4>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6" style="text-align:right;">Home<span class="sep"> 	/ </span><span class="current"> 자료실</span></div>
                </div>
            </div>
        </div>

        </div>
    </section>


    <section id="about-page-section-3">
        <div class="container">
            <div class="row">

                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-5">
                 	<img height="" width="auto" src='<c:url value="/resources/upload/${db.file }"/>' class="attachment-full img-responsive" alt="">
                </div>
                
                <div class="col-xs-12 col-sm-6 col-md-6 col-lg-7 text-align">
                    <p class="dcontent-txt">
                       	<span>${db.tag }</span><br><br>${db.subject }
                    </p>
                    <a href='<c:url value="/download/filedown?file_name=${db.dfile }"/>' class="btn btn-primary slide">다운받기<i class="fa fa-caret-right"></i></a>
                             
					<br><br>
	
				
				<form action='<c:url value="/download/insertLike"/>' method="GET">
						<div class="like">

							<c:if test="${! empty sessionScope.id }">
								<c:if test="${likeChk > 0}">					
<!-- 									<p class="btnlike_done">좋아요</p> -->
									<input type="hidden" name="bnum" value="${db.num }">
									<input type="hidden" name="id" value="${sessionScope.id }">
									<input type="hidden" name="like" value="1">
									<input type="submit" value="좋아요" class="btnlike_done">							
								</c:if>
							</c:if>								

							<c:if test="${! empty sessionScope.id }">
								<c:if test="${likeChk == 0}">																		
									<input type="hidden" name="bnum" value="${db.num }">
									<input type="hidden" name="id" value="${sessionScope.id }">
									<input type="hidden" name="like" value="0">
									<input type="submit" value="좋아요" class="btnlike">
								</c:if>
							</c:if>						
							
							<c:if test="${empty sessionScope.id }">
								<input type="submit" value="좋아요" class="btnlike_login" onclick="return loginCheck()">
							</c:if>
						
						</div>
				
						<div class="balloon">    
   							 <span class="number">${likeCount }</span>
						</div>
				</form><!-- like버튼 -->
				
			
					               
                </div> 
                
            </div>
            
            <br><br>
 			<div class="stop_category board">
 			<div class="tab">
					<ul>
				
						<li data-id="con1" class="on">상세설명</li>
				
						<li data-id="con2" class="">안내 및 참고사항</li>
				
						<li data-id="con3" class="">추천여행지</li>			
					</ul>
                    
            <br><br><br>
            <div id="con1" class="conBox on">
            	<p>${db.content }	</p>
            </div>
            
            
            
            <div id="con2" class="conBox">
            	<p>${db.content2 }	</p>
            </div>
            
     		<div id="con3" class="conBox">
     			<p>${db.content3 }	</p>
     		</div>


    			</div>
    		 </div>
 			
 

 
 	<div class="buttonBox">
		<div class="buttonBoxR">
		
	<c:if test="${! empty sessionScope.id }">
		<c:if test="${sessionScope.id eq 'admin'}">
			<input type="button" value="글수정" class="bu_gray_l" onclick="location.href='<c:url value="/download/update?num=${db.num}"/>'">
			<input type="button" value="글삭제" class="bu_gray_l" onclick="location.href='<c:url value="/download/delete?num=${db.num}"/>'">
		</c:if>
	</c:if>	
		
		<input type="button" value="글목록" class="bu_gray_l" onclick="location.href='download.jsp'">	
		
	<c:if test="${! empty sessionScope.id }">
		<c:if test="${sessionScope.id eq 'admin'}">
			<input type="button" value="글쓰기" class="bu_gray_g" onclick="location.href='<c:url value="/download/write"/>'">
		</c:if>
	</c:if>	
		
		
		</div>
	</div>
 
 
 
 
        </div>
    </section>

<jsp:include page="../inc/footer.jsp"/>

</body>

</html>