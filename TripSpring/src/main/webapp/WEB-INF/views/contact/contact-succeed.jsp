<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                <h2>메일이 <span>${message}</span></h2>
                <p class="subheading">문의사항은 영업시간내 빠른시간내에 답변드리겠습니다</p>
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
                                <iframe frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=JoomShaper,+Dhaka,+Dhaka+Division,+Bangladesh&amp;aq=0&amp;oq=joomshaper&amp;sll=37.0625,-95.677068&amp;sspn=42.766543,80.332031&amp;ie=UTF8&amp;hq=JoomShaper,&amp;hnear=Dhaka,+Dhaka+Division,+Bangladesh&amp;ll=23.73854,90.385504&amp;spn=0.001515,0.002452&amp;t=m&amp;z=14&amp;iwloc=A&amp;cid=1073661719450182870&amp;output=embed"></iframe>
                            </div>
                        </div>
                        <div class="col-sm-7 map-content">
                            <ul class="row">
                                <li class="col-sm-6">
                                    <address>
									<h5>Head Office</h5>
									<p>Aspire Software Solutions <br>
									202, Parishram Complex,<br>
									Mithakhali Six Roads,<br>
									Navrangpura, Ahmedabad,<br>
									Gujarat, India. </p>
										<p>Phone:+91 848 594 5080 <br>
									Email Address:sales@aspiresoftware.in</p>
								</address>

                                </li>
                                <li class="col-sm-6">
                                    <address>
									<h5>Zone#2 Office</h5>
									<p>Aspire Software Solutions <br>
									202, Parishram Complex,<br>
									Mithakhali Six Roads,<br>
									Navrangpura, Ahmedabad,<br>
									Gujarat, India. </p>
									<p>Phone:+91 848 594 5080 <br>
									Email Address:sales@aspiresoftware.in</p>
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