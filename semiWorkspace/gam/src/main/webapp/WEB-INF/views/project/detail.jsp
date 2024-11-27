<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 상세조회</title>
</head>
<body>

<h1>역삼 1호점 프로젝트</h1>
<form action="/project/edit">
<div class="prj-detail-box">
    <div>
        <div>생성자</div>
            <input type="text" value="이감자" readonly>
        <div>우선순위</div>
            <input type="text" value="MIDDLE" readonly>
        <br>
        <div>
            <div>시작날짜</div>
            <input type="text" value="24.11.30" readonly>
            <div>마감날짜</div>
            <input type="text" value="24.12.30" readonly>
        </div>
        <div>프로젝트 권한 설정</div>
            <input type="text" value="비공개">
    </div>

    <div>
        <div>참여인원</div>
        <table>
            <tbody>
                <tr>
                    <td>프로필사진</td>
                    <td>김두철</td>
                    <td>개발부</td>
                    <td>(부장)</td>
                    <td>수정권한</td>
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