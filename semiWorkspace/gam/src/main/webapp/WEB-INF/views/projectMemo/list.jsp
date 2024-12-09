<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 메모 목록조회</title>
    <link rel="stylesheet" href="/css/common/header.css">
    <link rel="stylesheet" href="/css/common/home.css">
    <link rel="stylesheet" href="/css/projectMemo/list.css">
    <script defer src="/js/common/header.js"></script>
    <script defer src="/js/common/home.js"></script>
    <script defer src="/js/projectMemo/list.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <div class="all">
                <div class="button-area">
                <button id="btn1" onclick="location.href='/projectMemo/cardList?projectNo=${key}'">카드조회</button>
                <button id="btn2" onclick="location.href='/projectMemo/write?projectNo=${key}'">메모 생성</button>
                </div>
                <div class="memo">
                    <div class="memo-list-area">
                        <input type="hidden" value="${key}">
                        <table class="list-table">
                            <thead>
                                <tr>
                                    <th>메모 이름</th>
                                    <th>진행상태</th>
                                    <th>메모 생성자</th>
                                    <th>중요도</th>
                                    <th>시작일</th>
                                    <th>종료일</th>
                                </tr>
                            </thead>
                            <tbody>




                            </tbody>
                        </table>
                    </div>  
                </div>
                <div>
                    <div class="page-area">



                    </div>
                </div>
            </div>
        </div>
    </main>


</body>
</html>