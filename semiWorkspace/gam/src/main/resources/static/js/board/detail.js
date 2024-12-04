function writeComment(boardNo){
    const commentContent = document.querySelector("#comment-write-area input[name=content]").value;
    console.log(commentContent);
    
    $.ajax({
        url : "comment/write",
        method : "post",
        data : {"content" : commentContent,
            boardNo,
        },
        success : function(x){
            console.log("통신 성공.");
            if(x == 1){
                alert("댓글 작성 완료");
                loadComment(x);
            }else{
                alert("댓글 작성 실패");
            }
        },
        error : function(){
            console.log("통신 실패.");
        },
    });
}

function loadComment(){
    const boardNo = document.querySelector("#comment-list-area").getAttribute("boardNo");
    console.log(boardNo);
    
    $.ajax({
        url : "/board/comment/list",
        method : "get",
        data : {
            boardNo
        },
        success : function(x){
            console.log("통신 성공.");
            paintCommentList(x);
        },
        error : function(){
            console.log("통신 실패.");
            
        },
    });
}

function paintCommentList(voList){
    const commentListArea = document.querySelector("#comment-list-area");
    commentListArea.innerHTML = "";

    for(const vo of voList){
        const commmentDiv = document.createElement("div");

        const div01 = document.createElement("div");
        div01.innerText = vo.nick;

        const div02 = document.createElement("div");
        div02.innerText = vo.content;

        const div03 = document.createElement("div");
        div03.innerText = vo.enrollDate;

        const deleteButton = document.createElement("img");

        deleteButton.src = "/img/icon/material-symbols-light_close.svg";
        deleteButton.alt = "삭제 버튼";
        deleteButton.style.cursor = "pointer";
        deleteButton.width = 24;
        deleteButton.height = 24;
        deleteButton.classList.add("delete-btn");
        deleteButton.onclick = function(){
            deleteComment(vo.no);
            console.log(vo.no);
            
        };

        commentListArea.appendChild(div01);
        commentListArea.appendChild(div02);
        commentListArea.appendChild(div03);
        commentListArea.appendChild(deleteButton);

        commentListArea.appendChild(commmentDiv);
    }
}

function deleteComment(no){
    const result = confirm("해당 댓글 삭제하시겠습니까?");
    if(result == false){
        return;
    }
    console.log(no);
    
    $.ajax({
        url : "/board/comment/del",
        method : "post",
        data : {
            no : no
        },
        success : function(x){
            alert("댓글이 삭제되었습니다.");
            loadComment(x);
        },
        error : function(){
            alert("통신 실패...");
        },
    });
}

loadComment();

