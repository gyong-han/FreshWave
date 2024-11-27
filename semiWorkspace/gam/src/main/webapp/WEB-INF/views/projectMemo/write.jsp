<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 메모 작성</title>

</head>
<body>

    <form action="/projectMemo/write" method="post">
        <input type="text" name="name" placeholder="메모 제목을 입력해주세요.">
        <br>
        <input type="text" name="choice" placeholder="거래처 or 가맹점 검색">
        <br>
        <select name="priority" aria-placeholder="HIGH/MIDDLE/LOW">
            <option value="1">HIGH</option>
            <option value="2">MIDDLE</option>
            <option value="3">LOW</option>
        </select>
        <br>
        <select name="status" aria-placeholder="진행대기">
            <option value="1">잔행대기</option>
            <option value="2">진행중</option>
            <option value="3">진행완료</option>
        </select>
        <br>
        시작 날짜
        <br>
        <input type="date" name="startDate">
        <br>
        마감 날짜
        <br>
        <input type="date" name="endDate">
        <br>
        메모 내용
        <br>
        <textarea name="content" placeholder="메모 내용을 입력해주세요."></textarea>
        <br>
        <input type="submit" value="저장">
    </form>
        <button>첨부파일</button>
        <form action="/projectMemo/cardList">
             <button>취소</button>
        </form>

</body>
</html>