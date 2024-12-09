<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FRESH WAVE</title>
<link rel="stylesheet" href="/css/reservation/list.css">
<script defer src="/js/reservation/list.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <div></div>
            <!-- 검색 -->
            <div class="search-area">
                <form action="/reservation/list" onsubmit="return submitSearchForm();">
                    <select name="searchType">
                        <option value="title">제목</option>
                        <option value="writerName">작성자</option>
                    </select>
                    <input type="text" name="searchValue" placeholder="검색어를 입력해주세요.">
                    <input type="submit" value="">
                </form>
            </div>
            <div></div>
            <div></div>
            <div class="list-area">
                <table class="list-table">
                    <thead>
                        <tr>
                            <th>게시글 번호</th>
                            <th>문의 제목</th>
                            <th>예약 날짜</th>
                            <th>예약자</th>
                            <th>등록일</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <div id="insert-area">
                    <button id="write-btn" onclick="location.href='/reservation/write'">작성하기</button>
                </div>
                <div class="page-area"></div>
            </div>
            </div>
    </main>
</body>
</html>
