<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="/css/member/login.css">
</head>
<body>
    <div class="container">
        <img src="/img/logo/logo.png" alt="logo" class="logo">
        <div class="form-container">
            <form action="/member/login" method="post">
                <div class="form-group">
                    <input type="text" name="cpCode" id="company-code" placeholder=" " required>
                    <label for="company-code">COMPANY CODE</label>
                </div>
                <div class="form-group">
                    <input type="text" name="id" id="user-id" placeholder=" " required>
                    <label for="user-id">ID</label>
                </div>
                <div class="form-group">
                    <input type="password" name="pwd" id="password" placeholder=" " required>
                    <label for="password">PASSWORD</label>
                </div>
                <div class="form-check">
                    <input type="checkbox" id="startTime">
                    출근하기
                </div>
                <button type="submit" class="login-btn">LOGIN</button>
            </form>
        </div>
    </div>
</body>
</html>