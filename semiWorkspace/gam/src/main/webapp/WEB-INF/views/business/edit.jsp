<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Business Edit</title>
            <link rel="stylesheet" href="/css/business/edit.css">
            <script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
            <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fcd1d93cc41be251252d5ca5e6380217&libraries=services"></script>
            <script defer src="/js/business/edit.js"></script>
        </head> 

        <body>
            <%@ include file="/WEB-INF/views/common/header.jsp" %>
                <main class="main-container">
                    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
                        <div class="content-wrapper">
                            <div></div> <!--공란-->
                            <div>
                                <form action="/business/edit?no=${vo.no}" method="post" enctype="multipart/form-data" class="detail-area">
                                    <div id="detail-main">
                                        <div>
                                            <h1>${vo.name}</h1>
                                        </div>
                                        <div>
                                            <h4>등록일 : ${vo.enrollDate}</h4>
                                        </div>
                                        <div id="map" style="width:450px;height:350px;"></div>
                                        <div class="table-div">
                                            <table class="table-area">
                                                <tr>
                                                    <th>대표자명</th>
                                                    <td><input type="text" value="${vo.ceo}" name="ceo"></td>
                                                </tr>
                                                <tr>
                                                    <th>대표자 전화번호</th>
                                                    <td><input type="number" value="${vo.phone}" name="phone"
                                                            oninput="if(this.value.length > 11) this.value = this.value.slice(0, 11);"><br>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>사업자 등록번호</th>
                                                    <td>&nbsp${vo.brn}</td>
                                                </tr>
                                                <tr>
                                                    <th>업태</th>
                                                    <td>&nbsp${vo.btCode}</td>
                                                </tr>
                                                <tr>
                                                    <th>담당부서</th>
                                                    <td>&nbsp${vo.deptName}</td>
                                                </tr>
                                                <tr>
                                                    <th>담당자</th>
                                                    <td>&nbsp${vo.managerName}</td>
                                                </tr>
                                                <tr>
                                                    <th>계약 시작일</th>
                                                    <td><input type="date" value="${vo.startDate}" name="startDate">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>계약 종료일</th>
                                                    <td><input type="date" value="${vo.endDate}" name="endDate">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>주소</th>
                                                    <td>
                                                        <input type="text" id="sample4_roadAddress" value="${vo.address}">
                                                        <input type="button" onclick="sample4_execDaumPostcode()" value="주소 찾기" id="search-address"><br>
                                                        <input type="text" id="sample4_detailAddress" placeholder="상세주소">
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <th>첨부파일</th>
                                                    <td>
                                                        <label for="f" class="custom-file-button" id="file-label">파일 선택</label>
                                                        <input type="file" name="ff" id="f">
                                                        <span id="fileName">${vo.originName}</span>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div></div><!--공란-->
                                    <div id="edit-delete-btn">
                                        <button type="button" onclick="location.href='/business/detail?no=${vo.no}'"id="cancel">취소</button>
                                        <input type="submit" value="수정" onclick="combineAddress();" id="save">
                                        <input type="hidden" id="address" value="${vo.address}" name="address">
                                        <input type="hidden" id="name" value="${vo.name}">
                                        <input type="hidden" id="no" value="${vo.no}">
                                    </div>
                                </form>
                            </div>
                            <div></div> <!--공란-->
                        </div>

                </main>
                <hidden input type="text" id="sample4_postcode" placeholder="우편번호"><br>
                    <hidden input type="text" id="sample4_jibunAddress" placeholder="지번주소">
                        <hidden span id="guide" style="color:#999;display:none"></span>
                            <hidden input type="text" id="sample4_extraAddress" placeholder="참고항목"><br>

        </body>


        </html>