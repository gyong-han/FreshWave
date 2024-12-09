<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Store Insert</title>
        <link rel="stylesheet" href="/css/store/insert.css">
        <script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        <script defer src="/js/store/insert.js"></script>

    </head>

    <body>
        <%@ include file="/WEB-INF/views/common/header.jsp" %>
            <main class="main-container">
                <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
                    <div class="content-wrapper">
                        <div></div>
                        <form class="insert-form" action="/store/insert" method="post" enctype="multipart/form-data">
                            <div>
                                <label id="bu-label">가맹점명</label><br>
                                <input type="text" placeholder="가맹점명을 입력해주세요." name="name">
                            </div>
                            <div>
                                <label id="bu-label">영업상태</label><br>
                                <select name="status">
                                    <option value="영업 준비중">영업 준비중</option>
                                    <option value="영업중">영업중</option>
                                    <option value="휴업중">휴업중</option>
                                    <option value="폐업">폐업</option>
                                </select>
                            </div>
                            <div>
                                <label id="bu-label">사업자 등록번호</label><br>
                                <input type="number" placeholder="01212312345(-없이 입력)" name="brn"
                                    oninput="if(this.value.length > 10) this.value = this.value.slice(0, 10);">
                            </div>
                            <div>
                                <label id="bu-label">가맹점 전화번호</label><br>
                                <input type="number" placeholder="021234567(-없이 입력)" name="phone"
                                    oninput="if(this.value.length > 11) this.value = this.value.slice(0, 11);">
                            </div>
                            <div>
                                <label id="bu-label">가맹점장</label><br>
                                <input type="text" placeholder="홍길동" name="ceo">
                            </div>
                            <div>
                                <label id="bu-label">가맹점장 전화번호</label><br>
                                <input type="number" placeholder="01012345678(-없이 입력)" name="ceoPhone"
                                    oninput="if(this.value.length > 11) this.value = this.value.slice(0, 11);">
                            </div>
                            <div>
                                <label id="bu-label">교육일자</label><br>
                                <input type="date" name="eduDate">
                            </div>
                            <div>
                                <label id="bu-label">영업 시작일</label><br>
                                <input type="date" name="openDate">
                            </div>
                            <div>
                                <label id="bu-label">계약 시작일</label><br>
                                <input type="date" name="startDate">
                            </div>
                            <div>
                                <label id="bu-label">계약 종료일</label><br>
                                <input type="date" name="endDate">
                            </div>
                            <div>
                                <input type="text" id="sample4_roadAddress" placeholder="주소"><br>
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
                        <hidden input type="text" id="sample4_extraAddress" placeholder="참고항목">
    </body>

    </html>