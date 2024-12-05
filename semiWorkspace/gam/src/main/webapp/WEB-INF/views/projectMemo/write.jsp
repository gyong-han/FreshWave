<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 메모 작성</title>
<script defer src="/js/projectMemo/write.js"></script>
</head>
<body>
    <form action="/projectMemo/write" method="post">
        <input type="hidden" name="prjKey" value="${key}">
        <input type="text" name="name" placeholder="메모 제목을 입력해주세요.">
        <br>
        <select name="priority" placeholder="HIGH/MIDDLE/LOW">
            <option value="1">HIGH</option>
            <option value="2">MIDDLE</option>
            <option value="3">LOW</option>
        </select>
        <br>
        <select name="ing" placeholder="진행대기">
            <option value="진행대기">잔행대기</option>
            <option value="진행중">진행중</option>
            <option value="진행완료">진행완료</option>
        </select>
        <br>
        시작날짜
        <input type="date" class="start">
        <input type="hidden" name="startDate" id="start">
        마감날짜
        <input type="date" class="end">
        <input type="hidden" name="endDate" id="end">
        <br>
        메모 내용
        <br>
        <textarea name="content" placeholder="메모 내용을 입력해주세요."></textarea>
        <br>
        <input type="submit" value="작성하기" onclick="removeHyphen();">
    </form>
        <button onclick="location.href='/projectMemo/list?projectNo=${key}'">취소</button>

</body>
</html>