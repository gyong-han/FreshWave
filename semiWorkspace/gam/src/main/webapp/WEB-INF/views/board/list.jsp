<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록조회</title>
<link rel="stylesheet" href="/css/board/list.css">
<script defer src="/js/board/list.js"></script>
</head>
<body>
    <main>
        <div class="table-area">
            <table>
                <thead>
                    <tr>
                        <th>글번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>등록일</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <div class="main-bottom-area">
            <button onclick="location.href='/board/write'">게시글 작성</button>
        </div>
        <div class="page-area"></div>
    </main>
</body>
</html>
