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

</head>
<body>
    <div class="join-contain">
        <form action="/member/join" method="post" onsubmit="combineAddress()">
            <!-- 부서 선택 -->
            <div class="left-join">
                <label>
                    부서<br>
                    <select name="deptCode" required>
                        <c:forEach items="${deptVoList}" var="deptVo">
                            <option value="${deptVo.deptCode}">${deptVo.deptName}</option>
                        </c:forEach>
                    </select>
                </label>
                <br><br>

                <!-- 이름 -->
                <label>
                    이름<br>
                    <input type="text" name="name" placeholder="이름을 입력하세요" required>
                </label>
                <br><br>

                <!-- 전화번호 -->
                <label>
                    전화번호<br>
                    <input type="tel" name="phone" placeholder="01012345678" required>
                </label>
                <br><br>

                <!-- 닉네임 -->
                <label>
                    닉네임<br>
                    <input type="text" name="nick" placeholder="닉네임을 입력하세요">
                </label>
                <br><br>

                <!-- 주소 -->
                <label>
                    주소<br>
                    <input type="text" id="sample4_roadAddress" name="roadAddress" placeholder="주소" size="30">
                    <input type="button" onclick="sample4_execDaumPostcode()" value="주소 찾기"><br>
                    <input type="text" id="sample4_detailAddress" name="detailAddress" placeholder="상세주소"><br>

                    <input type="hidden" id="address" name="address">

                    <hidden input type="text" id="sample4_postcode" placeholder="우편번호"><br>
                    <hidden input type="text" id="sample4_jibunAddress" placeholder="지번주소">
                    <hidden span id="guide" style="color:#999;display:none"></span>
                    <hidden input type="text" id="sample4_extraAddress" placeholder="참고항목">
                </label>
                <br><br>
            </div>

            <!-- 직급 선택 -->
            <div class="right-join">
                <label>
                    직급<br>
                    <select name="jobCode" required>
                         <c:forEach items="${jobVoList}" var="jobVo">
                            <option value="${jobVo.jobCode}">${jobVo.jobName}</option>
                         </c:forEach>
                    </select>
                </label>
                <br><br>

                <!-- 주민등록번호 -->
                <label>
                    주민등록번호<br>
                    <input type="text" id="idNumFront" placeholder="앞자리" maxlength="6" required> -
                    <input type="password" id="idNumBack" placeholder="뒷자리" maxlength="7" required>
                    <input type="hidden" name="idNum" id="idNum"> <!-- 합친 주민등록번호를 저장 -->
                </label>
                <br><br>

                <!-- 비밀번호 -->
                <label>
                    비밀번호<br>
                    <input type="password" name="pwd" placeholder="비밀번호를 입력하세요" required>
                </label>
                <br><br>

                <!-- 이메일 -->
                <label>
                    이메일<br>
                    <input type="email" name="email" placeholder="이메일을 입력하세요" required>
                    <button type="button">중복 확인</button>
                </label>
                <br><br>

                <!-- 성별 -->
                <label>
                    성별<br>
                    여 <input type="radio" name="gender" value="F" required>
                    남 <input type="radio" name="gender" value="M" required>
                </label>
                <br><br>
            </div>

            <!-- 저장 및 취소 버튼 -->
            <div>
                <button type="button" onclick="history.back()">취소</button>
                <button type="submit">저장</button>
            </div>
        </form>
    </div>
</body>
</html>
