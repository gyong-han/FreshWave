function reservationDelete(rno) {
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
          url : "/reservation/del",
          data :{
            rno
          },
          success : function(x){
            if(x == 1){
              Swal.fire({
                title: "삭제 완료!",
                icon: "success",
                confirmButtonText: "확인"
              })
              .then( () => {
                location.href = "/reservation/list";
              } );
            }else{
              Swal.fire({
                title: "삭제 실패...",
                icon: "error",
                confirmButtonText: "확인"
              })
              .then( () => {
                location.href = `/reservation/detail?rno=${rno}`;
              } );
            }
          },
          error : function(x){
          }
        })
      }
    });
  }