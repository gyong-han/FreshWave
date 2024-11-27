<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/business/detail.css">
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fcd1d93cc41be251252d5ca5e6380217&libraries=services"></script>
    <script defer src="/js/business/detail.js"></script>
</head>
<body>
    <div><h1>KH 식품</h1></div>
    <div id="map" style="width:350px;height:350px;" ></div><br>
    <table class="table-area">
        <!--td는 inputTag로 바꾸기-->
        <tr>
            <th>대표자명</th>
            <td>${vo.ceo}</td>
        </tr>
        <tr>
            <th>대표자 전화번호</th>
            <td>${vo.phone}</td>
        </tr>
        <tr>
            <th>사업자 등록번호</th>
            <td>${vo.brn}</td>
        </tr>
        <tr>
            <th>업태</th>
            <td>${vo.btCode}</td>
        </tr>
        <tr>
            <th>담당부서</th>
            <td>${vo.deptName}</td>
        </tr>
        <tr>
            <th>담당자</th>
            <td>${vo.managerName}</td>
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

    <input type="hidden" id="address" value="${vo.address}">
    <input type="hidden" id="name" value="${vo.name}">
    <button onclick="location.href='/business/list'">목록</button>
</body>
</html>