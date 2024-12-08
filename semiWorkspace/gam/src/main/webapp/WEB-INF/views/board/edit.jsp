<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/libs/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<title>게시글 수정</title>
<link rel="stylesheet" href="/css/board/write.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script defer src="/js/board/edit.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="area-container">
            <form action="/board/edit" method="post" enctype="multipart/form-data">
                <h2>게시판 제목</h2>
                <input type="hidden" name="no" value="${vo.no}">
                <input type="text" name="title" placeholder="제목" value="${vo.title}">
                <br>
                <h2>게시판 내용</h2>
                <textarea id="smarteditor" rows="20" cols="100" style="width: 100%;" name="content">${vo.content}</textarea>
                <br>
                    <label for="f" class="custom-file-button" id="file-label"> 
                        <img src="/img/icon/attach-file.svg" alt="파일 첨부" class="attach-icon">
                    </label>
                    <input type="file" name="ff" id="f">
                    <span id="fileName">${vo.originName}</span>
                <div class="preview-area">
                </div>
                <div class="bottom-button-area">
                    <button type="button" onclick="goback();">취소</button>
                    <input type="submit" value="수정" onclick="submitContents(this)">
                </div>
            </form>
        </div>
    </main>
    <script>
        var editor_object  = [];
            nhn.husky.EZCreator.createInIFrame({
                oAppRef: editor_object,
                elPlaceHolder: "smarteditor",  //textarea ID 입력
                sSkinURI: "/libs/smarteditor/SmartEditor2Skin.html",  //martEditor2Skin.html 경로 입력
                fCreator: "createSEditor2",
                htParams : { 
                // 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
                bUseToolbar : true, 
                // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
                bUseVerticalResizer : false, 
                // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
                bUseModeChanger : false 
                }
            });
    
            function submitContents(elClickedObj){
                editor_object.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
                const content = document.getElementById("smarteditor").value;
                document.getElementById("form").submit();
            }

            function goback() {
                if (document.referrer) {
                    history.back(); // 이전 페이지로 이동
                } else {
                    window.location.href = "/board/detail?bno=${vo.no}"; // 기본 페이지로 이동
                }
            }
        </script>
</body>
</html>
