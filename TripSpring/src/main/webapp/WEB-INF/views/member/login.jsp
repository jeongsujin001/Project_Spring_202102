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
    </section>



    <section id="login-reg">
        <div class="container">
            <!-- Top content -->
            <div class="row">
                <div class="col-md-6-1 col-sm-12 forms-right-icons">
                    <div class="section-heading">
                        <h2>K-트립으로 <span>로그인해주세요</span></h2>
                        <p class="subheading">로그인해서 많은 정보와 혜택을 누리세요.</p>
                    </div>

                </div>
                <div class="col-md-6-1 col-sm-12">

                    <div class="form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                                <h3>로그인</h3>
                                <p>아이디와 비밀번호를 입력해주세요.</p>
                            </div>
                            <div class="form-top-right">
                                <i class="fa fa-key"></i>
                            </div>
                        </div>
                        <div class="form-bottom">
                            <form role="form" action='<c:url value="/member/login"/>' class="login-form" method="post">
                                <label>아이디</label>
                                <input type="text" name="id" class="form-control" placeholder="이름" aria-describedby="basic-addon1" style="width:100%; margin-bottom:10px; padding-left:10px;">
                                <label>비밀번호</label>
                                <input type="password" name="pass" class="form-control" placeholder="비밀번호" aria-describedby="basic-addon1" style="width:100%; margin-bottom:10px; padding-left:10px;">
                                <button type="submit" class="btn">로그인</button>
                            </form>
                        </div>
                    </div>

                </div>

    </section>

<jsp:include page="../inc/footer.jsp"/>


</body>

</html>