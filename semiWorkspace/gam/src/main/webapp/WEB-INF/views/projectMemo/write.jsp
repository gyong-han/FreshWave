<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 메모 작성</title>
<link rel="stylesheet" href="/css/common/header.css">
<link rel="stylesheet" href="/css/common/home.css">
<link rel="stylesheet" href="/css/projectMemo/write.css">
<script defer src="/js/common/header.js"></script>
<script defer src="/js/common/home.js"></script>
<script defer src="/js/projectMemo/write.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <form action="/projectMemo/write" method="post">
                <input type="hidden" name="prjKey" value="${key}">
                <div class="all">
                    <div>
                        <label>메모 제목</label>
                        <input class="title" type="text" name="name" placeholder="메모 제목을 입력해주세요.">
                        <br>
                    </div>
                    <div></div>
                    <div>
                        <label>우선순위</label>
                        <select class="title" name="priority" placeholder="HIGH/MIDDLE/LOW">
                            <option value="1">HIGH</option>
                            <option value="2">MIDDLE</option>
                            <option value="3">LOW</option>
                        </select>
                    </div>
                    <div>
                        <label>진행상태</label>
                        <select class="title" name="ing" placeholder="진행대기">
                            <option value="진행대기">진행대기</option>
                            <option value="진행중">진행중</option>
                            <option value="진행완료">진행완료</option>
                        </select>
                    </div>
                    <div>
                        <label>시작날짜</label>
                        <input type="date" class="start">
                        <input type="hidden" name="startDate" id="start">
                    </div>
                    <div>
                        <label>마감날짜</label>
                        <input type="date" class="end">
                        <input type="hidden" name="endDate" id="end">
                        <br>
                    </div>
                    <div class="span">
                        <label>메모내용</label>
                        <textarea name="content" placeholder="메모 내용을 입력해주세요."></textarea>
                        <br>
                    </div>
                </div>
                <div class="button-area">
                <button class="btn1" type="button" onclick="location.href='/projectMemo/list?projectNo=${key}'">취소</button>
                <input class="btn2" type="submit" value="저장" onclick="removeHyphen();">
                </div>
            </div>
 
        </form>
    </main>
</body>
</html>