<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>

</head>
<body>

	<h1>게시글 작성</h1>

    <form action="/notice/write" method="post">
        <input type="text" name="title" placeholder="제목">
        <br>
        <textarea name="content" placeholder="내용"></textarea>
        <br>
        <input type="submit" value="작성하기">
    </form>



</body>
</html>