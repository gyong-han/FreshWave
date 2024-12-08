<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록조회</title>
<link rel="stylesheet" href="/css/board/list.css">
<script defer src="/js/board/list.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <div class="search-area">
                <form action="/board/list" onsubmit="return submitSearchForm();">
                    <select name="searchType" id="search-type">
                        <option value="title">제목</option>
                        <option value="nick">작성자</option>
                    </select>
                    <input type="text" name="searchValue" placeholder="검색어를 입력하세요" value="${searchValue}" id="search-value">
                    <input type="submit" value=""><br>
                </form>
            </div>
            <!--List-->
            <div class="list-area">
                <table class="list-table">
                    <thead>
                        <tr>
                            <th>글번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>등록일</th>
                            <th>조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="main-bottom-area">
                <button onclick="location.href='/board/write'">게시글 작성</button>
            </div>
            <div class="page-area"></div>
        </div>
    </main>
</body>
</html>
