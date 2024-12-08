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
                    <div class="title-icon">
                        <img src="/img/icon/carbon_book.svg" alt="icon" style="background-color: #1D64F2; border-radius: 5px;">
                        <h2>공지사항</h2>
                    </div>
                    <div class="action-icons">
                        <a href="/notice/list"><img src="/img/icon/prime_list.svg" alt=""></a>
                        <c:if test="${jobCode > 2}">
                            <a href="/notice/write"><img src="/img/icon/mynaui_plus.svg" alt=""></a>
                        </c:if>
                    </div>
                </div>
                <div class="notice-container">
                    <c:forEach items="${noticeVoList}" var="noticeVo">
                        <div class="notice-row">
                            <div class="front-title-hit">
                                <strong>${noticeVo.title}</strong><br>
                            </div>
                            <div class="info-row">
                                <div class="left-info">
                                    <img src="/img/icon/mdi-light_eye.svg" alt=""> ${noticeVo.hit}
                                </div>
                                <div class="right-info">
                                    ${noticeVo.enrollDate}
                                    ${noticeVo.deptName}
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            
            <!-- 게시판 영역 -->
            <div class="main-board">
                <div class="title-area">
                    <div class="title-icon">
                        <img src="/img/icon/carbon_book.svg" alt="icon" style="background-color: #BFF205; border-radius: 5px;">
                        <h2>게시판</h2>
                    </div>
                    <div class="action-icons">
                        <a href="/board/list"><img src="/img/icon/prime_list.svg" alt=""></a>
                        <a href="/board/write"><img src="/img/icon/mynaui_plus.svg" alt=""></a>
                    </div>
                </div>
                <div class="board-container">
                    <c:forEach items="${boardVoList}" var="boardVo">
                        <div class="board-row">
                            <div class="front-title-hit">
                                <strong>${boardVo.title}</strong><br>
                            </div>
                            <div class="info-row">
                                <div class="left-info">
                                    <img src="/img/icon/mdi-light_eye.svg" alt=""> ${boardVo.hit}<br>
                                </div>
                                <div class="right-info">
                                    ${boardVo.enrollDate}
                                    ${boardVo.nick}
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
