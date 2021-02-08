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
                        <h2>K-트립회원 <span>탈퇴하기</span></h2>
                        <p class="subheading">K-트립을 이용해주셔서 감사합니다.</p>
                    </div>

                </div>
                <div class="col-md-6-1 col-sm-12">

                    <div class="form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                                <h3>탈퇴하기</h3>
                                <p>아이디와 비밀번호를 입력해주세요.</p>
                            </div>
                            <div class="form-top-right">
                                <i class="fa fa-key"></i>
                            </div>
                        </div>
                        <div class="form-bottom">
                            <form role="form" action='<c:url value="/member/delete"/>' class="login-form" method="post">
                                <label>아이디</label>
                                <input type="text" name="id" class="form-control" placeholder="이름" aria-describedby="basic-addon1" style="width:100%; margin-bottom:10px; padding-left:10px;">
                                <label>비밀번호</label>
                                <input type="password" name="pass" class="form-control" placeholder="비밀번호" aria-describedby="basic-addon1" style="width:100%; margin-bottom:10px; padding-left:10px;">
                                <button type="submit" class="btn">탈퇴하기</button>
                            </form>
                        </div>
                    </div>

                </div>

    </section>

<jsp:include page="../inc/footer.jsp"/>


</body>

</html>