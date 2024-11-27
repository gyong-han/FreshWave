<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FRESH WAVE</title>
</head>
<body>
        <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main>
        <form action="/sch/edit" method="post">
            <input type="text" name="title" placeholder="제목을 입력해주세요.">
            <br>
            <input type="date" name="startDate">
            <input type="time" name="startTime">
            <input type="date" name="finishDate">
            <input type="time" name="finishTime">
            <input type="checkbox" name="allDay"> 하루종일
            <input type="text" name="userAdd">
            <textarea name="content"></textarea>
            <br>
            <input type="submit" value="작성하기">
            <h3>일정세부정보</h3>
            <h1>주소 api</h1>
            <form action="/sch/address" method="post">
                <input type="text" id="sample4_roadAddress" name="roadAddress" placeholder="주소" size="30">
                <input type="button" onclick="sample4_execDaumPostcode()" value="주소 찾기"><br>
                <input type="text" id="sample4_detailAddress" name="detailAddress" placeholder="상세주소"><br>
                <input type="submit" value="검색하기" onclick="combineAddress();">
                
                <input type="hidden" id="address" name="address">
                
                <hidden input type="text" id="sample4_postcode" placeholder="우편번호"><br>
                <hidden input type="text" id="sample4_jibunAddress" placeholder="지번주소">
                <hidden span id="guide" style="color:#999;display:none"></span>
                <hidden input type="text" id="sample4_extraAddress" placeholder="참고항목">
            </form>
        </form>
    </main>
</body>
</html>