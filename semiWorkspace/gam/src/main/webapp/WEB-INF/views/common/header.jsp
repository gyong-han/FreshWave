    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <link rel="stylesheet" href="/css/common/header.css">
        <link rel="stylesheet" href="/css/common/font.css">
        <link rel="stylesheet" as="style" crossorigin
            href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/static/pretendard.min.css" />
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script defer src="/js/common/header.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FRESH WAVE</title>

</head>
<body>
<header>
        <c:if test="${loginMemberVo != null}">
            <div class="logo">
                <img src="/img/logo/logo.png" alt="logo" onclick="location.href='/home'">
            </div>
        </c:if>
        <c:if test="${loginAdminVo != null}">
            <div class="logo">
                <img src="/img/logo/logo.png" alt="logo" onclick="location.href='/admin/home'">
            </div>
        </c:if>
        <div></div>
        <c:if test="${loginMemberVo != null}">
            <div id="account-list-icon"><img src="/img/icon/accountlist.svg" alt="account-list" onclick="location.href='/member/list'"></div>
            <div id="message-icon"><img src="/img/icon/message.svg" alt="message" onclick="location.href='/notion';">notion</div></div>
        </c:if>
        <c:if test="${loginAdminVo != null}">
            <div id="account-list-icon"></div>
            <div id="message-icon"></div>
        </c:if>
        <div class="profile-area">
            <div id="dropdown-container">
                <div id="profile" onclick="dropdown();">
                    <c:if test="${loginMemberVo != null}">
                        <img src="http://127.0.0.1/img/profile/${loginMemberVo.profile}">
                    </c:if>
                    <c:if test="${loginAdminVo != null}">
                        <img src="http://127.0.0.1/img/profile/8e0cfaf58709f7e626973f0b00d033d0.jpg">
                    </c:if>
                    
                </div>
                <c:if test="${loginMemberVo != null}">
                    <div id="dropdown">
                        <input type="button" value="마이페이지" onclick="location.href='/member/mypage'">
                        <input type="button" value="로그아웃" onclick="location.href='/member/logout'">
                        <input type="button" value="퇴근하기" onclick="finishLogOut();">
                    </div>
                </c:if>
                <c:if test="${loginAdminVo != null}">
                    <div id="dropdown">
                        <input type="button" value="로그아웃" onclick="location.href='/admin/logout'">
                    </div>
                </c:if>
        </div>
            <c:if test="${loginMemberVo != null}">
                <div id="userName"><span>${loginMemberVo.name}</span></div>
                <div id="userDept"><span>${loginMemberVo.deptName}(${loginMemberVo.jobName})</span></div>
            </c:if>
            <c:if test="${loginAdminVo != null}">
                <div id="userName"><span>${loginAdminVo.nick}</span></div>
            </c:if>


</header>

    

    </body>
    </html>

