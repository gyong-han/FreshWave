<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상세조회</title>
    <link rel="stylesheet" href="/css/board/detail.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script defer src="/js/board/detail.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", () => {
            <c:if test="${not empty alertNo}">
                console.log(${alertNo});

                <c:if test="${alertNo == 1}">
                    editSuccess();
                </c:if>
                <c:if test="${alertNo == 0}">
                    editFail();
                </c:if>
                <c:remove var="alertNo" scope="session" />
            </c:if>
        });
    </script>
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
                        <div class="detail-label"></div>
                        <div class="detail-value">
                            <div class="detail-nick">
                                ${vo.nick}<br>
                            </div>
                            <div class="detail-enrollDate">
                                ${vo.enrollDate}
                            </div>
                        </div>
                    </div>
                    <div class="detail-row">
                        <div class="detail-label">첨부파일</div>
                        <div class="detail-value">
                            <a onclick="imgOpen(`${vo.changeName}`);">&nbsp${vo.originName}</a>
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
                    <button class="btn-submit" onclick="writeComment(${vo.no});">등록</button>
                </div>
                <div id="comment-list-area" boardNo = "${vo.no}"></div>
            </div>
            <div class="button-container">
                <button class="left-button" onclick="location.href='/board/list'">목록</button>
                <div class="right-buttons">
                    <c:if test="${loginMemberVo.id == vo.writerNo}">
                        <button class="btn-del" onclick="boardDelete(`${vo.no}`)" id="delete">삭제</button>
                        <button class="btn-edit" onclick="location.href='/board/edit?bno=${vo.no}'">수정</button>
                    </c:if>
                </div>
            </div>
        </div>
        
    </main>
</body>
</html>
