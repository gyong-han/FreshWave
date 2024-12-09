<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 작성</title>
    <link rel="stylesheet" href="/css/project/write.css">
    <link rel="stylesheet" href="/css/common/header.css">
    <link rel="stylesheet" href="/css/common/home.css">
    <script defer src="/js/common/header.js"></script>
    <script defer src="/js/common/home.js"></script>
    <script defer src="/js/project/write.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
            <div class="content-area">
                <div class="content1">
                    <form class="content3" action="/project/write" method="post">
                        <div>
                            <input type="hidden" name="writerName" value="${loginMemberVo.id}">
                            프로젝트 이름*
                            <br>
                            <input class="in" type="text" name="name" placeholder="프로젝트 이름을 입력해주세요.">
                            <br>
                        </div>
                        <div>
                            우선순위 설정
                            <br>
                            <select class="in" name="priority">
                                <option value="1">HIGH</option>
                                <option value="2">MIDDLE</option>
                                <option value="3">LOW</option>
                            </select>
                            <br>
                        </div>
                        <div>
                            프로젝트 권한 설정*
                            <br>
                            <select class="in" name="disclosure" id="open" onchange="openDept();">
                                <option value="전체공개">전체공개</option>
                                <option value="부서공개">부서공개</option>
                                <option value="비공개">비공개</option>
                            </select>
                            <br>
                        </div>
                        <div>
                            시작날짜
                            <br>
                            <input type="date" class="start">
                            <br>
                            <input type="hidden" name="startDate" id="start">
                        </div>
                        <div>    
                            마감날짜
                            <br>
                            <input type="date" class="end">
                            <br>
                            <input type="hidden" name="endDate" id="end">
                            <br>
                        </div>
                    </div>
                <div class="content2">
                    <div>
                        <label> 사용자 초대 </label>
                        <br>
                        <input class="in" list="empVoList" id="memberAddName"/>
                            <datalist id="empVoList">
                                <c:forEach items="${empVoList}" var="vo">
                                    <option name="memberName" data-memberNo="${vo.empNo}">
                                        ${vo.memberName} (${vo.deptName})
                                    </option>
                                </c:forEach>
                            </datalist>
                        <button type="button" id="close" onclick="addMember();">추가</button>
                        <table id="addMember-area" name="ProjectMemberVo">
                            <tbody>
                
                            </tbody>
                        </table>
                    </div>
                </div>
                <div></div>
                <div class="button-area">
                <button id="cancle" class="button" type="button" onclick="location.href='/project/list'">취소</button>
                <input class="button" type="submit" value="저장" onclick="removeHyphen();">
                </div>
                </form>
            </div>
        </div>
    </main>

</body>
</html>