<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Business List</title>
            <link rel="stylesheet" href="/css/business/list.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            <script defer src="/js/business/list.js"></script>
        </head>

        <body>
            <%@ include file="/WEB-INF/views/common/header.jsp" %>
                <main class="main-container">
                    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
                        <div class="content-wrapper">
                            <div></div> <!--공란-->
                            <!--검색창-->
                            <div class="search-area">
                                <form action="/business/list?pno=1">

                                    <select name="searchType" id="search-type">
                                        <option value="name" ${searchType=='name' ? 'selected' : '' }>거래처명</option>
                                        <option value="managerName" ${searchType=='managerName' ? 'selected' : '' }>담당자</option>
                                        <option value="ceo" ${searchType=='ceo' ? 'selected' : '' }>대표자</option>
                                    </select>

                                    <input type="text" name="searchValue" value="${searchValue}"
                                        placeholder="검색어를 입력하세요!" id="search-value">

                                    <input type="submit" value=""><br>
                                </form>
                            </div>
                            <div></div><!--공란-->
                            <div></div><!--공란-->
                            <!--List-->
                            <div class="list-area">
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
                            </div>
                            <div></div><!--공란-->
                            <div></div><!--공란-->
                            <div id="insert-area"><button id="insert" onclick="location.href='/business/insert'">거래처 추가</button></div>
                            <div></div><!--공란-->
                            <div></div><!--공란-->
                            <div class="page-area">
                                <c:if test="${pvo.startPage != 1}">
                                    <a
                                        href="/business/list?pno=${pvo.startPage-1}&searchType=${searchType}&searchValue=${searchValue}">&lt;</a>&nbsp&nbsp&nbsp
                                </c:if>

                                <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
                                    <a
                                        href="/business/list?pno=${i}&searchType=${searchType}&searchValue=${searchValue}">${i}</a>&nbsp&nbsp&nbsp
                                </c:forEach>

                                <c:if test="${pvo.endPage != pvo.maxPage}">
                                    <a
                                        href="/business/list?pno=${pvo.endPage+1}&searchType=${searchType}&searchValue=${searchValue}">&gt;</a>
                                </c:if>
                            </div>
                        </div>
                </main>
        </body>

        </html>