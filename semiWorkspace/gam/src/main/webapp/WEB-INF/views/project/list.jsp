<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 목록</title>
<link rel="stylesheet" href="/css/project/list.css">
    <link rel="stylesheet" href="/css/common/header.css">
    <link rel="stylesheet" href="/css/common/home.css">
    <script defer src="/js/common/header.js"></script>
    <script defer src="/js/common/home.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
                <div class="prj-list-area">
                    <table class="list-table">
                        <thead >
                            <tr>
                                <td>프로젝트 이름</td>
                                <td>부서</td>
                                <td>프로젝트 생성자</td>
                                <td>중요도</td>
                                <td>시작일</td>
                                <td>종료일</td>
                            </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${projectVoList}" var="vo">
                            <tr>
                                <td id="fir" ><a href="/projectMemo/list?projectNo=${vo.key}">${vo.name}</a></td>
                                <td>${vo.deptName}</td>
                                <td>${vo.memberName}</td>
                                <td>${vo.priorityName}</td>
                                <td>${vo.startDate}</td>
                                <td id="la" >${vo.endDate}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="form-area">
                    <form action="/project/write">
                        <span>
                        <button type="button" class="btn1"value="카드조회"  onclick="location.href='/project/cardList'">카드조회</button>
                        </span>
                        <span>
                        <input class="button" type="submit" value="프로젝트 생성">
                         </span>
                    </form>
                    </div>
                   <div class="page-area">
                       <c:if test="${pvo.startPage != 1}">
                           <a href="/project/list?pno=${pvo.startPage-1}"> < </a>
                       </c:if>
                       <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i">
                           <a href="/project/list?pno=${i}">${i}</a>
                       </c:forEach>
                       <c:if test="${pvo.endPage != pvo.maxPage}">
                           <a href="/project/list?pno=${pvo.endPage + 1}"> > </a>
                       </c:if>
                   </div>
                </div>
    </main>
</body>
</html>