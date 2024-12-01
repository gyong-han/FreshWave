<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상세조회</title>
    <link rel="stylesheet" href="/css/board/detail.css">
</head>
<body>
    <!-- Header -->
    <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <main class="main-container">
        <!-- Sidebar -->
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

        <!-- Content -->
        <div class="content-container">
            <div class="detail-header">
                <div class="title-section">
                    <h3>제목</h3>
                    <p>${vo.title}</p>
                </div>
                <div class="info-section">
                    <p>${vo.nick} | 조회수 ${vo.hit}</p>
                </div>
            </div>

            <div class="content-section">
                <h3>내용</h3>
                <p>${vo.content}</p>
                
            </div>

            <div class="file-section">
                <h3>첨부파일</h3>
                ${vo.originName}
            </div>

            <div class="comment-section">
                <h3>댓글</h3>
                <textarea placeholder="댓글을 입력하세요..."></textarea>
                <button type="button">등록</button>
            </div>
            <div class="button-container">
                <button onclick="history.back()">목록</button>
            </div>
        </div>
        
    </main>
</body>
</html>
