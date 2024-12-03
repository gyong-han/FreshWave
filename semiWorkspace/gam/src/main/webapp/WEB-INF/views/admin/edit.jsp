<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>edit</title>
    <style>
        .form-contain{
            border: 1px solid black;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <form action="/admin/edit" method="post"  enctype="multipart/form-data">
        <input type="hidden" name="no" value="${vo.empNo}">
        <div class="form-contain">
            <div class="group-detail">
                ${vo.profile}
                <h3>${vo.memberName}</h3>
            </div>
            <div class="group-detail">
                <table>
                    <tr>
                        <th>성별</th>
                        <td>${vo.gender}</td>
                        <th>사번</th>
                        <td>${vo.empNo}</td>
                    </tr>
                    <tr>
                        <th>부서</th>
                        <td>
                             <select name="deptCode" >
                                <c:forEach items="${deptVoList}" var="deptVo">
                                    <c:if test="${vo.deptCode != deptVo.deptCode}">
                                        <option value="${deptVo.deptCode}">${deptVo.deptName}</option>
                                    </c:if>
                                    <c:if test="${vo.deptCode == deptVo.deptCode}">
                                        <option selected value="${deptVo.deptCode}">${deptVo.deptName}</option>
                                    </c:if>
                                </c:forEach>
                             </select>


                        </td>
                        <th>직급</th>
                        <td>
                            <select name="jobCode">
                                 <c:forEach items="${jobVoList}" var="jobVo">
                                    <c:if test="${vo.jobCode != jobVo.jobCode}">
                                        <option value="${jobVo.jobCode}">${jobVo.jobName}</option>
                                    </c:if>
                                    <c:if test="${vo.jobCode == jobVo.jobCode}">
                                        <option selected value="${jobVo.jobCode}">${jobVo.jobName}</option>
                                    </c:if>
                                 </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <hr>
            <div class="group-detail">
                <table>
                    <tr>
                        <th>전화번호</th>
                        <td>${vo.phone}</td>
                        <th>이메일</th>
                        <td>${vo.email}</td>
                    </tr>
                    <tr>
                        <th>닉네임</th>
                        <td>${vo.nick}</td>
                        <th>생년월일</th>
                        <td>${vo.idNum}</td>
                    </tr>
                    <tr>
                        <th>입사일</th>
                        <td>
                            <input type="input" name="hireDate" value="${vo.hireDate}">
                        </td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td>${vo.address}</td>
                        <th>비밀번호</th>
                        <td>${vo.pwd}</td>
                    </tr>
                </table>
            </div>

        </div>
        <input type="button" id="list-button" value="목록">
        <input type="submit" id="save-button" value="저장">
    </form>
</body>
</html>