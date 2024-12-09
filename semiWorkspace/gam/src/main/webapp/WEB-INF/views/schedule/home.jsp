<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- fullCalendar -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<!-- bootStrap -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- swal -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<!-- address -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- home.js -->
<script defer src="/js/schedule/home.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<link rel="stylesheet" href="/css/schedule/home.css">
<title>FRESH WAVE</title>
</head>
<body>
            <%@ include file="/WEB-INF/views/common/header.jsp" %>
        <main class="main-container">
            <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
            <div class="content-wrapper">
                <div id='calendar'></div>
            </div>
        </main>
<!-- Modal -->
<div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="addModalLabel">일정 추가하기</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <label class="sch-label">일정 제목</label>
                <input type="text" id="calTitle" name="title" placeholder="일정 제목을 입력해주세요!">
                <div></div>
                <input type="date" id="calStartDate" name="startDate">
                <input type="time" id="calStartTime" name="startTime">
                <span>-</span>
                <input type="date" id="calEndDate" name="finishDate">
                <input type="time" id="calEndTime" name="finishTime">
                <br>
                <label class="sch-label">주소</label>
                <br>
                <input type="text" name="zipcode" id="zipcode" size="7" readonly>
                <input type="button" id="address-btn" value="우편번호찾기" onclick="kakaopost();">
                <br>
                <input type="text" name="location" id="address" size="70">
                <br>
                <label class="sch-label">중요도</label>
                <select name="priority" id="priority"></select>
                <br>
                <textarea name="content" id="calContent" placeholder="내용을 입력해주세요."></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="close-btn" data-bs-dismiss="modal">목록</button>
                <button type="button" class="save-btn">작성</button>
            </div>
        </div>
    </div>
</div>

  <!-- Modal -->
  <div class="modal fade" id="detailModal" tabindex="-1" aria-labelledby="detailModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="detailModalLabel">일정 상세조회</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" id="detail-body">
        </div>
        <div class="modal-footer">
            <button type="button" class="close-btn" data-bs-dismiss="modal">목록</button>
            <button type="button" class="del-btn" onclick="delEvent(this);">삭제</button>
            <button type="button" class="edit-btn" onclick="changeEvent(this);">수정</button>
            <!-- <button type="button" class="save-btn">Save changes</button> -->
        </div>
      </div>
    </div>
  </div>

</body>
</html>