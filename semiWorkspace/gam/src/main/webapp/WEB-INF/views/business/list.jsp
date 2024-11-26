<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/business/list.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script defer src="/js/business/list.js"></script>
</head>
<body>
    <h1>거래처 목록조회</h1>

    <table class="list-table">
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
           
        </tbody>
    </table>
    <button onclick="location.href='/business/insert'">거래처 추가</button>
</body>
</html>