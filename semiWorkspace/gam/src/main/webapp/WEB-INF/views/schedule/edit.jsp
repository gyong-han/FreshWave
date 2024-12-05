<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js'></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script defer src="/js/schedule/write.js"></script>
<link rel="stylesheet" href="/css/schedule/write.css">
<title>FRESH WAVE</title>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <form action="/schedule/edit" method="post">
                <input type="hidden" name="no" value="${vo.no}">
                <input type="text" id="calTitle" name="title" value="${vo.title}">
                <br>
                <input type="date" id="calStartDate" name="startDate" value="${vo.startDate}">
                <div class="faildate-msg1">시작일을 선택해주세요.</div>
                <input type="time" id="calStartTime" name="startTime" value="${vo.startTime}">
                <span>-</span>
                <input type="date" id="calEndDate" name="finishDate" value="${vo.finishDate}">
                <div class="faildate-msg2">종료일을 선택해주세요.</div>
                <div class="faildate-msg3">종료일이 시작일보다 빠릅니다. 일정을 다시 선택해주세요.</div>
                <input type="time" id="calEndTime" name="finishTime" value="${vo.finishTime}">
                <br>
                <input type="text" name="location" value="${vo.location}">
                <br>
                <label>중요도 : </label>
                <select name="priority">
                    <c:forEach items="${priorityVoList}" var="priorityVo">
                        <option value="${priorityVo.no}">${priorityVo.name}</option>
                    </c:forEach>
                </select>
                <br>
                <input type="text" name="userAdd" placeholder="사용자 추가">
                <br>
                <textarea name="content" id="calContent" value="${vo.content}"></textarea>
                <input type="submit" value="작성하기">
            </form>
        </div>
    </main>

</body>
</html>