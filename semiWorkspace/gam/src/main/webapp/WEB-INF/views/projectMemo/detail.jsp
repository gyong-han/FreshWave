<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 메모 상세조회</title>
    <link rel="stylesheet" href="/css/common/header.css">
    <link rel="stylesheet" href="/css/common/home.css">
    <link rel="stylesheet" href="/css/projectMemo/detail.css">
    <script defer src="/js/common/header.js"></script>
    <script defer src="/js/common/home.js"></script>
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <div>
                <div class="all">
                    <div class="margin">
                    <div class="prj-name">
                        <h1>${memoVo.name}</h1>
                    </div>
                <div class="prj-detail-box">
                    <div class="line">
                        <form action="/projectMemo/edit?projectNo=${no}">
                            <div>
                                <label>우선순위</label>
                                <input type="text" name="priorityName" value="${memoVo.priorityName}" readonly>
                                <label>진행상태</label>
                                <input type="text" name="ing" value="${memoVo.ing}" readonly>
                                 <br>
                            </div>
                            <div>
                                <label>시작날짜</label>
                                <input type="text" name="startDate" value="${memoVo.startDate}" readonly>
                                <label>마감날짜</label>
                                <input type="text" name="endDate" value="${memoVo.endDate}" readonly>
                            </div>
                    </div>
                        <div class="line2">
                            <label>메모 내용</label>
                            <input class="memo-content" type="text" name="content" value="${memoVo.content}" readonly>
                        </div>
                    </div>
                    <div class="re">
                        <div>
                            <label>댓글</label>
                            <textarea name="comment" placeholder="댓글을 남겨보세요."></textarea>
                            <table>
                                <tr>
                                    <td>이감자(개발1팀 사원)</td>
                                </tr>
                                <tr>
                                    <td>확인했습니다.</td>
                                </tr>
                                <tr>
                                    <td>2024.12.25. 13.02</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                </div>
                <div class="btn-area">
                    <button id="btn1" type="button" onclick="location.href='/projectMemo/list?projectNo=${memoVo.prjKey}'">목록</button>
                    <button id="btn2" type="button" onclick="location.href='/projectMemo/delete?projectNo=${memoVo.prjKey}&no=${no}'">삭제</button>
                    <input id="btn3" type="submit" value="수정">
                    <input type="hidden" value="${memoVo.no}" name="no">
                    <input type="hidden" value="${memoVo.prjKey}" name="prjKey">
                    </form>
                </div>
            </div>
        </div>
    </main>





</body>
</html>