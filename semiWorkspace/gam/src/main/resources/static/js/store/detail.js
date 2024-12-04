function searchlocation(){
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    // 지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();

    const addressTag = document.querySelector("#address").value;

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(addressTag, function(result, status) {

        // 정상적으로 검색이 완료됐으면
         if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });

            const nameTag = document.querySelector("#name").value;            
            
            // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
                content: `<div style="width:150px;text-align:center;padding:6px;">${nameTag}</div>`
            });
            infowindow.open(map, marker);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        }
    });
}
searchlocation();

function storeDelete(bno) {
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
        url : "/store/delete",
        data :{
          bno
        },
        success : function(x){
          if(x == 1){
            Swal.fire({
              title: "삭제 완료!",
              icon: "success",
              confirmButtonText: "확인"
            })
            .then( () => {
              location.href = "/store/list";
            } );
          }else{
            Swal.fire({
              title: "삭제 실패...",
              icon: "error",
              confirmButtonText: "확인"
            })
            .then( () => {
              location.href = `/store/detail?no=${no}`;
            } );
          }
        },
        error : function(x){
        }
      })
    }
  });
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

function imgOpen(changeName){
  Swal.fire({
    imageUrl: `/img/store/attachment/${changeName}`,
    imageHeight: 700,
    imageWidth : 700,
    confirmButtonText: "닫기"
  });
}