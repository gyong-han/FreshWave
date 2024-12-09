<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script defer src="/js/member/list.js"></script>
    <link rel="stylesheet" href="/css/admin/list.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
        <main class="main-container">
            <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
            <div class="content-wrapper">
                <div></div> <!--공란-->
                <div class="search-area">
                    <form action="/member/list?pno=1" onsubmit="return searchForm();">
                        <select name="searchType" onchange="formSearchType(this);">
                            <option value="memberName">이름</option>
                            <option value="dept">부서</option>
                        </select>
                        <input type="text" name="searchValue" placeholder="검색할 제목">
                        <select name="searchValue" disabled>
                            <c:forEach items="${deptVoList}" var="deptVo">
                               <option value="${deptVo.deptCode}">${deptVo.deptName}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="">
                    </form>
                </div>
            <div></div>
            <div></div>
        <div class="list-area">
            <table class="list-table">
                <thead>
                    <tr>
                        <th>사번</th>
                        <th>이름</th>
                        <th>부서</th>
                        <th>직급</th>
                        <th>이메일</th>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
            <div id="insert-area">
                </div>
                <div class="page-area"></div>
            </div>
        </div>
    </main>

</body>
</html>



