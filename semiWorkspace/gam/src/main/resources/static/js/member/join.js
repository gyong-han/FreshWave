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
    addressTag.value = roadAddress + ", " + detailAddress;

    //주민번호 합치기
     const front = document.getElementById('idNumFront').value;
     const back = document.getElementById('idNumBack').value;
     document.getElementById('idNum').value = front + back; // 합쳐서 hidden input에 저장
}
combineAddress();

function togglePassword() {
  // 고유한 id를 사용하여 요소 선택
  const passwordField = document.getElementById('new-password');
  const toggleButton = document.querySelector('.toggle-btn');

  // 비밀번호 표시/숨기기 토글
  if (passwordField.type === 'password') {
      passwordField.type = 'text'; // 비밀번호 표시
      toggleButton.textContent = '숨기기';
  } else {
      passwordField.type = 'password'; // 비밀번호 숨기기
      toggleButton.textContent = '보기';
  }
}