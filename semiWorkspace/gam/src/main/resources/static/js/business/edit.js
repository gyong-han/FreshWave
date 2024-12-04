function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
        }
    }).open();
}

function combineAddress() {
    const roadAddress = document.getElementById("sample4_roadAddress").value;
    const detailAddress = document.getElementById("sample4_detailAddress").value;
    
    let addressTag = document.querySelector("#address");
    if(!detailAddress){
        addressTag.value = roadAddress;
        console.log(addressTag.value);
        
    }else{
        addressTag.value = roadAddress + ", " + detailAddress;
        console.log(addressTag.value);
    }
}

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

    const addressTag = document.querySelector("#sample4_roadAddress").value;
    
    
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

document.querySelector("#f").addEventListener('change', function() {
    const fileTag = document.querySelector("#f");
    const labelTag = document.querySelector("#file-label");
    const spanTag = document.querySelector("#fileName");

    fileTag.style.display = "inline";
    fileTag.setAttribute("name","f");
    labelTag.style.display = "none";
    spanTag.style.display = 'none';
  });