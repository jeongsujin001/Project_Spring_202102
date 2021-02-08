<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>

<jsp:include page="../inc/header.jsp"/>
<script type="text/javascript">
function check() {
	if(document.contactform.name.value==""){
		alert("이름을 입력하세요.");
		document.contactform.name.focus();
		return false;
	}
	if(document.contactform.subject.value==""){
		alert("제목을 입력하세요.");
		document.contactform.subject.focus();
		return false;
	}
	if(document.contactform.content.value==""){
		alert("내용을 입력하세요.");
		document.contactform.content.focus();
		return false;
	}	
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
                        <h4>문의하기</h4>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-6" style="text-align:right;">Home<span class="sep"> / </span><span class="current">문의하기</span></div>
                </div>
            </div>
        </div>

        </div>
    </section>
    <section id="contact-page">
        <div class="container">
            <div class="section-heading text-center">
                <h2>문의사항을 <span>이메일로 보내주세요.</span></h2>
                <p class="subheading">아래의 형식을 이용해서 이메일로 문의사항을 보내주세요.</p>
            </div>
            <div class="row contact-wrap">
                <div class="status alert alert-success" style="display: none"></div>
                <form id="main-contact-form" class="contact-form" name="contactform" method="post" action='<c:url value="/email/send"/>' onsubmit="return check()">
                    <div class="col-sm-5 col-sm-offset-1">

                        <div class="form-group">
                            <label>이름</label>
                            <input type="text" name="senderName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Email</label>
<!--                             <input type="text" name="receiveMail"> -->
                            <input type="email" name="senderMail" class="form-control">
                        </div>
<!--                         <div class="form-group"> -->
<!--                             <label>전화번호</label> -->
<!--                             <input type="number" name="number" class="form-control"> -->
<!--                         </div> -->
                        <!-- <div class="form-group">
                            <label>Company Name</label>
                            <input type="text" class="form-control">
                        </div> -->
                    </div>
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label>제목</label>
                            <input type="text" name="subject" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>내용</label>
                            <textarea name="message" id="message" class="form-control" rows="8"></textarea>
                        </div>
                        <div class="form-group">
<!--                         <input type="button" value="보내기" onclick="check()" class="btn btn-default submit-button"> -->
                        <input type="hidden" name="receiveMail" value="studyforit001@gmail.com">
<!--                         <input type="hidden" name="from" value="studyforit001@gmail.com"> -->
                        <button type="submit" name="submit" class="btn btn-default submit-button">보내기 <i class="fa fa-caret-right"></i></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <section id="contact">
        <div class="overlay">
            <div class="gmap-area">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-5">
                            <div class="gmap">
<!--                                 <iframe frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=JoomShaper,+Dhaka,+Dhaka+Division,+Bangladesh&amp;aq=0&amp;oq=joomshaper&amp;sll=37.0625,-95.677068&amp;sspn=42.766543,80.332031&amp;ie=UTF8&amp;hq=JoomShaper,&amp;hnear=Dhaka,+Dhaka+Division,+Bangladesh&amp;ll=23.73854,90.385504&amp;spn=0.001515,0.002452&amp;t=m&amp;z=14&amp;iwloc=A&amp;cid=1073661719450182870&amp;output=embed"></iframe> -->
									<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3261.928002983845!2d129.05988271524458!3d35.15841628031959!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3568eb7a9ffb035f%3A0x8e030b4b8ef4a2dd!2z7JWE7J207Yuw7JyMIOu2gOyCsOq1kOycoeyEvO2EsA!5e0!3m2!1sko!2skr!4v1601096906331!5m2!1sko!2skr" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
                            </div>
                        </div>
                        <div class="col-sm-7 map-content">
                            <ul class="row">
                                <li class="col-sm-6">
                                    <address>
									<h5>본사</h5>
									<p>K-TIP 부산영업점 <br>
									부산광역시 부산진구 부전동 동천로</p>
										<p>Phone:+82 051 1234 1234 <br>
									Email Address:k-trip@k-trip@com</p>
								</address>

                                </li>
                                <li class="col-sm-6">
                                    <address>
									<h5>지사</h5>
									<p>K-TIP 서울영업점 <br>
									서울특별시 강남구 역삼동</p>
									<p>Phone:+82 02 1234 1234 <br>
									Email Address:k-trip@k-trip@com</p>
								</address>

                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
<jsp:include page="../inc/footer.jsp"/>

</body>

</html>