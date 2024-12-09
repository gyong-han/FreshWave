 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사원 추가</title>
    <script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script defer src="/js/member/join.js"></script>
    <link rel="stylesheet" href="/css/member/join.css">

</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="form-join-container">
            <form action="/member/join" method="post" onsubmit="combineAddress()">
                <div class="join-contain">
                    <!-- 왼쪽 폼 -->
                    <div class="left-join">
                        <label>
                            부서
                            <label class="point">*</label>
                            <br>
                            <select name="deptCode"  class="input-contain" required>
                                <c:forEach items="${deptVoList}" var="deptVo">
                                    <option value="${deptVo.deptCode}">${deptVo.deptName}</option>
                                </c:forEach>
                            </select>
                        </label>

                        <label>
                            이름
                            <label class="point">*</label>
                            <br>
                            <input type="text" class="input-contain" name="name" placeholder="이름을 입력하세요" required>
                        </label>

                        <label>
                            전화번호
                            <label class="point">*</label>
                            <br>
                            <input type="tel" class="input-contain" name="phone" placeholder="01012345678" required>
                        </label>

                        <label>
                            닉네임
                            <br>
                            <input type="text" class="input-contain" name="nick" placeholder="닉네임을 입력하세요">
                        </label>

                        <!-- 주소 -->
                        <label>
                            주소
                            <br>
                            <input type="text" class="input-address" id="sample4_roadAddress" name="roadAddress" placeholder="주소" size="30">
                            <input type="button" class="input-enroll" onclick="sample4_execDaumPostcode()" value="주소 찾기"><br>
                            <br>
                            <input type="text" class="input-address" id="sample4_detailAddress" name="detailAddress" placeholder="상세주소"><br>

                            <input type="hidden" id="address" name="address">

                            <hidden input type="text" id="sample4_postcode" placeholder="우편번호"><br>
                            <hidden input type="text" id="sample4_jibunAddress" placeholder="지번주소">
                            <hidden span id="guide" style="color:#999;display:none"></span>
                            <hidden input type="text" id="sample4_extraAddress" placeholder="참고항목">
                        </label>
                    </div>

                    <!-- 오른쪽 폼 -->
                    <div class="right-join">
                        <label>
                            직급
                            <label class="point">*</label>
                            <br>
                            <select name="jobCode"  class="input-contain" required>
                                <c:forEach items="${jobVoList}" var="jobVo">
                                    <option value="${jobVo.jobCode}">${jobVo.jobName}</option>
                                </c:forEach>
                            </select>
                        </label>

                        <label>
                            주민등록번호
                            <label class="point">*</label>
                            <br>
                            <input type="text" class="input-inNum" id="idNumFront" placeholder="앞자리" maxlength="6" required> -
                            <input type="password" class="input-inNum" id="idNumBack" placeholder="뒷자리" maxlength="7" required>
                            <input type="hidden" name="idNum" id="idNum"> <!-- 합친 주민등록번호를 저장 -->
                        </label>

                        <div>
                            <label for="new-password">
                                비밀번호
                                <label class="point">*</label>
                            </label>
                            <br>
                                <div class="password-wrapper">
                                    <input type="password" class="input-contain" name="pwd" id="new-password" placeholder="비밀번호를 입력해주세요">
                                    <button type="button" class="toggle-btn" onclick="togglePassword();">보기</button>
                                </div>
                        </div>

                        <label>
                            이메일
                            <label class="point">*</label>
                            <br>
                            <input type="email" class="input-contain" name="email" placeholder="이메일을 입력하세요" required>
                        </label>

                        <label id="label-gender">
                            성별
                            <br>
                            여 <input type="radio" name="gender" value="F" required>
                            남 <input type="radio" name="gender" value="M" required>
                        </label>
                    </div>

                    <!-- 버튼 -->
                    <div class="join-buttons">
                        <button type="button" onclick="location.href='/admin/home'" id="cancel">취소</button>
                        <button type="submit" id="enroll">저장</button>
                    </div>
                </div>
            </form>
        </div>
    </main>
</body>
</html>