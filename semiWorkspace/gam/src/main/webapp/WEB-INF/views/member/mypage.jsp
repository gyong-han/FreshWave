<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UPDATE</title>
    <script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script defer src="/js/member/mypage.js"></script>
    <link rel="stylesheet" href="/css/member/mypage.css">


</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
    <div class="form-join-container">
         <!-- 메인 폼 -->
         <form id="edit-member" action="/member/edit" method="post" class="form-container" enctype="multipart/form-data">
            <div class="join-contain">
                    <!-- 프로필 섹션 -->
                    <div class="left-join">
                        <div class="profile">
                            <img src="http://127.0.0.1/img/profile/${loginMemberVo.profile}" width="100px" height="100px">
                        </div>
                        <h2>${loginMemberVo.name}</h2>
                        <p>${loginMemberVo.deptName}(${loginMemberVo.jobName})</p>
                        <p>${loginMemberVo.email}</p>
                        <label for="file-upload" class="custom-file-upload">사진 변경</label>
                        <input id="file-upload" name="f" type="file" accept=".png,.jpg,.jpeg,.svg" >
                    </div>
                    <br>
            <input type="hidden" name="id" value="${loginMemberVo.id}">

            <!-- 닉네임 -->
            <div class="right-join">
                <label id="nick">
                    닉네임
                    <br><br>
                    <input type="text" class="input-contain" name="nick" value="${loginMemberVo.nick}" placeholder="닉네임을 입력해주세요" oninput="disableSubmitBtn();">
                </label>
                <br>

                <!-- 비밀번호 -->
                <div>
                    <label for="new-password">
                        비밀번호
                        <br><br>
                    </label>
                        <div class="password-wrapper">
                            <input type="password" class="input-contain" name="pwd" id="new-password" placeholder="변경할 비밀번호를 입력해주세요">
                            <button type="button" class="toggle-btn" onclick="togglePassword();">보기</button>
                        </div>
                        <div class="strongPassword-message hide">8글자 이상, 영문, 숫자, 특수문자(@$!%*#?&)를 사용하세요</div>
                </div>
                    
                <br>


                <!-- 전화번호 -->
                <label id="phone">
                    전화번호
                    <br><br>
                    <input type="tel" class="input-contain" name="phone" value="${loginMemberVo.phone}" placeholder="전화번호를 입력해주세요" required>
                </label>
                <br>

                <!-- 주소 -->
                <label>
                    주소
                    <br><br>
                    <input type="text" class="input-address" id="sample4_roadAddress" name="roadAddress" value="${loginMemberVo.address}" placeholder="주소" size="30">
                    <input type="button" class="input-enroll" onclick="sample4_execDaumPostcode();" value="주소 찾기"><br><br>
                    <input type="text" class="input-address" id="sample4_detailAddress" name="detailAddress" placeholder="상세주소"><br>

                    <input type="hidden" id="address" name="address">

                    <hidden input type="text" id="sample4_postcode" placeholder="우편번호"><br>
                    <hidden input type="text" id="sample4_jibunAddress" placeholder="지번주소">
                    <hidden span id="guide" style="color:#999;display:none"></span>
                    <hidden input type="text" id="sample4_extraAddress" placeholder="참고항목">
                </label>
            </div>
            <br>

            <!-- 버튼 -->
            <div class="button-contain">
                <button type="button" onclick="location.href='/home'" id="cancel">취소</button>
                <button type="submit" onclick="combineAddress();" id="enroll">저장</button>
            </div>
        </form>
    </div>
       
    </main>

        

</body>
</html>
