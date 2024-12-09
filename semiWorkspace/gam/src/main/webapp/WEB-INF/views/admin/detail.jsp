<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>detail</title>
    <link rel="stylesheet" href="/css/admin/detail.css">
    <style>
        .form-contain{
            border: 1px solid black;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="form-contain">
            <div class="content-wrapper">
                <div></div>
                <div class="group-one">
                    <div class="profile">
                        <img src="http://127.0.0.1/img/profile/${vo.profile}" width="100px" height="100px">
                    </div>
                   <h3>${vo.memberName}</h3>
               </div>
               <div class="group-two">
                    <div class="group-up">
                        <table class="table-area">
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
                   <div class="group-under">
                       <table class="table-area">
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
            </div>


            <div class="btn-area">
                <div id="list-btn">
                    <button id="list" onclick="location.href='/admin/list?pno=1'">목록</button>
                </div>
                <div id="edit-delete-btn">
                            <button id="delete" onclick="location.href='/admin/del?no=${vo.empNo}'">삭제</button>
                            <button id="edit" onclick="location.href='/admin/edit?no=${vo.empNo}'">수정</button>
                </div>
            </div>

            </div>
        </main>

</body>
</html>