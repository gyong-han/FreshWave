<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/store/detail.css">
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fcd1d93cc41be251252d5ca5e6380217&libraries=services"></script>
    <script defer src="/js/store/detail.js"></script>
</head>
<body>
    <div><h1>${vo.name}</h1></div>
    <div id="map" style="width:250px;height:250px;" ></div><br>
    <table class="table-area">
        <tr>
            <th>가맹점장</th>
            <td>${vo.ceo}</td>
        </tr>
        <tr>
            <th>가맹점 전화번호</th>
            <td>${vo.phone}</td>
        </tr>
        <tr>
            <th>사업자 등록번호</th>
            <td>${vo.brn}</td>
        </tr>
        <tr>
            <th>대표자 전화번호</th>
            <td>${vo.ceoPhone}</td>
        </tr>
        <tr>
            <th>교육일</th>
            <td>${vo.eduDate}</td>
        </tr>
        <tr>
            <th>영업 시작일</th>
            <td>${vo.openDate}</td>
        </tr>
        <tr>
            <th>영업 종료일</th>
            <td>${vo.closeDate}</td>
        </tr>
        <tr>
            <th>영업상태</th>
            <td>${vo.status}</td>
        </tr>
        <tr>
            <th>계약 시작일</th>
            <td>${vo.startDate}</td>
        </tr>

        <tr>
            <th>계약 종료일</th>
            <td>${vo.endDate}</td>
        </tr>
        <tr>
            <th>주소</th>
            <td>${vo.address}</td>
        </tr>
        <tr>
            <th>첨부파일</th>
            <td><input type="file" style="pointer-events: none;"><span>${vo.originName}</span></td>
        </tr>
    </table>
    <button onclick="location.href='/store/list'">목록</button>
    <c:if test="${loginMemberVo.id == vo.managerNo}">
            <button onclick="location.href='/store/edit?no=${vo.no}'">수정</button>     
            <button onclick="storeDelete(`${vo.no}`)">삭제</button>
    </c:if>

    <input type="hidden" id="address" value="${vo.address}">
    <input type="hidden" id="name" value="${vo.name}">
</body>
</html>