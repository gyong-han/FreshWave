<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
   <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Store Detail</title>
            <link rel="stylesheet" href="/css/store/detail.css">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
            <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fcd1d93cc41be251252d5ca5e6380217&libraries=services"></script>
            <script defer src="/js/store/detail.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <script>
                document.addEventListener("DOMContentLoaded", () => {
                    <c:if test="${not empty alertNo}">
                        console.log(${alertNo});

                        <c:if test="${alertNo == 1}">
                            editSuccess();
                        </c:if>
                        <c:if test="${alertNo == 0}">
                            editFail();
                        </c:if>
                        <c:remove var="alertNo" scope="session" />
                    </c:if>
                });
            </script>
        </head>

        <body>
            <%@ include file="/WEB-INF/views/common/header.jsp" %>
                <main class="main-container">
                    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
                        <div class="content-wrapper">
                            <div></div><!--공락-->
                            <div class="detail-area">
                                <div id="detail-main">
                                    <div>
                                        <h1>${vo.name}</h1>
                                    </div>
                                    <div>
                                        <h4>등록일 : ${vo.enrollDate}</h4>
                                    </div>
                                    <div>
                                        <div id="map" style="width:450px;height:350px;"></div>
                                    </div>
                                    <div class="table-div">
                                        <table class="table-area">
                                            <tr>
                                                <th>가맹점장</th>
                                                <td>&nbsp${vo.ceo}</td>
                                            </tr>
                                            <tr>
                                                <th>가맹점 전화번호</th>
                                                <td>&nbsp${vo.phone}</td>
                                            </tr>
                                            <tr>
                                                <th>사업자 등록번호</th>
                                                <td>&nbsp${vo.brn}</td>
                                            </tr>
                                            <tr>
                                                <th>대표자 전화번호</th>
                                                <td>&nbsp${vo.ceoPhone}</td>
                                            </tr>
                                            <tr>
                                                <th>교육일</th>
                                                <td>&nbsp${vo.eduDate}</td>
                                            </tr>
                                            <tr>
                                                <th>영업 시작일</th>
                                                <td>&nbsp${vo.openDate}</td>
                                            </tr>
                                            <tr>
                                                <th>영업 종료일</th>
                                                <td>&nbsp${vo.closeDate}</td>
                                            </tr>
                                            <tr>
                                                <th>영업상태</th>
                                                <td>&nbsp${vo.status}</td>
                                            </tr>
                                            <tr>
                                                <th>계약 시작일</th>
                                                <td>&nbsp${vo.startDate}</td>
                                            </tr>
                                
                                            <tr>
                                                <th>계약 종료일</th>
                                                <td>&nbsp${vo.endDate}</td>
                                            </tr>
                                            <tr>
                                                <th>주소</th>
                                                <td>&nbsp${vo.address}</td>
                                            </tr>
                                            <tr>
                                                <th>첨부파일</th>
                                                <td><a onclick="imgOpen(`${vo.changeName}`);">&nbsp${vo.originName}</a></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div></div> <!--공락-->
                                <div class="btn-area">
                                    <div id="list-btn">
                                        <button onclick="location.href='/store/list'" id="list">목록</button>
                                    </div>
                                    <div id="edit-delete-btn">
                                        <c:if test="${loginMemberVo.id == vo.managerNo}">
                                            <button onclick="storeDelete(`${vo.no}`)" id="delete">삭제</button>
                                            <button onclick="location.href='/store/edit?no=${vo.no}'" id="edit">수정</button>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <div></div><!--공락-->
                        </div>
                </main>
                <input type="hidden" id="address" value="${vo.address}">
                <input type="hidden" id="name" value="${vo.name}">

        </body>

        </html>