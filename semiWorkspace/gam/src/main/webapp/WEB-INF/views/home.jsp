<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FRESH WAVE</title>
<link rel="stylesheet" href="/css/common/home.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
<script defer src="/js/common/home.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <div class="content-area">
                <div class="pieChart-area" style="width: 600px; height: 600px;">
                    <h1>프로젝트 중요도</h1>
                    <canvas id="myPieChart" ></canvas>
                </div>
                <div class="noti-bo-area">
                    <div id="notice-area">
                        <div id="content">
                            <div id="side">
                                <img src="/img/icon/sounds.svg" id="side-logo"><br>
                                <input type="text" id="noti-txt" value="공지사항" disabled>
                                <input type="text" id="noti-priority" value="긴급" disabled>
                            </div>
                            <div class="noti-table-area">
                                <table id="noti-table">
                                    <c:forEach items="${redNoticeList}" var="vo">
                                            <tr>
                                                <th onclick="location.href='/notice/detail?bno=${vo.no}'">❗${vo.title}</th>
                                                <td>${vo.enrollDate}</td>
                                            </tr>
                                        </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div id="notice-area">
                        <div id="content">
                            <div id="side">
                                <img src="/img/icon/sounds.svg" id="side-logo"><br>
                                <input type="text" id="noti-txt" value="공지사항" disabled>
                                <input type="text" id="noti-priority2" value="일반" disabled>
                            </div>
                            <div class="noti-table-area">
                                <table id="noti-table">
                                    <c:forEach items="${blueNoticeList}" var="vo">
                                        <tr>
                                            <th onclick="location.href='/notice/detail?bno=${vo.no}'">${vo.title}</th>
                                            <td>${vo.enrollDate}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div></div> <!--공란-->
        </div>
    </main>
</body>
</html>

