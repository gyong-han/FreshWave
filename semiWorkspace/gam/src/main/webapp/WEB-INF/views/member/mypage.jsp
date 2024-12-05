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

    <style>
        .custom-file-upload {
          display: inline-block;
          padding: 10px 20px;
          background-color: #BFF205; /* 버튼 색상 */
          border-radius: 15px;
          color: #121212; /* 텍스트 색상 */
          cursor: pointer;
          text-align: center;
        }

        .hide {
        display: none;
        }

        input[type="file"] {
          display: none; /* 기본 파일 업로드 버튼 숨기기 */
        }
    </style>
</head>
<body>


        <!-- 메인 폼 -->
        <form id="edit-member" action="/member/edit" method="post" class="form-container">
            <div class="container">
                    <!-- 프로필 섹션 -->
                    <div class="member-profile">
                        ${loginMemberVo.profile}
                        <h2>${loginMemberVo.name}</h2>
                        <br>
                        <p>${loginMemberVo.deptName}(${loginMemberVo.jobName})</p>
                        <p>${loginMemberVo.email}</p>
                        <label for="file-upload" class="custom-file-upload">사진 변경</label>
                        <input id="file-upload" name="profile" type="file" accept=".png,.jpg,.jpeg,.svg">
                    </div>
                    <br>
            <input type="hidden" name="id" value="${loginMemberVo.id}">
            <!-- 닉네임 -->
            <div class="member-detail">
                <label id="nick">
                    닉네임
                    <br>
                    <input type="text" name="nick" value="${loginMemberVo.nick}" placeholder="닉네임을 입력해주세요" oninput="disableSubmitBtn();">
                    <input type="button" value="중복확인" class="semi-btn-nick" onclick="checkDupNick();">
                </label>
                <br>

                <!-- 비밀번호 -->
                <label id="password">
                    비밀번호
                    <br>
                    <input type="password" name="pwd" id="password" placeholder="비밀번호를 입력해주세요">
                </label>
                    <div class="strongPassword-message hide">8글자 이상, 영문, 숫자, 특수문자(@$!%*#?&)를 사용하세요</div>
                <br>

                <!-- 비밀번호 재입력 -->
                <label id="password-again">
                    비밀번호 재입력
                    <br>
                    <input type="password" id="password-retype" placeholder="비밀번호를 다시 입력해주세요">
                </label>
                  <div class="mismatch-message hide">비밀번호가 일치하지 않습니다</div>
                <br>

                <!-- 전화번호 -->
                <label id="phone">
                    전화번호
                    <br>
                    <input type="tel" name="phone" value="${loginMemberVo.phone}" placeholder="전화번호를 입력해주세요" required>
                </label>
                <br>

                <!-- 주소 -->
                <label>
                    주소
                    <br>
                    <input type="text" id="sample4_roadAddress" name="roadAddress" value="${loginMemberVo.address}" placeholder="주소" size="30">
                    <input type="button" onclick="sample4_execDaumPostcode();" value="주소 찾기"><br>
                    <input type="text" id="sample4_detailAddress" name="detailAddress" placeholder="상세주소"><br>

                    <input type="hidden" id="address" name="address">

                    <hidden input type="text" id="sample4_postcode" placeholder="우편번호"><br>
                    <hidden input type="text" id="sample4_jibunAddress" placeholder="지번주소">
                    <hidden span id="guide" style="color:#999;display:none"></span>
                    <hidden input type="text" id="sample4_extraAddress" placeholder="참고항목">
                </label>
            </div>
            <br>

            <!-- 버튼 -->
            <div>
                <button type="button" onclick="location.href='/home'">취소</button>
                <button type="submit" onclick="combineAddress();">저장</button>
            </div>
        </form>
    </div>


</body>
</html>
