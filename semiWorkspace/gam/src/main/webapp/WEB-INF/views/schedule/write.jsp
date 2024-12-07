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
<script defer src="/js/schedule/write.js"></script>
<link rel="stylesheet" href="/css/schedule/write.css">
<title>FRESH WAVE</title>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <form action="/schedule/write" method="post">
                <input type="text" id="calTitle" name="title" placeholder="일정 제목을 입력해주세요!">
                <br>
                <input type="date" id="calStartDate" name="startDate">
                <input type="time" id="calStartTime" name="startTime">
                <span>-</span>
                <input type="date" id="calEndDate" name="finishDate">
                <input type="time" id="calEndTime" name="finishTime">
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
                      <td><input type="text" name="location" id="address" size="70">
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
                <textarea name="content" id="calContent" placeholder="내용을 입력해주세요."></textarea>
                <input type="submit" value="작성하기">
            </form>
        </div>
    </main>

</body>
</html>