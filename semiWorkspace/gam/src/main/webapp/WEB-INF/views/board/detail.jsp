<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상세조회</title>
    <link rel="stylesheet" href="/css/board/detail.css">
    <script defer src="/js/board/detail.js"></script>
</head>
<body>
    <!-- Header -->
    <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <main class="main-container">
        <!-- Sidebar -->
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

        <!-- Content -->
        <div class="content-container">
            <div class="detail-container">
                <div class="detail-col">
                    <div class="detail-row">
                        <div class="detail-label">제목</div>
                        <div class="detail-value">${vo.title}</div>
                    </div>
                    <div class="detail-row">
                        <div class="detail-label">조회수</div>
                        <div class="detail-value">${vo.hit}</div>
                    </div>
                </div>
                <div class="detail-col">
                    <div class="detail-row">
                        <div class="detail-label">작성인</div>
                        <div class="detail-value">${vo.nick}<br>${vo.enrollDate}</div>
                    </div>
                    <div class="detail-row">
                        <div class="detail-label">첨부파일</div>
                        <div class="detail-value">
                            <c:forEach items="${attachmentVoList}" var="attachVo">
                                <img src="/img/board/attachment/${attachVo.changeName}" alt="${attachVo.originName}" width="80px" , height="80px">
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="detail-row content-block">
                    <div class="detail-label">내용</div>
                    <div class="detail-value">${vo.content}</div>
                </div>
            </div>
            <hr>
            <div class="comment-section">
                <h3>댓글</h3>
                <div id="comment-write-area">
                    <input type="text" name="content" placeholder="댓글을 작성하세요.">
                    <button onclick="writeComment(${vo.no});">등록</button>
                </div>
                <div id="comment-list-area" boardNo = "${vo.no}"></div>
            </div>
            <div class="button-container">
                <button class="left-button" onclick="history.back()">목록</button>
                <div class="right-buttons">
                    <c:if test="${loginMemberVo.id == vo.writerNo}">
                        <button class="btn-del" onclick="location.href='/board/del?bno=${vo.no}'">삭제</button>
                        <button class="btn-edit" onclick="location.href='/board/edit?bno=${vo.no}'">수정</button>
                    </c:if>
                </div>
            </div>
        </div>
        
    </main>
</body>
</html>
