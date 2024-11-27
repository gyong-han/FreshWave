<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항, 게시판 홈</title>
<link rel="stylesheet" href="/css/board/home.css">
</head>
<body>

    <main>
        <!-- 사이드 영역 -->
        <div class="main-side">사이드</div>
        <!-- 공지 영역 -->
        <div class="main-notice">공지
            <c:forEach items="${noticeVoList}" var="noticeVo">
                <strong>${noticeVo.title}</strong>
                ${noticeVo.deptName}
                ${noticeVo.hit}
                ${noticeVo.enrollDate}
            </c:forEach>
        </div>
        <!-- 게시판 영역 -->
        <div class="main-board">게시판
            <c:forEach items="${boardVoList}" var="boardVo">
                <strong>${boardVo.title}</strong><br>
                ${boardVo.nick}<br>
                ${boardVo.enrollDate}<br>
                ${boardVo.hit}<br>
            </c:forEach>
        </div>
    </main>
</body>
</html>
