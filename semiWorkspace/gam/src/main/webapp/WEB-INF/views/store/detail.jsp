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
    <div><h1>역삼 1호점</h1></div>
    <div id="map" style="width:350px;height:350px;" ></div><br>
    <table border ="1">
        <!--td는 inputTag로 바꾸기-->
        <tr>
            <th>가맹점명</th>
            <td>이왕자</td>
        </tr>
        <tr>
            <th>가맹점 전화번호</th>
            <td>02-123-1234</td>
        </tr>
        <tr>
            <th>사업자 등록번호</th>
            <td>402-43-50251</td>
        </tr>
        <tr>
            <th>대표자 전화번호</th>
            <td>010-1234-5678</td>
        </tr>
        <tr>
            <th>교육일</th>
            <td>2024.12.15</td>
        </tr>
        <tr>
            <th>영업 시작일</th>
            <td>2024.12.01</td>
        </tr>
        <tr>
            <th>영업 종료일</th>
            <td>2026.11.02</td>
        </tr>
        <tr>
            <th>영업상태</th>
            <td>영업중</td>
        </tr>
        <tr>
            <th>계약 시작일</th>
            <td>2024.11.03</td>
        </tr>

        <tr>
            <th>계약 종료일</th>
            <td>2026.11.02</td>
        </tr>
        <tr>
            <th>주소</th>
            <td>서울시 강남구 테헤란로</td>
        </tr>
        <tr>
            <th>첨부파일</th>
            <td>이왕자</td>
        </tr>
    </table>
    <button onclick="location.href='/store/insert'">가맹점 추가</button>
</body>
</html>