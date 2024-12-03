<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 상세조회</title>
</head>
<body>

<h1>${projectVo.name}</h1>
<div class="prj-detail-box">
    <div>
        <div>생성자</div>
            <input type="text" value="${projectVo.memberName}" readonly>
        <div>우선순위</div>
            <input type="text" value="${projectVo.priorityName}" readonly>
        <br>
        <div>
            <div>시작날짜</div>
            <input type="text" value="${projectVo.startDate}" readonly>
            <div>마감날짜</div>
            <input type="text" value="${projectVo.endDate}" readonly>
        </div>
        <div>프로젝트 권한 설정</div>
            <input type="text" value="${projectVo.disclosure}"readonly>
    </div>

    <div>
        <div>참여인원</div>
        <table>
            <tbody>
                <c:forEach items="${add}" var="vo">
                    <tr>
                        <td>${vo.profileName}</td>
                        <td>${vo.name}</td>
                        <td>${vo.deptName}</td>
                        <td>${vo.jobName}</td>
                        <td>${vo.modiAuth}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<button onclick="location.href='/project/edit?projectNo=${projectVo.key}'">수정</button>
<button onclick="location.href='/project/delete?projectNo=${projectVo.key}'">삭제</button>
<button onclick="location.href='/project/list'">목록</button>

</body>
</html>