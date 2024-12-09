<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 수정</title>
    <link rel="stylesheet" href="/css/common/header.css">
    <link rel="stylesheet" href="/css/common/home.css">
    <link rel="stylesheet" href="/css/project/edit.css">
    <script defer src="/js/common/header.js"></script>
    <script defer src="/js/common/home.js"></script>
    <script defer src="/js/project/edit.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="content-wrapper">
                <div class="content">
                    <div class="box">

                        <div class="head">
                            <h1>${projectVo.name}</h1>
                        </div>
                        <form action="/project/edit" method="post">
                        <div class="project">
                            <div class="input-area">
                                <div>
                                    <input type="hidden" name ="key" value="${projectVo.key}">
                                    <div class="title-area">생성자</div>
                                    <input id="memberName" type="text" value="${projectVo.memberName}" readonly>
                                </div>
                                <div>
                                    <div class="title-area">우선순위</div>
                                    <select name="priority" value="${projectVo.priorityName}">
                                        <option value="1" ${projectVo.priorityName == 'HIGH' ? 'selected' : ''} >HIGH</option>
                                        <option value="2" ${projectVo.priorityName == 'MIDDLE' ? 'selected' : ''}>MIDDLE</option>
                                        <option value="3" ${projectVo.priorityName == 'LOW' ? 'selected' : ''} >LOW</option>
                                    </select>
                                <br>
                                </div>
                                <div  class="edit-date">
                                    <div>시작날짜</div>
                                    <input type="hidden" name="startDate1" value="${projectVo.startDate}">
                                    <input type="date" id="start" name="startDate2">
                                    <input type="hidden" name="startDate">
                                </div>
                                <div class="edit-date">   
                                    <div>마감날짜</div>
                                    <input type="hidden" name="endDate1" value="${projectVo.endDate}">
                                    <input type="date"  id="end" name="endDate2">
                                    <input type="hidden" name="endDate">
                                </div>
                                <div>
                                    <div class="title-area">프로젝트 권한 설정</div>
                                    <select name="disclosure" disabled id="open">
                                        <option value="전체공개" ${projectVo.disclosure == '전체공개' ? 'selected' : ''}>전체공개</option>
                                        <option value="부서공개" ${projectVo.disclosure == '부서공개' ? 'selected' : ''}>부서공개</option>
                                        <option value="비공개" ${projectVo.disclosure == '비공개' ? 'selected' : ''}>비공개</option>
                                    </select>
                                </div>
                                <div id="add">
                                    <c:if test ="${projectVo.disclosure != '비공개'}">
                                        <div>
                                            <label> 사용자 초대 </label>
                                            <br>
                                              <input list="empVoList" id="memberAddName"/>
                                                    <datalist id="empVoList">
                                                        <c:forEach items="${empVoList}" var="vo">
                                                            <option name="memberName" data-memberNo="${vo.empNo}">
                                                                ${vo.memberName} (${vo.deptName})
                                                            </option>
                                                        </c:forEach>
                                                    </datalist>
                                              <button type="button" id="close" onclick="addMember();">추가</button>
                                        </div>
                                </div>
                            </div>
                        

                            <div class="add-area">
                              
                                <div class="title-area">참여인원</div>
                                <table id="addMember-area" name="ProjectMemberVo">
                                    <tbody>
                                        <c:forEach items="${add}" var="vo">
                                            <tr>
                                                <td>${vo.profileName}</td>
                                                <td>${vo.name}</td>
                                                <td>(${vo.jobName})</td>
                                                <td>${vo.deptName}</td>
                                                <td>
                                                    <select name="access[]">
                                                        <option value="수정" ${vo.modiAuth == '수정' ? 'selected' : ''}>수정 </option>
                                                        <option value="읽기" ${vo.modiAuth == '읽기' ? 'selected' : ''}>읽기 </option>
                                                    </select>
                                                </td>
                                                <td><input type="hidden" name="empNo[]" value="${vo.empNo}" ></td>
                                                <td><button class="delMember" type="button"= onclick="delMember(this);">x</button></td>
                                                <td><input type="hidden" name="empNo" value="${vo.empNo}"></td>
                                                <td><input type="hidden" name="prjKey" value="${projectVo.key}"></td>
                                            </tr>
                                       </c:forEach>
                                    </tbody>
                                </c:if>
        
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="button-area">
                        <span>
                            <span class="spanBtn">
                                <button type="button" onclick="location.href='/project/detail?projectNo=${projectVo.key}'">취소</button>
                            </span>
                        </span>
                        <span>
                            <span id="btn" class="spanBtn">
                                <input type="submit" value="저장">
                            </span>
                        </span>
                    </form>
                    </div>
                </div>
    </main>
</body>
</html>