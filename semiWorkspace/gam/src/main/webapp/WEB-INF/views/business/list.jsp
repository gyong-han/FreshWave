<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/business/list.css">
    <script defer src="/js/business/list.js"></script>
</head>
<body>
    <h1>가맹점 목록조회</h1>
    <table border ="1">
        <thead>
            <tr>
                <th>거래처명</th>
                <th>대표자명</th>
                <th>대표자 전화번호</th>
                <th>담당자</th>
                <th>담당부서</th>
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
    <button onclick="location.href='/business/insert'">거래처 추가</button>
</body>
</html>