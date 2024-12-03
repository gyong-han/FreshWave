<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>detail</title>
    <style>
        .form-contain{
            border: 1px solid black;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <main>
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
                            <td>${vo.deptName}</td>
                            <th>직급</th>
                            <td>${vo.jobName}</td>
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
                            <td>${vo.hireDate}</td>
                            <th>퇴사일</th>
                            <td>${vo.quitDate}</td>
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
        </main>
        <button onclick="location.href='/admin/listDel?pno=1'">목록</button>
        <button onclick="location.href='/admin/out?no=${vo.empNo}'">삭제</button>
</body>
</html>