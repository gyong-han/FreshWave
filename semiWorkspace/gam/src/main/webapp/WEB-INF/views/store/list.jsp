<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/store/list.css">
    <script defer src="/js/store/list.js"></script>
</head>
<body>
    <h1>가맹점 목록조회</h1>
    <table border ="1">
        <thead>
            <tr>
                <th>가맹점명</th>
                <th>대표자명</th>
                <th>가맹점 전화번호</th>
                <th>가맹점 상태</th>
                <th>오픈일</th>
                <th>계약일</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>신논현 </td>
                <td>홍길동 </td>
                <td>02123456 </td>
                <td>휴업중 </td>
                <td>24.11.11 </td>
                <td>24.11.11 ~ 25.11.11 </td>
            </tr>
        </tbody>
    </table>
    <button onclick="location.href='/store/insert'">가맹점 추가</button>
</body>
</html>