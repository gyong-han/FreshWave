<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 목록</title>
    <link rel="stylesheet" href="/list.js">
    <script defer src="/js/project/list.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

    <div class="prj-list-area">
        <table border="1">
            <thead >
                <tr>
                    <td>프로젝트 이름</td>
                    <td>부서</td>
                    <td>프로젝트 생성자</td>
                    <td>중요도</td>
                    <td>시작일</td>
                    <td>종료일</td>
                </tr>
                <hr>
            </thead>
            <tbody>

            <c:forEach items="${projectVoList}" var="vo">
                <tr>
                    <td><a href="/project/detail?projectNo=${vo.key}">${vo.name}</a></td>
                    <td>${vo.deptName}</td>
                    <td>${vo.memberName}</td>
                    <td>${vo.priorityName}</td>
                    <td>${vo.startDate}</td>
                    <td>${vo.endDate}</td>
                </tr>
            </c:forEach>
            <c:forEach items="${projectVoList1}" var="vo">
                <tr>
                    <td><a href="/project/detail?projectNo=${vo.key}">${vo.name}</a></td>
                    <td>${vo.deptName}</td>
                    <td>${vo.memberName}</td>
                    <td>${vo.priorityName}</td>
                    <td>${vo.startDate}</td>
                    <td>${vo.endDate}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
       <form action="/project/write">
           <input type="submit" value="프로젝트 생성">
       </form>
    </div>

    <div class="page-area">
        < 1 2 3 4 5 >
    </div>


</body>
</html>