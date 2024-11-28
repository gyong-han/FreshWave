<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<link rel="stylesheet" href="/css/common/header.css">
<link rel="stylesheet" href="/css/common/font.css">
<link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/static/pretendard.min.css" />

<script defer src="/js/common/header.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FRESH WAVE</title>

</head>
<body>


    <aside>
            <div class="icon">
                <img src="/img/icon/home.svg" alt="home">
                <span class="icon-text">홈</span>
                <div class="bluediv"></div>
            </div>
            <div class="icon">
                <img src="/img/icon/project.svg" alt="project">
                <span class="icon-text">프로젝트 관리</span>
                <div class="bluediv"></div>
            </div>
            <div class="icon">
                <img src="/img/icon/client.svg" alt="client" onclick="location.href='/store/home'">
                <span class="icon-text" onclick="location.href='/store/home'">고객관계 관리</span>
                <div class="bluediv"></div>
            </div>
            <div class="icon">
                <img src="/img/icon/calendar.svg" alt="calendar">
                <span class="icon-text">일정 관리</span>
                <div class="bluediv"></div>
            </div>
            <div class="icon">
                <img src="/img/icon/board.svg" alt="board">
                <span class="icon-text">게시판</span>
                <div class="bluediv"></div>
            </div>
            <div class="icon">
                <img src="/img/icon/callcenter.svg" alt="callcenter">
                <span class="icon-text">고객센터</span>
                <div class="bluediv"></div>
            </div>
    </aside>

    </body>
    </html>
