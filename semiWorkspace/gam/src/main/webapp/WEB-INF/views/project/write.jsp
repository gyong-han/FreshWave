<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 작성</title>
    <script defer src="/js/project/write.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

    <form action="/project/write" method="post">
        <input type="hidden" name="writerName" value="${loginMemberVo.id}">
        프로젝트 이름*
        <br>
        <input type="text" name="name" placeholder="프로젝트 이름을 입력해주세요.">
        <br>
        우선순위 설정
        <br>
        <select name="priority">
            <option value="1">HIGH</option>
            <option value="2">MIDDLE</option>
            <option value="3">LOW</option>
        </select>
        <br>
        프로젝트 권한 설정*
        <br>
        <select name="disclosure" id="open" onchange="openDept();">
            <option value="전체공개">전체공개</option>
            <option value="부서공개">부서공개</option>
            <option value="비공개">비공개</option>
        </select>
        <br>
        시작날짜
        <input type="date" class="start">
        <input type="hidden" name="startDate" id="start">
        마감날짜
        <input type="date" class="end">
        <input type="hidden" name="endDate" id="end">
        <br>
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
        <br>
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

            </tbody>
        </table>
        <br>
        <input type="submit" value="작성하기" onclick="removeHyphen();">
    </form>




</body>
</html>