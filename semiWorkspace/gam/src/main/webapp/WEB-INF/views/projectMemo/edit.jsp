<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 메모 수정</title>

<link rel="stylesheet" href="/css/common/header.css">
<link rel="stylesheet" href="/css/common/home.css">
<link rel="stylesheet" href="/css/projectMemo/edit.css">
<script defer src="/js/common/header.js"></script>
<script defer src="/js/common/home.js"></script>
<script defer src="/js/projectMemo/edit.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
                            <h1>이벤트 페이지 디자인</h1>
                        </div>
                        <div class="prj-detail-box">
                            <div class="line">
                                <form action="/projectMemo/edit" method="post">

                                    <div class="prj-detail-box">
                                        <div class="box">
                                            <input type="hidden" name="no" value="${vo.no}">
                                            <input type="hidden" name="prjKey" value="${vo.prjKey}">
                                            <div>
                                            <label>우선순위</label>
                                                <select name="priority" value="${vo.priorityName}">
                                                    <option value="1" ${vo.priorityName == 'HIGH' ? 'selected' : ''} >HIGH</option>
                                                    <option value="2" ${vo.priorityName == 'MIDDLE' ? 'selected' : ''}>MIDDLE</option>
                                                    <option value="3" ${vo.priorityName == 'LOW' ? 'selected' : ''} >LOW</option>
                                                </select>
                                            </div>
                                            <br>
                                            <div>
                                            <label>진행상태</label>
                                                <select name="ing" value="${vo.ing}">
                                                    <option value="진행대기" ${vo.ing == '진행대기' ? 'selected' : ''} >진행대기</option>
                                                    <option value="진행중" ${vo.ing == '진행중' ? 'selected' : ''}>진행중</option>
                                                    <option value="진행완료" ${vo.ing == '진행완료' ? 'selected' : ''} >진행완료</option>
                                                </select>
                                            </div>
                                            <br>
                                            <div>
                                                <div class="edit-date">
                                                    <label>시작날짜</label>
                                                    <input type="hidden" name="startDate1" value="${vo.startDate}">
                                                    <input type="date" id="start" name="startDate2">
                                                    <input type="hidden" name="startDate">
                                                </div>
                                            </div>
                                            <br>
                                            <div>
                                                <div class="edit-date">
                                                    <label>마감날짜</label>
                                                    <input type="hidden" name="endDate1" value="${vo.endDate}">
                                                    <input type="date"  id="end" name="endDate2">
                                                    <input type="hidden" name="endDate">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            <div class="line2">
                            <label>메모 내용</label>
                            <textarea name="content">${vo.content}</textarea>
                            </div>
                        </div>
                    </div>
                </div>  
                    <div class="btn-area">
                        <button type="button" onclick="location.href='/projectMemo/detail?projectNo=${vo.prjKey}'">취소</button>
                        <input id="btn3" type="submit" value="저장">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
    
</body>
</html>