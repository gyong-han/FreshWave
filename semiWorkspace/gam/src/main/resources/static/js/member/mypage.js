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


function checkDupNick(){
    // 제출 버튼 가져오기
    const submitBtn = document.querySelector("form input[type=submit]");
    const nick = document.querySelector("input[name=nick]").value;

    // 서버한테 아이디 넘기기
    $.ajax({
        url : "/member/dup-nick" ,
        method : "POST" ,
        data : {
            nick : nick
        } ,
        success : function(x){
            console.log("통신성공 ~~~");
            const o = JSON.parse(x);
            if(o.status === "good"){
                alert(`${o.data}는 사용 가능한 닉네임`);
                submitBtn.removeAttribute("disabled");
            }else{
                alert(`${o.data}는 사용 불가한 닉네임`);
                submitBtn.setAttribute("disabled" , 'true');
            }
        } ,
        fail : function(){
            console.log("통신실패 ...");
        } ,
    });


}

// 1. 비밀번호 입력창 정보 가져오기
let elInputPassword = document.querySelector('#password'); // input#password
// 2. 비밀번호 확인 입력창 정보 가져오기
let elInputPasswordRetype = document.querySelector('#password-retype'); // input#password-retype
// 3. 실패 메시지 정보 가져오기 (비밀번호 불일치)
let elMismatchMessage = document.querySelector('.mismatch-message'); // div.mismatch-message.hide
// 4. 실패 메시지 정보 가져오기 (8글자 이상, 영문, 숫자, 특수문자 미사용)
let elStrongPasswordMessage = document.querySelector('.strongPassword-message'); // div.strongPassword-message.hide

function strongPassword (str) {
  return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(str);
}

function isMatch (password1, password2) {
  return password1 === password2;
}

elInputPassword.onkeyup = function () {

  // console.log(elInputPassword.value);
  // 값을 입력한 경우
  if (elInputPassword.value.length !== 0) {
    if(strongPassword(elInputPassword.value)) {
      elStrongPasswordMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
    }
    else {
      elStrongPasswordMessage.classList.remove('hide'); // 실패 메시지가 보여야 함
    }
  }
  // 값을 입력하지 않은 경우 (지웠을 때)
  // 모든 메시지를 가린다.
  else {
    elStrongPasswordMessage.classList.add('hide');
  }
};

elInputPasswordRetype.onkeyup = function () {

  // console.log(elInputPasswordRetype.value);
  if (elInputPasswordRetype.value.length !== 0) {
    if(isMatch(elInputPassword.value, elInputPasswordRetype.value)) {
      elMismatchMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
    }
    else {
      elMismatchMessage.classList.remove('hide'); // 실패 메시지가 보여야 함
    }
  }
  else {
    elMismatchMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
  }
};

function disableSubmitBtn(){
    const submitBtn = document.querySelector("form input[type=submit]");
    submitBtn.setAttribute("disabled" , "true");
}

const pwdInputTag = document.querySelector("form input[name=pwd]");
const pwdResult = document.querySelector("#pwd-result");
pwdInputTag.addEventListener("input" , function(e){
    if(e.target.value.length >= 4){
        pwdResult.classList.add("color-blue");
        pwdResult.classList.remove("color-red");
    }else{
        pwdResult.classList.add("color-red");
        pwdResult.classList.remove("color-blue");
    }
});

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