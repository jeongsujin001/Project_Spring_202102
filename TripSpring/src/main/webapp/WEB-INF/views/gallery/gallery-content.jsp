<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>

<jsp:include page="../inc/header.jsp"/>

<script type="text/javascript">
function loginCheck() {
		alert("회원전용입니다.");
		return false;
}

function makeTextarea(obj){
    var tmpHtml = obj.innerHTML.replace("<BR>", "\n");
    var html = '<textarea name="content" cols="75" rows="10">' + tmpHtml + '</textarea>';
    obj.innerHTML = html;
}
</script>

<script>
$(document).ready(function(){
	  $(".comment_modify").click(function(){
		  $('.comm_add').removeClass("on");
		  var a = $(this).parents('dl');
		  	  
		  if(a.find('.obj').hasClass("on")){
			  a.find('.obj').removeClass("on");
		  }else{
			  a.find('.obj').addClass("on"); 
		  }
		  	  
	  });
});

$(document).ready(function(){
	  $(".comment_add").click(function(){
		  $('.obj').removeClass("on");
		  var a = $(this).parents('dl');
		  
		  if(a.find('.comm_add').hasClass("on")){
			  a.find('.comm_add').removeClass("on");
		  }else{
			  a.find('.comm_add').addClass("on"); 
		  }
		  
	  });
});

function button_event(){
    if (confirm("정말 삭제하시겠습니까?") == true){    //확인
        document.form.submit();
    }else{   //취소
        return false;
    }
}
</script>
<style type="text/css">
    #obj {
        display: none;
    }
    #obj.on {
        display: block;
    }
    #comm_add {
        display: none;
    }
    #comm_add.on {
        display: block;
    }
    span.del{
    	font-style:italic !important;
    	color:#a0a0a0 !important;
    	margin-bottom:20px !important;
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
                        <h4>후기갤러리</h4>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6" style="text-align:right;">Home<span class="sep"> 	/ </span><span class="current"> 후기갤러리</span></div>
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
				<td>${gb.subject }</td>
				<th>작성일</th>
				<td>${gb.date }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${gb.name }</td>
				<th>조회수</th>
				<td>${gb.readcount }</td>
			</tr>
			<tr>
				<td colspan="4" class="tableTBox">
					<p><img alt="" src='<c:url value="/resources/upload/${gb.file }"/>'></p>
					<br>${gb.content }				
					</td>
			</tr>
		</tbody>
	</table>



<div id="commentArea" class="comment_area">

	<div id="comment_list" class="comment_list_wrap">
		<div class="comment_count">코멘트 <strong>${count }개</strong></div>
	
		<!-- 반복 -->
<c:forEach var="cb" items="${gcommentList }">

		<c:if test="${! empty sessionScope.id }">
			<c:if test="${sessionScope.id eq cb.comment_id}">
				<dl id="add" class="comment_list_in" style="background-color: #fafafa; padding-right:5px;">
			</c:if>
		</c:if>				

		<c:if test="${! empty sessionScope.id }">
			<c:if test="${sessionScope.id != cb.comment_id}">
				<dl id="add" class="comment_list_in" style="padding-right:5px;">
			</c:if>
		</c:if>
		
		<c:if test="${empty sessionScope.id }">
			<dl id="add" class="comment_list_in" style="padding-right:5px;">
		</c:if>
    
    	<c:if test="${cb.comment_del == 0}">
			<dt><span>${cb.comment_id}</span></dt>
		</c:if>
			
			
			<dd>
			<c:if test="${cb.comment_lev > 0}">
				<div class="comment_left_box comment_left_box_p">
			</c:if>
			
			<c:if test="${cb.comment_lev == 0}">	
				<div class="comment_left_box">
 			</c:if>
		
			<c:if test="${cb.comment_del == 0}">
				<c:if test="${cb.comment_lev > 0}">
					<b>[RE]</b>
				</c:if>
			</c:if>
			
			
		<c:if test="${cb.comment_del == 0}">
			${cb.comment_content}			
				<p class="date">[${cb.comment_date}]</p>
				</div>

				<c:if test="${! empty sessionScope.id }">
					<c:if test="${sessionScope.id eq cb.comment_id}">
						<span style="float:right;">				
						<a href='<c:url value="/gallery/commentDelete?comment_num=${cb.comment_num}&comment_bnum=${cb.comment_bnum}"/>' class="bu_gray_s" onclick='return button_event();'>삭제</a>
						</span>
						<span style="float:right;">
						<input type="button" value="수정" class="bu_gray_s comment_modify">
						</span>	
					</c:if>
				</c:if>
	
   				<c:if test="${cb.comment_lev == 0}">
   					<c:if test="${! empty sessionScope.id }">
						<span style="float:right;"><input type="button" value="답댓글" class="bu_gray_s comment_add"></span>
					</c:if>
				</c:if>
   		</c:if>
   		
   		<c:if test="${cb.comment_del != 0}">
			<span class="del">삭제된 댓글입니다</span></div>
		</c:if>
					
					
					 
   			 	<div class="clearfix"></div>
   			     <div id="obj" class="obj">
					<form name="comment" id="comment" action='<c:url value="/gallery/commentUpdate"/>' method="post" style="margin-top:0px !important; padding-top:0px !important;">
					<input type="hidden" name="comment_id" value="${sessionScope.id }">
					<input type="hidden" name="comment_bnum" value="${num }">
					<input type="hidden" name="comment_num" value="${cb.comment_num }">
					<!-- <input type="hidden" name="BD_CD" value="REVIEW"> -->

					<div class="comment_box_wrap">
	
						<div class="comment_area_w">
							<p class="comment_area2"><textarea style="height:70px;" name="comment_content" id="comment_content" hname="내용" required="">${cb.comment_content }</textarea></p>
							<p class="lo_left">
							<input type="submit" value="수정" class="btn_comment">
							<!-- <a href="#" class="btn_comment" onclick="return checkFormC();">확인</a> -->
							</p>
						</div>
					</div>
					<!-- //덧글입력폼 -->
					</form>
				</div><!-- obj -->

   			     <div id="comm_add" class="comm_add">
					<form name="comment" id="comment" action='<c:url value="/gallery/commentReWrite"/>' method="post" style="margin-top:0px !important; padding-top:0px !important;">
					<input type="hidden" name="comment_id" value="${sessionScope.id }">
					<input type="hidden" name="comment_bnum" value="${num }">
					<input type="hidden" name="comment_num" value="${cb.comment_num }">

					<div class="comment_box_wrap">
	
						<div class="comment_area_w">
							<p class="comment_area2"><textarea style="height:70px;" name="comment_content" id="comment_content" hname="내용" required=""></textarea></p>
							<p class="lo_left">
							<input type="submit" value="등록" class="btn_comment">
							<!-- <a href="#" class="btn_comment" onclick="return checkFormC();">확인</a> -->
							</p>
						</div>
					</div>
					<!-- //덧글입력폼 -->
					</form>
				</div><!-- comm_add -->	
			</dd>
    	</dl>        
</c:forEach>
	</div><!-- comment_list -->

	<form name="comment" id="comment" action='<c:url value="/gallery/commentWrite"/>' method="post" style="margin-top:0px !important; padding-top:0px !important;">
		<input type="hidden" name="comment_id" value="${sessionScope.id }">
		<input type="hidden" name="comment_bnum" value="${num }">
			<div class="comment_box_wrap">
				
				<c:if test="${ empty sessionScope.id }">
					<div class="name">로그인을 하셔야 작성이 가능합니다.</div>
 				</c:if>

				<div class="comment_area_w">
					<p class="comment_area"><textarea style="height:70px;" name="comment_content" id="comment_content" hname="내용" required=""></textarea></p>
					<p class="lo_left">
		
					<c:if test="${! empty sessionScope.id }">
						<input type="submit" value="확인" class="btn_comment">
					</c:if>
					
					<c:if test="${ empty sessionScope.id }">
						<input type="submit" value="확인" class="btn_comment" onclick="return loginCheck()">
 					</c:if>		
					</p>
				</div>
			</div>
			<!-- //덧글입력폼 -->
	</form>
</div><!-- commentArea -->



	
	<div class="buttonBox">
		<div class="buttonBoxR">

			<c:if test="${! empty sessionScope.id }">
				<c:if test="${sessionScope.id eq gb.name}">
						<input type="button" value="글수정" class="bu_gray_l" onclick="location.href='<c:url value="/gallery/update?num=${gb.num}"/>'">
						<input type="button" value="글삭제" class="bu_gray_l" onclick="location.href='<c:url value="/gallery/delete?num=${gb.num}"/>'">		
				</c:if>
			</c:if>	
		
		<input type="button" value="글목록" class="bu_gray_l" onclick="location.href='<c:url value="/gallery/list"/>'">	
		
			<c:if test="${! empty sessionScope.id }">
				<input type="button" value="글쓰기" class="bu_gray_g" onclick="location.href='<c:url value="/gallery/write"/>'">
			</c:if>
			<c:if test="${ empty sessionScope.id }">
				<input type="button" value="글쓰기" class="bu_gray_g" onclick="return loginCheck()">
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