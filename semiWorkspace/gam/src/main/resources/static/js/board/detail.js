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
        commmentDiv.classList.add("comment-item");
        
        const brDiv = document.createElement("br");
        
        const div01 = document.createElement("div");
        div01.classList.add("comment-author");
        div01.innerText = vo.nick;

        const div02 = document.createElement("div");
        div02.classList.add("comment-content");
        div02.innerText = vo.content;

        const div03 = document.createElement("div");
        div03.classList.add("comment-date");
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
        };

        const contentRow = document.createElement("div");
        contentRow.classList.add("comment-row");
        contentRow.appendChild(div02);
        contentRow.appendChild(deleteButton);

        commentListArea.appendChild(brDiv);
        commentListArea.appendChild(div01);
        commentListArea.appendChild(contentRow);
        commentListArea.appendChild(div03);

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

function editSuccess() {
    Swal.fire({
        title: "수정완료!",
        icon: "success",
        confirmButtonColor: "#1D64F2",
        confirmButtonText: "확인"
    });
  }
  
  function editFail() {
    Swal.fire({
        title: "수정실패..",
        icon: "error",
        confirmButtonColor: "#1D64F2",
        confirmButtonText: "확인"
    })
  }

function imgOpen(changeName){
    Swal.fire({
      imageUrl: `/img/board/attachment/${changeName}`,
      imageHeight: 700,
      imageWidth : 700,
      confirmButtonText: "닫기"
    });
  }

  function boardDelete(bno){
    Swal.fire({
        title: "삭제하시겠습니까?", //alert창 문구
        icon: "question", // 그림(error,success,warning,info,question)
        showCancelButton: true, //취소버튼 여부
        confirmButtonColor: "#1D64F2",
        cancelButtonColor: "#121212",
        confirmButtonText: "삭제", //취소 버튼 문구
        cancelButtonText : "취소", //확인 버튼 문구
        reverseButtons: true //취소,확인버튼 좌우 반전
      })
      .then((result) => {
        if (result.isConfirmed) {
          $.ajax({
            url : "/board/del",
            data :{
              bno
            },
            success : function(x){
              if(x == 1){
                Swal.fire({
                    title: "삭제 실패...",
                    icon: "error",
                    confirmButtonText: "확인"
                })
                .then( () => {
                    location.href = `/board/detail?bno=${bno}`;
                } );
            }else{
                Swal.fire({
                    title: "삭제 완료!",
                    icon: "success",
                    confirmButtonText: "확인"
                })
                .then( () => {
                      location.href = "/board/list";
                } );
      
              }
            },
            error : function(x){
            }
          })
        }
      })
      ;
  }

