<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 메모 목록조회</title>
    <script defer src="/js/projectMemo/list.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
    <button onclick="location.href='/projectMemo/cardList?projectNo=${key}'">카드조회</button>
    <button onclick="location.href='/projectMemo/write?projectNo=${key}'">메모 생성</button>
    <div class="memo-list-area">
        <input type="hidden" value="${key}">
        <table border="1">
            <thead>
                <tr>
                    <td>메모 이름</td>
                    <td>진행상태</td>
                    <td>메모 생성자</td>
                    <td>중요도</td>
                    <td>시작일</td>
                    <td>종료일</td>
                </tr>
            </thead>
            <tbody>




            </tbody>
        </table>
    </div>

    <div class="page-area">
        < 1 2 3 4 5 >
    </div>

</body>
</html>