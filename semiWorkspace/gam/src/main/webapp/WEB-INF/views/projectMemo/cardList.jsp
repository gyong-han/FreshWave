<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 메모 목록(카드)</title>
<link rel="stylesheet" href="/css/projectMemo/cardList.css">
<link rel="stylesheet" href="/css/common/header.css">
<link rel="stylesheet" href="/css/common/home.css">
<script defer src="/js/common/header.js"></script>
<script defer src="/js/common/home.js"></script>
<script defer src="/js/projectMemo/cardList.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <div class="head">
                <div class="title-area">
                    <h1></h1>
                </div>
                <div class="btn-area">
                    <button id="btn1" onclick="location.href='/projectMemo/list?projectNo=${key}'">목록 조회</button>
                    <button id="btn2" onclick="location.href='/projectMemo/write?projectNo=${key}'">메모 생성</button>
                </div>
            </div>
                <div></div>
                <div class="box-container">
                    <div class ="outer">
                        <h3 class="headName" aling="center">진행 대기</h3>
                        <div id="waitDiv">

                        </div>
                    </div>


                    <div class ="outer">
                        <h3 class="headName" aling="center">진행 중</h3>
                        <div id="ingDiv">

                        </div>
                    </div>


                    <div class="outer">
                        <h3 class="headName" aling="center">진행 완료</h3>
                        <div id="comDiv">

                        </div>
                    </div>
                </div>
            </div>
        </main>
</body>
</html>