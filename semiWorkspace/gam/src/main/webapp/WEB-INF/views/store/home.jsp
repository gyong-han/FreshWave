<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SEMI</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
<link rel="stylesheet" href="/css/store/home.css">
<script defer src="/js/store/home.js"></script>
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        
        <div class="content-wrapper">
            <div></div>
            <div class="home-title">
                <div class="title">
                    <h1>거래처 분석</h1>
                </div>
                <div class="button-area">
                    <button onclick="location.href='/business/insert'" id="home-btn">거래처 등록</button>
                    <button onclick="location.href='/business/list'" id="home-btn">거래처 목록조회</button>
                </div>
            </div>

            <div class="home-title">
                <div class="title">
                    <h1>가맹점 분석</h1>
                </div>
                <div  class="button-area">
                    <button onclick="location.href='/store/insert'" id="home-btn">가맹점 등록</button>
                    <button onclick="location.href='/store/list'" id="home-btn">가맹점 목록조회</button>
                </div>
            </div>

            <div></div>
            <div></div>

            <div class="barChart-area" >
                <canvas id="myBarChart"></canvas>
            </div>

            <div class="pieChart-area">
                <canvas id="myPieChart"></canvas>
            </div>
        
        </div>
    </main>


</body>
</html>