<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
    <img src="/img/logo/logo.png" alt="logo">
    <div class="form-container">
        <form action="/member/login" method="post">
            <div class="form-group">
                <input type="text" name="cpCode" id="company-code" placeholder=" " required>
                <label for="company-code">회사 코드를 입력하세요</label>
            </div>
            <div class="form-group">
                <input type="text" name="id" id="user-id" placeholder=" " required>
                <label for="user-id">아이디</label>
            </div>
            <div class="form-group">
                <input type="password" name="pwd" id="password" placeholder=" " required>
                <label for="password">비밀번호</label>
            </div>
            <input type="submit" value="LOGIN">
        </form>
    </div>
</body>
</html>