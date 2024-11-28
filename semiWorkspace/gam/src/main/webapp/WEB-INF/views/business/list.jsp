<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/business/list.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script defer src="/js/business/list.js"></script>
</head>
<body>
    <h1><a href="/business/list">거래처 목록조회</a></h1>

    <div class="search-area">
        <form action="/business/list?pno=1">
            <!-- 검색 카테고리 -->
            <select name="searchType">
                <option value="name" ${searchType == 'name' ? 'selected' : ''}>거래처명</option>
                <option value="managerName" ${searchType == 'managerName' ? 'selected' : ''}>담당자</option>
                <option value="ceo" ${searchType == 'ceo' ? 'selected' : ''}>대표자</option>
            </select>
    
            <!-- 검색 창-->
            <input type="text" name="searchValue" value="${searchValue}" placeholder="검색어를 입력하세요!">
    
            <!-- 검색 버튼-->
            <input type="submit" value=""><br>
        </form>
    </div><br>

    <table class="list-table">
        <thead>
            <tr>
                <th>거래처명</th>
                <th>대표자명</th>
                <th>대표자 전화번호</th>
                <th>담당자</th>
                <th>담당부서</th>
                <th>계약일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${businessVoList}" var="vo">
                <tr>
                    <td hidden>${vo.no}</td>
                    <td>${vo.name}</td>
                    <td>${vo.ceo}</td>
                    <td>${vo.phone}</td>
                    <td>${vo.managerName}</td>
                    <td>${vo.deptName}</td>
                    <td>${vo.startDate} ~ ${vo.endDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button id="insert" onclick="location.href='/business/insert'">거래처 추가</button>

    <div class="page-area">
        <c:if test="${pvo.startPage != 1}" >
            <a href="/business/list?pno=${pvo.startPage-1}&searchType=${searchType}&searchValue=${searchValue}">⬅️</a>
        </c:if>

        <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
            <a href="/business/list?pno=${i}&searchType=${searchType}&searchValue=${searchValue}">${i}</a>
        </c:forEach>

        <c:if test="${pvo.endPage != pvo.maxPage}">
            <a href="/business/list?pno=${pvo.endPage+1}&searchType=${searchType}&searchValue=${searchValue}">➡️</a>
        </c:if>

    </div>
</body>
</html>