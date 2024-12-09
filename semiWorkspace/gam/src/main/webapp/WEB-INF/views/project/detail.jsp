<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 상세조회</title>
<link rel="stylesheet" href="/css/common/header.css">
<link rel="stylesheet" href="/css/common/home.css">
<link rel="stylesheet" href="/css/project/detail.css">
<script defer src="/js/common/header.js"></script>
<script defer src="/js/common/home.js"></script>
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
                <div class="content">
                    <div class="box">

                        <div class="head">
                        <h1>${projectVo.name}</h1>
                        </div>

                        <div class="project">
                            <div class="input-area">
                                <div>
                                    <div class="title-area">생성자</div>
                                    <input type="text" value="${projectVo.memberName}" readonly>
                                </div>
                                <div>

                                    <div class="title-area">우선순위</div>
                                    <input type="text" value="${projectVo.priorityName}" readonly>
                                <br>
                                </div>
                                <div>
                                    <div class="title-area">시작날짜</div>
                                    <input type="text" value="${projectVo.startDate}" readonly>
                                </div>
                                <div>   
                                    <div class="title-area">마감날짜</div>
                                    <input type="text" value="${projectVo.endDate}" readonly>
                                </div>
                                <div>
                                    <div class="title-area">프로젝트 권한 설정</div>
                                    <input type="text" value="${projectVo.disclosure}"readonly>
                                </div>
                            </div>
                        

                            <div class="add-area">
                                <div class="title-area">참여인원</div>
                                <table>
                                    <tbody>
                                        <c:forEach items="${add}" var="vo">
                                            <tr>
                                                <td>${vo.profileName}</td>
                                                <td>${vo.name}</td>
                                                <td>${vo.deptName}</td>
                                                <td>${vo.jobName}</td>
                                                <td>${vo.modiAuth}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="button-area">
                        <span>
                            <span id="btn" class="spanBtn">
                                <button onclick="location.href='/project/list'">목록</button>
                            </span>
                        </span>
                        <span>
                            <span class="spanBtn">
                                <button onclick="location.href='/project/delete?projectNo=${projectVo.key}'">삭제</button>
                            </span>
                            <span id="mouse">
                                <button onclick="location.href='/project/edit?projectNo=${projectVo.key}'">수정</button>
                            </span>
                        </span>
                        </div>
            </div>
    </main>
</body>
</html>