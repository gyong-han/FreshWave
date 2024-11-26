<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SEMI</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<link rel="stylesheet" href="/css/store/home.css">
<script defer src="/js/store/home.js"></script>
</head>
<body>
   <h1>Business && Store HOME ~ !</h1>

    <div style="width: 350px; margin: 0 auto;">
        <h2>수직 막대 차트 예제</h2>
        <canvas id="myBarChart"></canvas>
    </div>

   <button onclick="location.href='/business/insert'">거래처 등록</button>
   <button onclick="location.href='/business/list'">거래처 목록조회</button><br><br>
   <button onclick="location.href='/store/insert'">가맹점 등록</button>
   <button onclick="location.href='/store/list'">가맹점 목록조회</button>


</body>
</html>