<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 메모 상세조회</title>

</head>
<body>

    <h1>이벤트 페이지 디자인</h1>
    <form action="/projectMemo/edit?projectNo=${no}">
    <div class="prj-detail-box">
        <div>
            <div>우선순위</div>
                <input type="text" name="priorityName" value="${memoVo.priorityName}" readonly>
            <div>진행상태</div>
                <input type="text" name="ing" value="${memoVo.ing}" readonly>
            <br>
            <div>
                <div>시작날짜</div>
                <input type="text" name="startDate" value="${memoVo.startDate}" readonly>
                <div>마감날짜</div>
                <input type="text" name="endDate" value="${memoVo.endDate}" readonly>
            </div>
            <div>첨부파일</div>
             <button>첨부파일</button> <span>이벤트페이지시안.pdf</span>
        </div>

        <div>
            <div>메모 내용</div>
            <textarea name="content">${memoVo.content}</textarea>
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
    <input type="submit" value="수정">
    <input type="hidden" value="${memoVo.no}" name="no">
    <input type="hidden" value="${memoVo.prjKey}" name="prjKey">
    </form>
    <button onclick="location.href='/projectMemo/delete?projectNo=${memoVo.prjKey}'">삭제</button>
    <button onclick="location.href='/projectMemo/list?projectNo=${memoVo.prjKey}'">목록</button>

</body>
</html>