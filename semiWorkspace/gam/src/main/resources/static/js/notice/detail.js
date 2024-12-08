function imgOpen(changeName){
    Swal.fire({
      imageUrl: `/img/notice/attachment/${changeName}`,
      imageHeight: 700,
      imageWidth : 700,
      confirmButtonText: "닫기"
    });
  }


  function noticeDelete(bno) {

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
            url : "/notice/del",
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
                    location.href = `/notice/detail?bno=${bno}`;
                } );
            }else{
                Swal.fire({
                    title: "삭제 완료!",
                    icon: "success",
                    confirmButtonText: "확인"
                })
                .then( () => {
                    location.href = "/notice/list";
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

  