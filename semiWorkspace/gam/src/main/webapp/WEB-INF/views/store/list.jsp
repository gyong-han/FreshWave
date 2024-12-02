<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Store List</title>
            <link rel="stylesheet" href="/css/store/list.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            <script defer src="/js/store/list.js"></script>
        </head>

        <body>
            <%@ include file="/WEB-INF/views/common/header.jsp" %>
                <main class="main-container">
                    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
                        <div class="content-wrapper">
                            <div></div> <!--공란-->
                            <div class="search-area">
                                <form action="/store/list?pno=1">
                                    <!-- 검색 카테고리 -->
                                    <select name="searchType"  id="search-type">
                                        <option value="name" ${searchType=='name' ? 'selected' : '' }>가맹점명</option>
                                        <option value="ceo" ${searchType=='ceo' ? 'selected' : '' }>가맹점장</option>
                                    </select>

                                    <!-- 검색 창-->
                                    <input type="text" name="searchValue" value="${searchValue}"
                                        placeholder="검색어를 입력하세요!" id="search-value">

                                    <!-- 검색 버튼-->
                                    <input type="submit" value=""><br>
                                </form>
                            </div>
                            <div id="1"></div><!--공란-->
                            <div id="2"></div><!--공란-->
                            <!--List-->
                            <div class="list-area">
                                <table class="list-table">
                                    <thead>
                                        <tr>
                                            <th>가맹점명</th>
                                            <th>가맹점장</th>
                                            <th>가맹점 전화번호</th>
                                            <th>가맹점 상태</th>
                                            <th>오픈일</th>
                                            <th>계약일</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${storeVoList}" var="vo">
                                            <tr>
                                                <td hidden>${vo.no}</td>
                                                <td>${vo.name}</td>
                                                <td>${vo.ceo}</td>
                                                <td>${vo.phone}</td>
                                                <td>${vo.status}</td>
                                                <td>${vo.openDate}</td>
                                                <td>${vo.startDate} ~ ${vo.endDate}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div></div><!--공란-->
                            <div></div><!--공란-->
                            <div id="insert-area"><button id="insert" onclick="location.href='/store/insert'">가맹점
                                    추가</button></div>
                            <div></div><!--공란-->
                            <div></div><!--공란-->
                            <div class="page-area">
                                <c:if test="${pvo.startPage != 1}">
                                    <a
                                        href="/store/list?pno=${pvo.startPage-1}&searchType=${searchType}&searchValue=${searchValue}">&lt;</a>&nbsp&nbsp&nbsp
                                </c:if>

                                <c:forEach begin="${pvo.startPage}" end="${pvo.endPage}" var="i" step="1">
                                    <a
                                        href="/store/list?pno=${i}&searchType=${searchType}&searchValue=${searchValue}">${i}</a>&nbsp&nbsp&nbsp
                                </c:forEach>

                                <c:if test="${pvo.endPage != pvo.maxPage}">
                                    <a
                                        href="/store/list?pno=${pvo.endPage+1}&searchType=${searchType}&searchValue=${searchValue}">&gt;</a>
                                </c:if>

                            </div>

                        </div>
                </main>

        </body>

        </html>