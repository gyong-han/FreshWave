<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 메모 수정</title>
<script defer src="/js/projectMemo/edit.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

    <h1>이벤트 페이지 디자인</h1>
    <form action="/projectMemo/edit" method="post">
    <div class="prj-detail-box">
        <div>
            <input type="hidden" name="no" value="${vo.no}">
            <div>우선순위</div>
                <select name="priority" value="${vo.priorityName}">
                    <option value="1" ${vo.priorityName == 'HIGH' ? 'selected' : ''} >HIGH</option>
                    <option value="2" ${vo.priorityName == 'MIDDLE' ? 'selected' : ''}>MIDDLE</option>
                    <option value="3" ${vo.priorityName == 'LOW' ? 'selected' : ''} >LOW</option>
                </select>
            <div>진행상태</div>
                <select name="ing" value="${vo.ing}">
                    <option value="진행대기" ${vo.ing == '진행대기' ? 'selected' : ''} >진행대기</option>
                    <option value="진행중" ${vo.ing == '진행중' ? 'selected' : ''}>진행중</option>
                    <option value="진행완료" ${vo.ing == '진행완료' ? 'selected' : ''} >진행완료</option>
                </select>
            <br>
            <div>
            <div class="edit-date">
                <div>시작날짜</div>
                <input type="hidden" name="startDate1" value="${vo.startDate}">
                <input type="date" id="start" name="startDate2">
                <input type="hidden" name="startDate">
                <div>마감날짜</div>
                <input type="hidden" name="endDate1" value="${vo.endDate}">
                <input type="date"  id="end" name="endDate2">
                <input type="hidden" name="endDate">
            </div>
            <div>첨부파일</div>
             <button>첨부파일</button> <span>이벤트페이지시안.pdf</span>
        </div>

        <div>
            <div>메모 내용</div>
            <textarea name="content">${vo.content}</textarea>
        </div>

        <div>
            <div>댓글</div>
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
    <input type="submit" value="저장">
    </form>
    <button onclick="location.href='/projectMemo/detail?projectNo=${vo.prjKey}'">취소</button>
</body>
</html>