<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SEMI</title>
<link rel="stylesheet" href="/css/store/insert.css">
<script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script defer src="/js/store/insert.js"></script>

</head>
<body>
   <h1>Store insert ~ !</h1>
    <form action="/store/insert" method="post" enctype="multipart/form-data">
        <label>가맹점명</label><br>
        <input type="text" placeholder="가맹점명을 입력해주세요." name="name"><br>

        <label>영업상태</label><br>
        <select name="status">
            <option value="영업 준비 중">영업 준비중</option>
            <option value="영업중">영업중</option>
            <option value="휴업중">휴업중</option>
            <option value="폐업">폐업</option>
        </select>
        <br>

        <label>사업자 등록번호</label><br>
        <input type="number" placeholder="01212312345(-없이 입력)" name="brn" max="9999999999"><br>

        <label>가맹점 전화번호</label><br>
        <input type="number" placeholder="021234567(-없이 입력)" name="phone" max="99999999999"><br>

        <label>가맹점장</label><br>
        <input type="text" placeholder="홍길동" name="ceo"><br>

        <label>가맹점장 전화번호</label><br>
        <input type="number" placeholder="01012345678(-없이 입력)" name="ceoPhone" max="99999999999"><br>

        <label>교육일자</label><br>
        <input type="date" name="eduDate"><br>

        <label>영업 시작일</label><br>
        <input type="date" name="openDate"><br>

        <label>계약 시작일</label><br>
        <input type="date" name="startDate"><br>

        <label>계약 종료일</label><br>
        <input type="date" name="endDate"><br>


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