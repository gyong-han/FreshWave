<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SEMI</title>
<link rel="stylesheet" href="/css/business/insert.css">
<script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script defer src="/js/business/insert.js"></script>

</head>
<body>
   <h1>Business insert ~ !</h1>
    <form action="/business/insert" method="post" enctype="multipart/form-data">
        <label>거래처명</label><br>
        <input type="text" placeholder="거래처명을 입력해주세요." name="name"><br>

        <label>사업자 등록번호</label><br>
        <input type="number" placeholder="01212312345(-없이 입력)" name="brn"><br>

        <label>업태</label><br>
        <input type="number" placeholder="008123" name="btCode"><br>

        <label>대표자명</label><br>
        <input type="text" placeholder="홍길동" name="ceo"><br>

        <label>대표자 전화번호</label><br>
        <input type="number" placeholder="01012345678(-없이 입력)" name="phone"><br>

        <label>담당부서</label><br>
        <input type="text" placeholder="영업 2팀" name="deptName"><br>

        <label>계약 시작일</label><br>
        <input type="date" placeholder="2024-12-18" name="startDate"><br>

        <label>계약 종료일</label><br>
        <input type="date" placeholder="2024-12-18" name="endDate"><br>

        <input type="text" id="sample4_roadAddress"  placeholder="주소" size="30">
        <input type="text" id="sample4_detailAddress" placeholder="상세주소">
        <input type="button" onclick="sample4_execDaumPostcode()" value="주소 찾기"><br>
        <input type="file" name="f"><br>
        <input type="submit" value="등록하기" onclick="combineAddress();">

        <input type="hidden" id="address" name="address">

        <hidden input type="text" id="sample4_postcode" placeholder="우편번호"><br>
        <hidden input type="text" id="sample4_jibunAddress" placeholder="지번주소">
        <hidden span id="guide" style="color:#999;display:none"></span>
        <hidden input type="text" id="sample4_extraAddress" placeholder="참고항목"><br>


    </form>


</body>
</html>