<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FRESH WAVE</title>
<link rel="stylesheet" href="/css/reservation/write.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <div id="content-area">
                <h1>상담 예약 문의</h1>
                <br>
                <form class="write-form" action="/reservation/edit?rno=${vo.no}" method="post">
                    <div id="title-area">
                        <label class="re-label">문의 제목</label>
                        <br>
                        <input type="text" name="title" value="${vo.title}">
                    </div>
                    <br>
                    <div id="text-area">
                        <label class="re-label">문의 내용</label>
                        <br>
                        <textarea name="content">${vo.content}</textarea>
                    </div>
                    <br><br>
                    <div id="code-area">
                        <label class="re-label">회사코드</label>
                        <br>
                        <input type="text" name="cpCode" value="${vo.cpCode}">
                    </div>
                    <div id="phone-area">
                        <label class="re-label">상담받을 전화번호</label>
                        <br>
                        <input type="text" name="phone" value="${vo.phone}">
                    </div>
                    <br>
                    <div id="date-area">
                        <label class="re-label">문의시간</label>
                        <br>
                        <input type="date" name="rdate" value="${vo.rdate}">
                        <input type="time" name="rtime" value="${vo.rtime}">
                    </div>
                    <div class="btn-area">
                            <button type="button" id="back" onclick="location.href='/reservation/detail?rno=${vo.no}'">취소</button>
                            <button type="submit" id="enroll">수정</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
</body>
</html>
