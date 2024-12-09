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
                <form class="write-form" action="/reservation/write" method="post">
                    <div id="title-area">
                        <label class="re-label">문의 제목</label>
                        <br>
                        <input type="text" name="title" placeholder="문의 제목을 입력해주세요.">
                    </div>
                    <br>
                    <div id="text-area">
                        <label class="re-label">문의 내용</label>
                        <br>
                        <textarea name="content" placeholder="문의 내용을 작성해주시면 보다 정확한 상담이 가능합니다.
                        민감한 정보(카드, 계좌번호) 작성에 유의하시기 바랍니다."></textarea>
                    </div>
                    <br><br>
                    <div id="code-area">
                        <label class="re-label">회사코드</label>
                        <br>
                        <input type="text" name="cpCode" value="${loginMemberVo.cpCode}">
                    </div>
                    <div id="phone-area">
                        <label class="re-label">상담받을 전화번호</label>
                        <br>
                        <input type="text" name="phone" value="${loginMemberVo.phone}">
                    </div>
                    <br>
                    <div id="date-area">
                        <label class="re-label">문의시간</label>
                        <br>
                        <input type="date" name="rdate">
                        <input type="time" name="rtime">
                    </div>
                    <div class="btn-area">
                        <button type="button" id="back" onclick="location.href='/reservation/list?rno=1'">취소</button>
                        <button type="submit" id="enroll">작성</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
</body>
</html>
