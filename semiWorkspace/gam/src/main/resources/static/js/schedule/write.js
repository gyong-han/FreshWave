// 주소
function kakaopost() {
    new daum.Postcode({
      oncomplete: function (data) {
        document.querySelector("#zipcode").value = data.zonecode;
        document.querySelector("#address").value = data.address
      }
    }).open();
  }

  // cancel
  function cancel(evt){
    console.log("evt" , evt);
    location.href='/schedule/home';
}