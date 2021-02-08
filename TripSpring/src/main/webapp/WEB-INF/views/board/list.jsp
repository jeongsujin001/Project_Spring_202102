<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>WebContent/board/list.jsp</h1>
<h1>전체 글개수 [${pbBean.count }]</h1>
<table border="1">
<tr><td>번호</td><td>제목</td><td>작성자</td><td>날짜</td><td>조회수</td></tr>

<c:forEach var="bb" items="${boardList }">
<tr><td>${bb.num }</td>
<td>
<!-- // 답글 이면 이미지 보이기 -->
<!-- // 들여쓰기 1 => 흰색이미지 너비 10px , 들여쓰기2 => 흰색이미지 너비 20px -->
<c:if test="${bb.re_lev >0}">
	<img src='<c:url value="/resources/boardimg/level.gif"/>' width="${bb.re_lev*10 }" height="15">
	<img src='<c:url value="/resources/boardimg/re.gif"/>'>
</c:if>
<a href='<c:url value="/board/content?num=${bb.num }"/>'>${bb.subject }</a>
</td>
    <td>${bb.name }</td>
    <td>${bb.date }
    </td><td>${bb.readcount }</td></tr>
</c:forEach>


</table>
<c:if test="${pbBean.startPage > pbBean.pageBlock}">
	<a href='<c:url value="/board/list?pageNum=${pbBean.startPage - pbBean.pageBlock}"/>'>[이전]</a>
</c:if>
<c:forEach var="i" begin="${pbBean.startPage }" end="${pbBean.endPage }" step="1">
	<a href='<c:url value="/board/list?pageNum=${i}"/>'>${i}</a>
</c:forEach>
<c:if test="${pbBean.endPage < pbBean.pageCount}">
	<a href='<c:url value="/board/list?pageNum=${pbBean.startPage + pbBean.pageBlock}"/>'>[다음]</a>
</c:if>
<!--         1 2 3 4 5 ... 10 [다음] -->
<!-- [이전]  11 12 13  ... 20  -->
</body>
</html>





