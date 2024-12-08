<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록조회</title>
<link rel="stylesheet" href="/css/notice/list.css">
<script defer src="/js/notice/list.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <div class="search-area">
                <form action="/notice/list" onsubmit="return submitSearchForm();">
                    <select name="searchType" id="search-type">
                        <option value="title">제목</option>
                        <option value="name">작성자</option>
                    </select>
                    <input type="text" name="searchValue" placeholder="검색어를 입력하세요">
                    <input type="submit" value=""><br>
                </form>
            </div>
            <div class="list-area">
                <table class="list-table">
                    <thead>
                    <tr>
                        <th>말머리</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th id="sortDept" data-column="deptName" data-order="desc">부서</th>
                        <th>조회수</th>
                        <th>등록일</th>
                        <th>수정일</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${voList}" var="vo"> 
                        <tr class="${vo.urgentYn == 'Y' ? 'urgent-row' : ''}">
                            <td><div class="status">${vo.urgentStatus}</div></td>
                            <td><a href="/notice/detail?bno=${vo.no}">${vo.title}</a></td>
                            <td>${vo.name}</td>
                            <td>${vo.deptName}</td>
                            <td>${vo.hit}</td>
                            <td>${vo.enrollDate}</td>
                            <td>${vo.modifyDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            </div>
            <c:if test="${jobCode > 2}">
                <div id="insert-area"><button id="insert" onclick="location.href='/notice/write'">게시글 작성</button></div>
            </c:if>
            <div class="page-area">
                <c:if test="${pvo.startPage != 1}">
                <button><a href="/notice/list?pno=${pvo.startPage-1}&searchType=${searchType}&searchValue=${searchValue}"><</a></button>
                </c:if>
                <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
                <button><a href="/notice/list?pno=${i}&searchType=${searchType}&searchValue=${searchValue}">${i}</a></button>
                </c:forEach>
                <c:if test = "${pvo.endPage != pvo.maxPage}">
                <button><a href="/notice/list?pno=${pvo.endPage+1}&searchType=${searchType}&searchValue=${searchValue}">></a></button>
                </c:if>
            </div>
        </div>
    </main>
</body>
</html>
