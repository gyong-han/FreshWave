<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/business/edit.css">
    <link rel="stylesheet" href="/css/business/detail.css">
    <script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fcd1d93cc41be251252d5ca5e6380217&libraries=services"></script>
    <script defer src="/js/business/edit.js"></script>
</head>
<body>
    <div><h1>${vo.name}</h1></div>
    <div id="map" style="width:250px;height:250px;" ></div><br>
    <form action="/business/edit?no=${vo.no}" method="post" enctype="multipart/form-data">
        <table class="table-area">
            <!--td는 inputTag로 바꾸기-->
            <tr>
                <th>대표자명</th>
                <td><input type="text" value="${vo.ceo}" name="ceo"></td>
            </tr>
            <tr>
                <th>대표자 전화번호</th>
                <td><input type="number" value="${vo.phone}" name="phone"><br></td>
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
                <td><input type="date" value="${vo.startDate}" name="startDate"></td>
            </tr>
            <tr>
                <th>계약 종료일</th>
                <td><input type="date" value="${vo.endDate}" name="endDate"></td>
            </tr>
            <tr>
                <th>주소</th>
                <td>
                    <input type="text" id="sample4_roadAddress"  value="${vo.address}" size="40" >
                    <input type="text" id="sample4_detailAddress" placeholder="상세주소">
                    <input type="button" onclick="sample4_execDaumPostcode()" value="주소 찾기">
                </td>
            </tr>
    
            <tr>
                <th>첨부파일</th>
                <td><input type="file" style="pointer-events: none;"><span>${vo.originName}</span></td>
            </tr>
        </table>

        <input type="hidden" id="searchAddress" value="${vo.address}">    
        <input type="hidden" id="name" value="${vo.name}">
        <input type="hidden" id="address" name="address">
        
        <button type="button" onclick="location.href='/business/detail?no=${vo.no}'">취소</button>
        <input type="submit" value="수정" onclick="combineAddress();">
   
    </form>
        <hidden input type="text" id="sample4_postcode" placeholder="우편번호"><br>
        <hidden input type="text" id="sample4_jibunAddress" placeholder="지번주소">
        <hidden span id="guide" style="color:#999;display:none"></span>
        <hidden input type="text" id="sample4_extraAddress" placeholder="참고항목"><br>

    
    
</body>
</html>