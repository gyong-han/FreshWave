<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/notice/write.css">
<script type="text/javascript" src="/libs/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<title>공지사항 작성</title>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <main class="main-container">
        <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>
        <div class="area-container">
            <form action="/notice/write" method="post">
                <div class="input-checkbox-container">
                    <input type="text" name="title" placeholder="제목을 입력하세요.">
                    <label>
                        <input type="checkbox" name="urgent"> 긴급
                    </label>
                </div>
                <textarea id="smarteditor" rows="30" cols="100" style="width: 100%;" name="content"></textarea>
                <div class="bottom-button-area">
                    <button type="button" onclick="goback();">취소</button>
                    <input type="submit" value="저장" onclick="submitContents(this)">
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
            </script>



</body>
</html>