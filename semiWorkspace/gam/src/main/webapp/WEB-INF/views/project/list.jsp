<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 목록</title>
    <link rel="stylesheet" href="/list.js">
</head>
<body>

    <div class="prj-list-area">
        <table border="1">
            <thead >
                <tr>
                    <td>프로젝트 이름</td>
                    <td>부서</td>
                    <td>프로젝트 생성자</td>
                    <td>중요도</td>
                    <td>등록일</td>
                    <td>수정일</td>
                </tr>
                <hr>
            </thead>
            <tbody>
                <tr>
                    <td>역삼 1호점 프로젝트</td>
                    <td>개발부</td>
                    <td>이감자</td>
                    <td>HIGH</td>
                    <td>24.11.11</td>
                    <td>24.11.12</td>
                </tr>
            </tbody>
        </table>
       <form action="/project/write">
           <input type="submit" value="프로젝트 생성">
       </form>
    </div>

    <div class="page-area">
        < 1 2 3 4 5 >
    </div>


</body>
</html>