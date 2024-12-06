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
                        <div class="detail-value">${vo.name}(${vo.deptName})<br>${vo.enrollDate}</div> 
                    </div>
                    <div class="detail-row">
                        <div class="detail-label">유형</div>   
                        <div class="detail-value">${vo.urgentYn == 'Y' ? '긴급' : '일반'}</div> 
                    </div>
                </div>
                <div class="detail-row content-block">
                    <div class="detail-label">내용</div>   
                    <div class="detail-value">${vo.content}</div> 
                </div>
                <div class="detail-row">
                    <div class="detail-label">첨부파일</div>
                    <div class="detail-value">
                        <c:forEach items = "${attachmentVoList}" var="attachVo">
                            <img src="/img/notice/attachment/${attachVo.changeName}" alt="${attachVo.originName}" width="80px" height="80px">
                        </c:forEach>
                    </div>
                </div>
            </div>
                <div class="button-container">
                <button class="left-button" onclick="history.back()">목록</button>
                <div class="right-buttons">
                    <c:if test="${loginMemberVo.id == vo.writerNo}">
                        <button class="btn-del" onclick="location.href='/notice/del?no=${vo.no}'">삭제</button>
                        <button class="btn-edit" onclick="location.href='/notice/edit?no=${vo.no}'">수정</button>
                    </c:if>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
