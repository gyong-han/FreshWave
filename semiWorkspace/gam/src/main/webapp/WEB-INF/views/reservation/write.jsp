<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>
<link rel="stylesheet" href="/css/common/home.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <h1>상담 예약 문의</h1>
            <form action="/reservation/write" method="post">
                <label>문의 제목</label>
                <input type="text" name="title" placeholder="문의 제목을 입력해주세요.">
                <br>
                <label>문의 내용</label>
                <textarea name="content" placeholder="문의 내용을 작성해주시면 보다 정확한 상담이 가능합니다.
                민감한 정보(카드, 계좌번호) 작성에 유의하시기 바랍니다."></textarea>
                <br>
                <label>회사코드</label>
                <input type="text" name="cpCode" value="${loginMemberVo.cpCode}">
                <label>상담받을 전화번호</label>
                <input type="text" name="phone" value="${loginMemberVo.phone}">
                <br>
                <label>문의시간</label>
                <input type="date" name="rDate">
                <input type="time" name="rTime">
                <br>
                <input type="submit" value="작성하기">
            </form>
        </div>
    </main>
</body>
</html>
