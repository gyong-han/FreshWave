<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 목록</title>
    <link rel="stylesheet" href="/css/project/cardList.css">
    <link rel="stylesheet" href="/css/common/header.css">
    <link rel="stylesheet" href="/css/common/home.css">
    <script defer src="/js/common/header.js"></script>
    <script defer src="/js/common/home.js"></script>
    <script defer src="/js/project/cardList.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <div>
                <div id="btn1">
                    <button type="button" class="btn1"value="카드조회"  onclick="location.href='/project/list'">목록조회</button>
                </div>
                <div class="card-list">
        
        
                </div>
            </div>
  
        </div>
    </main>


</body>
</html>