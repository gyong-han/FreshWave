<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 홈</title>
<link rel="stylesheet" href="/css/admin/home.css">
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
                        <img src="/img/icon/accountlist.svg" alt="icon" id="iconMember" width="50px" height="50px">
                        <h2>사원목록</h2>
                    </div>
                    <div class="action-icons">
                        <a href="/admin/list"><img src="/img/icon/prime_list.svg" alt=""></a>
                        <a href="/member/join"><img src="/img/icon/mynaui_plus.svg" alt=""></a>
                    </div>
                </div>
                <div class="notice-container">
                    <c:forEach items="${employeeVoList}" var="employeeVo">
                        <div class="notice-row">
                            <div class="front-title-hit">
                                <strong>${employeeVo.name}</strong><br>
                            </div>
                            <div class="back-date-dept">
                                ${employeeVo.deptName}
                                ${employeeVo.jobName}
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            
            <!-- 게시판 영역 -->
            <div class="main-board">
                <div class="title-area">
                    <div class="title-icon">
                        <img src="/img/icon/carbon_book.svg" alt="icon" id="iconBoard">
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
                                <img src="/img/icon/mdi-light_eye.svg" alt=""> ${boardVo.hit}<br>
                            </div>
                            <div class="back-date-dept">
                                ${boardVo.enrollDate}
                                ${boardVo.nick}
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
