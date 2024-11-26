<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 수정</title>

</head>
<body>

<h1>역삼 1호점 프로젝트</h1>
<form action="/project/edit" method="post">
    <div class="prj-detail-box">
        <div>
            <div>생성자</div>
                <input type="text" value="이감자" readonly>
            <div>우선순위</div>
                <select name="priority">
                    <option value="1">HIGH</option>
                    <option value="2">MIDDLE</option>
                    <option value="3">LOW</option>
                </select>
            <br>
            <div>
                <div>시작날짜</div>
                <input type="date" name="startDate">
                <div>마감날짜</div>
                <input type="date" name="endDate">
            </div>
            <div>프로젝트 권한 설정</div>
                <select name="prjPublic">
                    <option value="1">비공개</option>
                    <option value="2">부서공개</option>
                    <option value="3">전체공개</option>
                </select>
            </div>
            <div>사용자초대</div>
                <input type="text" value="홍길동">
        <div>
            <div>참여인원</div>
            <table>
                <tbody>
                    <tr>
                        <td>프로필사진  </td>
                        <td>김두철</td>
                        <td>개발부</td>
                        <td>(부장)</td>
                        <td>
                            <select name="prjPermission">
                                <option value="1">수정권한 </option>
                                <option value="2">읽기권한 </option>
                            </select>
                        </td>
                        <td> x </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
         <input type="submit" value="저장">
</form>
<form action="/project/detail">
    <input type="submit" value="취소">
</form>

</body>
</html>