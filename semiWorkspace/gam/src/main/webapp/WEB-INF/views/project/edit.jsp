<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 수정</title>
    <script defer src="/js/project/edit.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

<h1>${projectVo.name}</h1>
<form action="/project/edit" method="post">
    <div class="prj-detail-box">
        <div>
            <input type="hidden" name ="key" value="${projectVo.key}">
            <div>생성자</div>
            <input type="text" name = "id" value="${projectVo.memberName}" readonly>
            <div>우선순위</div>
            <select name="priority" value="${projectVo.priorityName}">
                <option value="1" ${projectVo.priorityName == 'HIGH' ? 'selected' : ''} >HIGH</option>
                <option value="2" ${projectVo.priorityName == 'MIDDLE' ? 'selected' : ''}>MIDDLE</option>
                <option value="3" ${projectVo.priorityName == 'LOW' ? 'selected' : ''} >LOW</option>
            </select>
            <br>
            <div class="edit-date">
                <div>시작날짜</div>
                <input type="hidden" name="startDate1" value="${projectVo.startDate}">
                <input type="date" id="start" name="startDate2">
                <input type="hidden" name="startDate">
                <div>마감날짜</div>
                <input type="hidden" name="endDate1" value="${projectVo.endDate}">
                <input type="date"  id="end" name="endDate2">
                <input type="hidden" name="endDate">
            </div>
            <div>프로젝트 권한 설정</div>
                <select name="disclosure" disabled id="open">
                    <option value="전체공개" ${projectVo.disclosure == '전체공개' ? 'selected' : ''}>전체공개</option>
                    <option value="부서공개" ${projectVo.disclosure == '부서공개' ? 'selected' : ''}>부서공개</option>
                    <option value="비공개" ${projectVo.disclosure == '비공개' ? 'selected' : ''}>비공개</option>
                </select>
            </div>
            <c:if test ="${projectVo.disclosure != '비공개'}">
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
                    <div>참여인원</div>
                     <table id="addMember-area" name="ProjectMemberVo">
                            <thead>
                                <tr>
                                    <th>사용자 이름</th>
                                    <th>부서</th>
                                    <th>권한</th>
                                    <th>삭제</th>
                                </tr>
                            </thead>
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
         <input type="submit" value="저장">
</form>
<button onclick="location.href='/project/detail?projectNo=${projectVo.key}'">취소</button>
</body>
</html>