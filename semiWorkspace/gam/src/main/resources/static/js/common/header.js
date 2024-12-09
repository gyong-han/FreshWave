
// const iconTag = document.querySelector(".icon");
// const blueTag = document.querySelectorAll(".bluediv");
// iconTag.addEventListener('mouseover', () => {
//     blueTag.style.visibility = "visible";
// });

// iconTag.addEventListener('mouseleave', () => {
//     blueTag.style.visibility = "hidden";
//  });


function dropdown() {
    const dropdownMenu = document.querySelector("#dropdown");
    if (dropdownMenu.style.display === "none" || dropdownMenu.style.display === "") {
        dropdownMenu.style.display = "block";
    } else {
        dropdownMenu.style.display = "none";
    }
}

function finishLogOut() {

    Swal.fire({
        title: "퇴근하시겠습니까?", //alert창 문구
        icon: "question", // 그림(error,success,warning,info,question)
        showCancelButton: true, //취소버튼 여부
        confirmButtonColor: "#1D64F2",
        cancelButtonColor: "#121212",
        confirmButtonText: "퇴근", //취소 버튼 문구
        cancelButtonText : "취소", //확인 버튼 문구
        reverseButtons: true //취소,확인버튼 좌우 반전
      })
      .then((result) => {
        if (result.isConfirmed) {
          $.ajax({
            url : "/member/logOutFinish",
            success : function(x){
              if(x == 1){
                Swal.fire({
                  title: "퇴근 완료!",
                  icon: "success",
                  confirmButtonText: "확인"
                })
                .then( () => {
                  location.href = "/member/login";
                } );
              }else{
                Swal.fire({
                  title: "퇴근 실패...",
                  icon: "error",
                  confirmButtonText: "확인"
                })
                .then( () => {
                  location.href = "/home";
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


