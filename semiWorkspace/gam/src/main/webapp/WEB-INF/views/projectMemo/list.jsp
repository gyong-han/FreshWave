<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 메모 목록조회</title>

</head>
<body>
    <form action="/projectMemo/cardList">
        <button>카드조회</button>
    </form>
   <form action="/projectMemo/write">
       <button>메모 생성</button>
   </form>
    <div class="prj-list-area">
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
                <hr>
            </thead>
            <tbody>
                <tr>
                    <td>광고 모델 섭외</td>
                    <td>진행 대기</td>
                    <td>이감자</td>
                    <td>HIGH</td>
                    <td>24.11.25</td>
                    <td>24.11.27</td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="page-area">
        < 1 2 3 4 5 >
    </div>



</body>
</html>