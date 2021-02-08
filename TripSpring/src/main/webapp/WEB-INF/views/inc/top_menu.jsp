<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
String id = (String)session.getAttribute("id");
if(id==null){
	response.sendRedirect("../login.jsp");
}
%>
<section id="top-header">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-sm-6 col-xs-12 top-header-links">
                    <ul class="contact_links">
                        <li><i class="fa fa-phone"></i><a href="#">+82-51-1234-1234</a></li>
                        <li><i class="fa fa-envelope"></i><a href="#">cs@k-trip.com</a></li>
                    </ul>
                </div>
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <ul class="social_links">
						    <%
							if(id!=null){
							%><li style="font-size:14px;"><%=id%>님 | </li><%
							}else{
							%><li style="font-size:12px;"></li><%
							}
							%>
						    <%
							if(id!=null){
							%><li><a href='<c:url value="/member/logout"/>'>로그아웃</a> | </li><%
							}else{
							%><li><a href='<c:url value="/member/login"/>'>로그인</a> | </li><%
							}
							%>
						   <%
							if(id!=null){
							%><li><a href='<c:url value="/member/update"/>'>회원정보수정</a></li><%
							}else{
							%><li><a href='<c:url value="/member/registration"/>'>회원가입</a></li><%
							}
							%>  
                    </ul>
                </div>
            </div>
        </div>
        </div>

    </section>

    <header>
        <nav class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			  </button>
                    <a class="navbar-brand" href="#">
                        <h1>K-TRIP</h1><span>Enjoy your trip</span></a>
                </div>
                <div id="navbar" class="collapse navbar-collapse navbar-right">
                    <ul class="nav navbar-nav">
                        <li><a href='<c:url value="/main/main"/>'>Home</a></li>
                            <li><a href='<c:url value="/notice/list"/>'>공지사항</a></li>
                            <li><a href='<c:url value="/board/list"/>'>여행이야기</a></li>
                            <li><a href='<c:url value="/gallery/list"/>'>후기갤러리</a></li>
                            <li><a href='<c:url value="/download/list"/>'>자료실</a></li>
                            <li><a href='<c:url value="/contact/contact"/>'>문의하기</a></li>   
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
        </nav>
        <!--/.nav-ends -->
    </header>