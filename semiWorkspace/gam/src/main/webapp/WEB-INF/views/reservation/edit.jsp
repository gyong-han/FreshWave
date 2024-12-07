<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FRESH WAVE</title>
<link rel="stylesheet" href="/css/reservation/edit.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <h1>상담 예약 문의</h1>
            <form action="/reservation/edit" method="post">
                <input type="hidden" name="no" value="${vo.no}">
                <label>문의 제목</label>
                <input type="text" name="title" value="${vo.title}">
                <br>
                <label>문의 내용</label>
                <textarea name="content">${vo.content}</textarea>
                <br>
                <label>회사코드</label>
                <input type="text" name="cpCode" value="${loginMemberVo.cpCode}">
                <label>상담받을 전화번호</label>
                <input type="text" name="phone" value="${loginMemberVo.phone}">
                <br>
                <label>문의시간</label>
                <input type="date" name="rdate" value=${vo.rdate}>
                <input type="time" name="rtime" value=${vo.rtime}>
                <br>
                <input type="submit" value="수정하기">
            </form>
        </div>
    </main>
</body>
</html>
