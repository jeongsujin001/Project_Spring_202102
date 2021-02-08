<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
//BoardDAO bdao 객체생성
// BoardDAO bdao = new BoardDAO();

//int count = getBoardCount() 메서드 만들기 호출
// int count=bdao.getBoardCount();

//List boardList = getBoardList() 메서드 만들고 호출
// List boardList=bdao.getBoardListFooter();

// List boardList=bdao.getBoardList(1,3);


//int count =  getBoardCount() 메서드 만들기 호출
// CommentDAO cdao = new CommentDAO();

//날짜 => 원하는 모양을 변경 문자열 결과값
// SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");

// String id = (String)session.getAttribute("id");
%>
    <section id="footer">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-12 block">
                    <div class="footer-block">
                        <h4>K-트립 자료실</h4>
                        <hr/>
                        <p>여행에 관한 많은 자료가 있습니다. 다운로드해주세요!</p>
                        <a href="#" class="learnmore">자세히 보기 <i class="fa fa-caret-right"></i></a>
                    </div>
                </div>

                <div class="col-md-3 col-sm-3 col-xs-12 block">
                    <div class="footer-block">
                        <h4>바로가기</h4>
                        <hr/>
                        <ul class="footer-links">
                            <li><a href='<c:url value="/notice/list"/>'>공지사항</a></li>
                            <li><a href='<c:url value="/board/list"/>'>여행이야기</a></li>
                            <li><a href='<c:url value="/gallery/list"/>'>후기갤러리</a></li>
                            <li><a href='<c:url value="/download/list"/>'>자료실</a></li>
<%--                             <li><a href='<c:url value="/contact/contact"/>'>문의하기</a></li> --%>

                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-sm-3 col-xs-12 block">
                    <div class="footer-block">
                        <h4>커뮤니티</h4>
                        <hr/>
                        <ul class="footer-links">
                            <li><a href="#">Blog</a></li>
                            <li><a href="#">페이스북</a></li>
                            <li><a href="#">인스타그램</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-sm-3 col-xs-12 <block></block>">
                    <div class="footer-block">
                        <h4>문의하기</h4>
                        <hr/>
                        <ul class="footer-links">
                           
 <%
// for(int i=0; i<boardList.size(); i++){
// 	// 배열 한칸에서 게시판 글 하나 가져오기
// 	BoardBean bb=(BoardBean)boardList.get(i);
// 	int ccount = cdao.getCommentCount(bb.getNum());
%>                 
                           
<!--                             <li> -->
<%--                                 <a href="../board/board-content.jsp?num=<%//=bb.getNum()%>" class="post"><%//=bb.getSubject()%></a> --%>
<%--                                 <p class="post-date"><%//=sdf.format(bb.getDate())%></p> --%>
<!--                             </li> -->
                       
				
<%
// }
%>
	                          
                           <li><a href='<c:url value="/contact/contact"/>'>문의하기</a></li>
<!--                             <li> -->
<!--                                 <a href="#" class="post">대한민국 추천 명소</a> -->
<!--                                 <p class="post-date">May 25, 2017</p> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a href="#" class="post">제주도 가을 단풍 관광지 다녀왔어요</a> -->
<!--                                 <p class="post-date">May 25, 2017</p> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a href="#"about class="post">서면 소바 맛집</a> -->
<!--                                 <p class="post-date">May 25, 2017</p> -->
<!--                             </li> -->

                        </ul>
                    </div>
                </div>
            </div>
        </div> 


    </section> 

    <section id="bottom-footer">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-sm-6 col-xs-12 btm-footer-links">
                    <a href="#">Privacy Policy</a>
                    <a href="#">Terms of Use</a>
                </div>
                <div class="col-md-6 col-sm-6 col-xs-12 copyright">
                    Developed by <a href="#">Aspire Software Solutions</a> designed by <a href="#">Designing Team</a>
                </div>
            </div>
        </div>
    </section>