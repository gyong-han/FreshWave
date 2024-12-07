<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js'></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script defer src="/js/schedule/edit.js"></script>
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
                <input type="time" id="calStartTime" name="startTime" value="${vo.startTime}">
                <span>-</span>
                <input type="date" id="calEndDate" name="finishDate" value="${vo.finishDate}">
                <input type="time" id="calEndTime" name="finishTime" value="${vo.finishTime}">
                <br>
                <table border="1">
                    <tr>
                      <td width="200">우편번호</td>
                      <td><input type="text" name="zipcode" id="zipcode" size="7" readonly>
                        <input type="button" value="우편번호찾기" onclick="kakaopost();">
                      </td>
                    </tr>
                    <tr>
                      <td>주소</td>
                      <td><input type="text" name="location" id="address" size="70" value="${vo.location}">
                      </td>
                    </tr>
                  </table>
                <br>
                <label>중요도 : </label>
                <select name="priority">
                    <c:forEach items="${priorityVoList}" var="priorityVo">
                        <option value="${priorityVo.no}">${priorityVo.name}</option>
                    </c:forEach>
                </select>
                <br>
                <textarea name="content" id="calContent">${vo.content}</textarea>
                <input type="submit" value="작성하기">
            </form>
        </div>
    </main>

</body>
</html>