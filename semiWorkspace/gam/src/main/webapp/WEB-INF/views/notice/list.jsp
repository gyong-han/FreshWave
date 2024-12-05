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
        <div class="area-container">
            <div class="search-area">
                <form action="/notice/list" onsubmit="return submitSearchForm();">
                    <select name="searchType">
                        <option value="title">제목</option>
                        <option value="name">작성자</option>
                    </select>
                    <input type="text" name="searchValue" placeholder="검색할 제목 입력">
                    <button type="submit" style="border: none; background: none; padding: 0;">
                        <img src="/img/icon/search.svg" alt="검색 버튼">
                    </button>
                </form>
            </div>
            <div class="table-area">
                <table>
                    <thead>
                    <tr>
                        <th>말머리</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th id="sortDept" data-column="deptName" data-order="desc">부서 ▼</th>
                        <th>조회수</th>
                        <th>등록일</th>
                        <th>수정일</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${voList}" var="vo"> 
                        <tr class="${vo.urgentYn == 'Y' ? 'urgent-row' : ''}">
                            <td>${vo.urgentStatus}</td>
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
            <div class="page-area">
                <c:if test="${pvo.startPage != 1}">
                    <a href="/notice/list?pno=${pvo.startPage-1}&searchType=${searchType}&searchValue=${searchValue}"><</a>
                </c:if>
                <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
                    <a href="/notice/list?pno=${i}&searchType=${searchType}&searchValue=${searchValue}">${i}</a>
                </c:forEach>
                <c:if test = "${pvo.endPage != pvo.maxPage}">
                    <a href="/notice/list?pno=${pvo.endPage+1}&searchType=${searchType}&searchValue=${searchValue}">></a>
                </c:if>
            </div>
        </div>
    </main>
</body>
</html>
