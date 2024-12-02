<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상세조회</title>
    <link rel="stylesheet" href="/css/notice/detail.css">
</head>
<body>
    <!-- Header 포함 -->
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    
    <main class="main-container">
        <!-- Sidebar 포함 -->
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        
        <!-- 상세 내용 -->
        <div class="content-container">
            <div class="detail-section">
                <h3>제목</h3>
                <p>${vo.title}</p>
            </div>
            
            <div class="detail-section">
                <h3>조회수</h3>
                <p>${vo.hit}</p>
            </div>
            
            <div class="detail-section">
                <p>${vo.name} (${vo.deptName})</p>
            </div>
            
            <div class="detail-section">
                <h3>유형</h3>
                <p>${vo.urgentYn == 'Y' ? '긴급' : '일반'}</p>
            </div>
            
            <div class="detail-section">
                <h3>내용</h3>
                <p>${vo.content}</p>
            </div>
            
            
            <div class="button-container">
                <button onclick="history.back()">목록</button>
            </div>
        </div>
    </main>
</body>
</html>
