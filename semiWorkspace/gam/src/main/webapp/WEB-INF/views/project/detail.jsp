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
<form action="/project/edit">
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
            <input type="text" value="${projectVo.accessName}">
    </div>

    <div>
        <div>참여인원</div>
        <table>
            <tbody>
                    <tr>
                        <td>${add.profileName}</td>
                        <td>${add.name}</td>
                        <td>${add.deptName}</td>
                        <td>${add.jobName}</td>
                        <td>${add.pmAccess}</td>
                    </tr>
            </tbody>
        </table>
    </div>
</div>
<input type="submit" value="수정">
</form>
<form action="/project/list">
    <input type="submit" value="삭제">
</form>

<form action="/project/list">
    <input type="submit" value="목록">
</form>

</body>
</html>