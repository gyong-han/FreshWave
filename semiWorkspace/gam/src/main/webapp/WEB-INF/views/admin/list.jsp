<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script defer src="/js/admin/list.js"></script>
</head>
<body>
    <main>
        <div class="search-area">
            <form action="/admin/list?pno=1" onsubmit="return searchForm();">
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
                <input type="submit" value="검색">
            </form>
        </div>
        <br>
        <div class="list-member">
            <table>
                <thead>
                    <th>사번</th>
                    <th>이름</th>
                    <th>부서</th>
                    <th>직급</th>
                    <th>이메일</th>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
        <div class="page-area">

        </div>
    </main>
</body>
</html>

