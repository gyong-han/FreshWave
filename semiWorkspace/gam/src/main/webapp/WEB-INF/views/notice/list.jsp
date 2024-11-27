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
<!-- <script defer src="/js/notice/list.js"></script> -->
</head>
<body>
    <main>
        <div class="table-area">
            <table>
                <thead>
                    <tr>
                        <th>말머리</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>부서</th>
                        <th>조회수</th>
                        <th>등록일</th>
                        <th>수정일</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${voList}" var="vo"> 
                        <tr>
                            <td>${vo.urgentStatus}</td>
                            <td>${vo.title}</td>
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
                <a href="/notice/list?pno=${pvo.startPage-1}"><</a>
            </c:if>
            <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
                <a href="/notice/list?pno=${i}">${i}</a>
            </c:forEach>
            <c:if test = "${pvo.endPage != pvo.maxPage}">
                <a href="/notice/list?pno=${pvo.endPage+1}">></a>
            </c:if>
        </div>
    </main>
</body>
</html>
