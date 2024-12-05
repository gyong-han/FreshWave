<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Business Insert</title>
        <link rel="stylesheet" href="/css/business/insert.css">
        <script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        <script defer src="/js/business/insert.js"></script>

    </head>

    <body>
        <%@ include file="/WEB-INF/views/common/header.jsp" %>
            <main class="main-container">
                <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
                    <div class="content-wrapper">
                        <div></div>
                        <form class="insert-form" action="/business/insert" method="post" enctype="multipart/form-data">
                                <div>
                                    <label id="bu-label">거래처명</label><br>
                                    <input type="text" placeholder="거래처명을 입력해주세요." name="name">
                                </div>
                                <div>
                                    <label id="bu-label">사업자 등록번호</label><br>
                                    <input type="number" placeholder="01212312345(-없이 입력)" name="brn" oninput="if(this.value.length > 10) this.value = this.value.slice(0, 10);">
                                </div>
                                <div>
                                    <label id="bu-label">대표자명</label><br>
                                    <input type="text" placeholder="홍길동" name="ceo">
                                </div>
                                <div>
                                    <label id="bu-label">대표자 전화번호</label><br>
                                    <input type="number" placeholder="01012345678(-없이 입력)" name="phone" oninput="if(this.value.length > 11) this.value = this.value.slice(0, 11);">
                                </div>
                                <div>
                                    <label id="bu-label">업태</label><br>
                                    <input list="Bt_Code" id="input_Bt_Code" name="btCode"/>
                                    <datalist id="Bt_Code">
                                        <c:forEach items="${BtCodeList}" var="BtCode">
                                            <option value="${BtCode.code}">${BtCode.name}</option>
                                        </c:forEach>
                                    </datalist>
                                </div>
                                <div>
                                    <label id="bu-label">담당부서</label><br>
                                    <select name="deptName">
                                        <option value="영업부">영업부</option>
                                        <option value="관리부">관리부</option>
                                        <option value="마케팅부">마케팅부</option>
                                        <option value="개발부">개발부</option>
                                        <option value="CS부">CS부</option>
                                        <option value="생산부">생산부</option>
                                        <option value="인사부">인사부</option>
                                        <option value="재무부">재무부</option>
                                        <option value="총무부">총무부</option>
                                        <option value="기획부">기획부</option>
                                        <option value="법무부">법무부</option>
                                        <option value="홍보부">홍보부</option>
                                        <option value="IT부">IT부</option>
                                        <option value="물류부">물류부</option>
                                    </select>
                                </div>
                                <div>
                                    <label id="bu-label">계약 시작일</label><br>
                                    <input type="date" placeholder="2024-12-18" name="startDate">
                                </div>
                                <div>
                                    <label id="bu-label">계약 종료일</label><br>
                                    <input type="date" placeholder="2024-12-18" name="endDate">
                                </div>
                                <div class="address">
                                    <label id="bu-label">주소</label><br>
                                    <input type="text" id="sample4_roadAddress" placeholder="주소">
                                    <input type="text" id="sample4_detailAddress" placeholder="상세주소">
                                </div>
                                <div>
                                    <input type="button" onclick="sample4_execDaumPostcode()" value="주소 찾기"
                                        id="search-address">
                                </div>
                                <div id="btn-area">
                                    <input type="file" name="f" id="file">
                                </div>
                                <div id="btn-area">
                                    <input type="button" value="취소" id="cancel" onclick="location.href='/business/list'">
                                    <input type="submit" value="등록" id="enroll" onclick="combineAddress();">
                                    <input type="hidden" id="address" name="address">
                                </div>
                        </form>
                        <div></div>
                    </div>

                    
            </main>
            

                    <hidden input type="text" id="sample4_postcode" placeholder="우편번호"><br>
                        <hidden input type="text" id="sample4_jibunAddress" placeholder="지번주소">
                            <hidden span id="guide" style="color:#999;display:none"></span>
                                <hidden input type="text" id="sample4_extraAddress" placeholder="참고항목"><br>
    </body>

    </html>