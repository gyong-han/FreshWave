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

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <!-- 공지 영역 -->
            <div class="main-notice">
                <div class="title-area">
                    <h2>
                        <img src="/img/icon/carbon_book.svg" alt="icon">
                        공지사항
                    </h2>
                    <div class="action-icons">
                        <a href="/notice/list"><img src="/img/icon/prime_list.svg" alt=""></a>
                        <a href="/notice/write"><img src="/img/icon/mynaui_plus.svg" alt=""></a>
                    </div>
                </div>
                <div class="notice-container">
                    <c:forEach items="${noticeVoList}" var="noticeVo">
                        <div class="notice-row">
                            <strong>${noticeVo.title}</strong><br>
                            부서: ${noticeVo.deptName}<br>
                            조회수: ${noticeVo.hit}<br>
                            등록일: ${noticeVo.enrollDate}<br>
                        </div>
                    </c:forEach>
                </div>
            </div>
            
            <!-- 게시판 영역 -->
            <div class="main-board">
                <div class="title-area">
                    <h2>
                        <img src="/img/icon/carbon_book.svg" alt="icon">
                        게시판
                    </h2>
                    <div class="action-icons">
                        <a href="/board/list"><img src="/img/icon/prime_list.svg" alt=""></a>
                        <a href="/board/write"><img src="/img/icon/mynaui_plus.svg" alt=""></a>
                    </div>
                </div>
                <div class="board-container">
                    <c:forEach items="${boardVoList}" var="boardVo">
                        <div class="board-row">
                            <strong>${boardVo.title}</strong><br>
                            작성자: ${boardVo.nick}<br>
                            등록일: ${boardVo.enrollDate}<br>
                            조회수: ${boardVo.hit}<br>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
