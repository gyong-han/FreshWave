<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FRESH WAVE</title>
<link rel="stylesheet" href="/css/schedule/write.css">
<script defer src="/js/schedule/write.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <div id="content-area">
                <form class="write-form" action="/schedule/write" method="post">
                    <div id="title-area">
                        <label class="sch-label">일정 제목</label>
                        <br>
                        <input type="text" name="title" placeholder=" 제목을 입력해주세요.">
                    </div>
                    <br>
                    <div id="date-area">
                      <input type="date" id="calStartDate" name="startDate">
                      <input type="time" id="calStartTime" name="startTime">
                      <span>-</span>
                      <input type="date" id="calEndDate" name="finishDate">
                      <input type="time" id="calEndTime" name="finishTime">
                    </div>
                    <br>
                    <div id="location-area">
                      <label class="sch-label">주소</label>
                      <br>
                      <input type="text" name="zipcode" id="zipcode" size="7" readonly>
                      <input type="button" id="address-btn" value="우편번호찾기" onclick="kakaopost();">
                      <br>
                      <input type="text" name="location" id="address" size="70">
                    </div>
                    <div id="priority-area">
                        <label class="sch-label">우선순위</label>
                      <select name="priority">
                        <c:forEach items="${priorityVoList}" var="priorityVo">
                            <option value="${priorityVo.no}">${priorityVo.name}</option>
                        </c:forEach>
                      </select>
                    </div>
                    <div id="text-area">
                          <label class="sch-label">일정 내용</label>
                          <br>
                          <textarea name="content" placeholder="내용을 입력해주세요."></textarea>
                    </div>
                    <div class="btn-area">
                        <button type="button" id="back" onclick="cancel();">취소</button>
                        <!-- <button type="button" id="cancel" onclick="location.href='/schedule/home'">취소</button> -->
                        <button type="submit" id="enroll">작성</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
</body>
</html>