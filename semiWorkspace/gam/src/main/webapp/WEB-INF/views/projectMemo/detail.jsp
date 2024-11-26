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
    <form action="/projectMemo/edit">
    <div class="prj-detail-box">
        <div>
            <div>우선순위</div>
                <input type="text" value="MIDDLE" readonly>
            <div>진행상태</div>
                <input type="text" value="진행중" readonly>
            <br>
            <div>
                <div>시작날짜</div>
                <input type="text" value="24.11.30" readonly>
                <div>마감날짜</div>
                <input type="text" value="24.12.30" readonly>
            </div>
            <div>관련 가맹점/거래처 명</div>
                <input type="text" value="튼튼페이지">
            <div>첨부파일</div>
             <button>첨부파일</button> <span>이벤트페이지시안.pdf</span>
        </div>

        <div>
            <div>메모 내용</div>
            <textarea name="content">이벤트 페이지 디자인 수정</textarea>
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
    </form>
    <form action="/projectMemo/list">
        <input type="submit" value="삭제">
    </form>

    <form action="/projectMemo/list">
        <input type="submit" value="목록">
    </form>

</body>
</html>