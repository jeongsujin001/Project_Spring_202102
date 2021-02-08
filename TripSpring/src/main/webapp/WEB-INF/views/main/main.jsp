<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<jsp:include page="../inc/header.jsp"/>

<style type="text/css">
ul{
	list-style: none outside none;
    padding-left: 0;
    margin: 0;
}
.content-slider li img{ 
 	width :100%;
} 
.content-slider h3 {
    margin: 0;
    padding: 70px 0;
    height:200px;
}

.demo p {
	margin-top:10px;
}
		
/* 슬라이드 */		
.content-slider .s-content{
	overflow:hidden; 
	text-overflow:ellipsis;
	white-space:nowrap;
	padding: 10px 0 0 0;
}

.imgbox img {
	width: 100%;
	height: 200px;
	object-fit: cover;
	object-position: 0 100%;
}
.lSAction > a {
    top: 40%;
}
</style>


    <script src='<c:url value="/resources/src/js/lightslider.js"/>'></script> 
    <script>
    	 $(document).ready(function() {
			$("#content-slider").lightSlider({
                loop:true,
                keyPress:true,
                pager:false,
                item:4
            });
            $('#image-gallery').lightSlider({
                gallery:true,
                item:4,
                thumbItem:9,
                slideMargin: 0,
                speed:500,
                auto:true,
                loop:true,
                onSliderLoad: function() {
                    $('#image-gallery').removeClass('cS-hidden');
                }  
            });
		});
    </script>

<jsp:include page="../inc/top_menu.jsp"/>

   
    <!--/.nav-ends -->
    
    <div id="myCarousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>



		<div class="carousel-inner">
            	<div class="item active">
            		<img src='<c:url value="/resources/img/main1.jpg"/>' class="fill">
            	</div>
            	<div class="item">
            		<img src='<c:url value="/resources/img/main1.jpg"/>' class="fill">
            	</div>
            	<div class="item">
            		<img src='<c:url value="/resources/img/main1.jpg"/>' class="fill">
            	</div>
		</div> 


  </div>
 
	<div class="demo">
        <div class="item">
        <h2 style="text-align:center;">여행이야기</h2>
        <h3 style="text-align:center; margin:0px auto 20px auto; font-size:18px; color:#ccc;">Enjoy Your Trip</h3>
         
        
            <ul id="content-slider" class="content-slider">
 
   
<c:forEach var="mgb" items="${mainGalleryList }">
                <li>
                    <div class="imgbox"><a class="" href='<c:url value="/gallery/content?num=${mgb.num}"/>'><img src='<c:url value="/resources/upload/${mgb.file }"/>'></a></div>
                    
                    <p class="s-content"><%//=gb.getSubject()%></p>
                </li>
</c:forEach>               
                
                
            </ul>
        </div>
	</div>

<jsp:include page="../inc/footer.jsp"/>



</html>