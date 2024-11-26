<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 작성</title>

</head>
<body>

    <form action="/project/write" method="post">
        프로젝트 이름*
        <br>
        <input type="text" name="name" placeholder="프로젝트 이름을 입력해주세요.">
        <br>
        프로젝트 키*
        <br>
        <input type="text" name="key" placeholder="프로젝트 키">
        <br>
        우선순위 설정
        <br>
        <select name="priority">
            <option value="1">HIGH</option>
            <option value="2">MIDDLE</option>
            <option value="3">LOW</option>
        </select>
        <br>
        사용자 초대
        <br>
        <input type="text" name="writerNo" placeholder="사용자">
        <br>
        편집 권한 설정
        <br>
        <select name="accessLevel">
            <option value="1">수정</option>
            <option value="2">읽기</option>
        </select>
        <br>
        프로젝트 권한 설정*
        <br>
        <select name="prjPublic">
            <option value="1">비공개</option>
            <option value="2">부서공개</option>
            <option value="3">전체공개</option>
        </select>
        <br>
        시작날짜
        <input type="date" name="startDate">
        마감날짜
        <input type="date" name="endDate">
        <br>
        <input type="submit" value="작성하기">
    </form>


</body>
</html>