<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FRESH WAVE</title>
<link rel="stylesheet" href="/css/common/font.css">
<link rel="stylesheet" href="/css/reservation/detail.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <div></div>
            <div class="detail-area">
                <div id="detail-main">
                    <h1>${vo.title}</h1>
                    <div id="enroll-area">작성일 : ${vo.enrollDate}</div>
                    <div id="content-area">${vo.content}</div>
                    <div id="date-area">예약일 : ${vo.rdate}</div>
                    <div id="time-area">예약 시간 : ${vo.rtime}</div>
                    <div id="writer-area">에약자 : ${vo.writerName}</div>
                    <div id="phone-area">전화번호 : ${vo.phone}</div>
                </div>
            </div>
            <div class="btn-area">
                <div id="list-btn">
                    <button id="list" onclick="location.href='/reservation/list'">목록</button>
                </div>
                <div id="edit-delete-btn">
                        <c:if test="${loginMemberVo.id == vo.writerNo}">
                            <button id="delete" onclick="location.href=`/reservation/del?rno=${vo.no}`">삭제</button>
                            <button id="edit" onclick="location.href=`/reservation/edit?rno=${vo.no}`">수정</button>
                        </c:if>
                </div>
            </div>
        </div>
        <div></div>
    </main>
</body>
</html>
