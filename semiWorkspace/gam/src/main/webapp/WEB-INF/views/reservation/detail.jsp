<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FRESH WAVE</title>
<link rel="stylesheet" href="/css/reservation/detail.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <h1>${vo.title}</h1>
            <div>${vo.content}</div>
            <div>${vo.enrollDate}</div>
            <div>${vo.rdate}</div>
            <div>${vo.rtime}</div>
            <div>${vo.writerName}</div>
            <div>${vo.phone}</div>
        </div>
        <c:if test="${loginMemberVo.id == vo.writerNo}">
            <div class="main-footer">
                <button onclick="location.href=`/reservation/edit?rno=${vo.no}`">수정</button>
                <button onclick="location.href=`/reservation/del?rno=${vo.no}`">삭제</button>
            </div>
        </c:if>
    </main>
</body>
</html>
