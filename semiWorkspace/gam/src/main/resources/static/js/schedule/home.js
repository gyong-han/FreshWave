let calendar;
let evtVoList = [];

$.ajax({
    url : '/schedule/event',
    method : 'POST',
    success : function(data){
        if(data != null){
            console.log("이벤트리스트 보여줘 :" , data);

            evtVoList = data;
            calendarEvt();
        }
        // setTimeout(calendarEvt, 1000)
    },
    error : function(){
        console.log("통신 실패-_-");
    }
});

function calendarEvt() {
    let calendarId = document.querySelector('#calendar');
    calendar = new FullCalendar.Calendar(calendarId, {

        expandRows : true,                       // 화면에 맞게 높이 재설정
        slotMinTime : '00:00',                   // Day 캘린더에서 시작 시간
        slotMaxTime : '23:59',                   // Day 캘린더에서 종료 시간

        customButtons: {
            addSchedule: {
                text: "일정 추가",
                click: function () {
                    addEventBtn();
                },
            },
        },
        headerToolbar: {
            left : 'dayGridMonth,timeGridWeek,timeGridDay',
            center : 'title',
            right : 'prev,today,next addSchedule'
          },                                 // 버튼 눌러서 월, 주, 일 이동(현재, 이전, 다음 이동)
        initialView : 'dayGridMonth',        // 기본 화면 설정(month)
        stickyHeaderDates: true,             // 헤더 요일 고정
        editable : true,                     // drag & drop on
        allDaySlot : false,                   // 하루종일 설정(기본으로 탑재 여부)
        navLinks : true,                      // 상세보기 들어가게 해주는 기능
        selectable : true,                   // 날짜 선택 가능하게
        selectMirror: true,                  // 날짜 선택 미리보기
        dayMaxEvents: true,                  // 이벤트 너무 많으면 더보기 뜨게

        events : evtVoList,

        // 중요도에 따른 색 지정
        
        

        // 날짜 클릭했을 때 실행(새 일정 추가)
        dateClick : function(info){
            addEventToCalendar(calendar, info);
        },

        // 이벤트 클릭했을 때 실행(상세조회)
        eventClick : function(info){
            detailEvent(calendar, info);
        },

        // 선택했을 때 실행
        select : function(info){

        },

        // 이벤트 이동할 때 실행(날짜 수정)
        eventDrop : function(event, delta, revertFunc, jsEvent, ui, view){
            updateEvent(event, revertFunc);
        },

        // 이벤트 사이즈 조절 할 때 실행(시간 수정)
        eventResize : function(){

        },

        eventDidMount : function(evt){
            console.log("이벤트", evt);

        }

    });

    calendar.render();
};

// 일정 상세보기 함수
function detailEvent(calendar, info){
    const sno = info.event.id;

    const dbody = document.querySelector('#detail-body');
    $.ajax({
        url : '/schedule/detail',
        method : 'GET',
        data : {
            sno
        },
        success : function(data) {
            let detailModal = new bootstrap.Modal(document.querySelector('#detailModal'));
            const modalDiv = document.querySelector('#detailModal');

            modalDiv.setAttribute("sno" , sno);

            // title
            if(document.querySelector('#titleDiv')){
                const divTag1 = document.querySelector('#titleDiv')
                divTag1.remove();
            }
            const titleDiv = document.createElement('div');
            titleDiv.setAttribute('id', 'titleDiv');
            titleDiv.innerText = '일정제목 : '+ data.title;
            dbody.appendChild(titleDiv);

            // startDate
            if(document.querySelector('#startDateDiv')){
                const divTag1 = document.querySelector('#startDateDiv')
                divTag1.remove();
            }
            const startDateDiv = document.createElement('div');
            startDateDiv.setAttribute('id', 'startDateDiv');
            startDateDiv.innerText = data.startDate;
            dbody.appendChild(startDateDiv);

            // startTime
            if(document.querySelector('#startTimeDiv')){
                const divTag1 = document.querySelector('#startTimeDiv')
                divTag1.remove();
            }
            const startTimeDiv = document.createElement('div');
            startTimeDiv.setAttribute('id', 'startTimeDiv');
            startTimeDiv.innerText = data.startTime;
            dbody.appendChild(startTimeDiv);

            // finishDate
            if(document.querySelector('#finishDateDiv')){
                const divTag1 = document.querySelector('#finishDateDiv')
                divTag1.remove();
            }
            const finishDateDiv = document.createElement('div');
            finishDateDiv.setAttribute('id', 'finishDateDiv');
            finishDateDiv.innerText = data.finishDate;
            dbody.appendChild(finishDateDiv);

            // finishTime
            if(document.querySelector('#finishTimeDiv')){
                const divTag1 = document.querySelector('#finishTimeDiv')
                divTag1.remove();
            }
            const finishTimeDiv = document.createElement('div');
            finishTimeDiv.setAttribute('id', 'finishTimeDiv');
            finishTimeDiv.innerText = data.finishTime;
            dbody.appendChild(finishTimeDiv);          

            // location
            if(document.querySelector('#locationDiv')){
                const divTag1 = document.querySelector('#locationDiv')
                divTag1.remove();
            }
            const locationDiv = document.createElement('div');
            locationDiv.setAttribute('id', 'locationDiv');
            locationDiv.innerText = '위치 : '+ data.location;
            dbody.appendChild(locationDiv);

            // priority
            if(document.querySelector('#priorityDiv')){
                const divTag1 = document.querySelector('#priorityDiv')
                divTag1.remove();
            }
            const priorityDiv = document.createElement('div');
            priorityDiv.setAttribute('id', 'priorityDiv');
            priorityDiv.innerText = '중요도 :'+ data.priorityName;
            dbody.appendChild(priorityDiv);

            // content
            if(document.querySelector('#contentDiv')){
                const divTag1 = document.querySelector('#contentDiv')
                divTag1.remove();
            }
            const contentDiv = document.createElement('div');
            contentDiv.setAttribute('id', 'contentDiv');
            contentDiv.innerText = '내용 : ' + data.content;
            dbody.appendChild(contentDiv);


            detailModal.show();
        },
        error : function(){
            console.log("상세보기 실패");
        }
    });
};

// 일정 추가하기 함수 (버튼-페이지)
function addEventBtn(){
    document.addEventListener("click", function(){
        const addEvtBtnTag = document.querySelector('.fc-addSchedule-button fc-button fc-button-primary');
        window.location.href = "/schedule/write";
    });
};

// 일정 추가하기 (비동기-모달)
function addEventToCalendar(calendar, info){

    const titleTag = document.querySelector('.modal-body input[name=title]');
    const contentTag = document.querySelector('.modal-body textarea[name=content]');
    const startDateTag = document.querySelector('.modal-body input[name=startDate]');
    const startTimeTag = document.querySelector('.modal-body input[name=startTime]');
    const finishDateTag = document.querySelector('.modal-body input[name=finishDate]');
    const finishTimeTag = document.querySelector('.modal-body input[name=finishTime]');
    const priorityTag = document.querySelector('#priority');
    const locationTag = document.querySelector('#address');

    titleTag.value = '';
    contentTag.value = '';
    startDateTag.value = '';
    startTimeTag.value = '';
    finishDateTag.value = '';
    finishTimeTag.value = '';
    priorityTag.value = '';
    locationTag.value = '';

    let addModal = new bootstrap.Modal(document.querySelector('#addModal'));
    addModal.show();

    startDateTag.value = info.dateStr;
    finishDateTag.value = info.dateStr;
    getPriority();


    // 저장버튼 누르면 이벤트 실행
    document.querySelector('.save-btn').addEventListener('click', function(data){
        // 모달 입력 필드 값 가져오기
        const title = titleTag.value;
        const content = contentTag.value;
        const startDate = startDateTag.value;
        const startTime = startTimeTag.value;
        const finishDate = finishDateTag.value;
        const finishTime = finishTimeTag.value;
        const priority = priorityTag.value;
        const location = locationTag.value;

        // 입력 값 유효성 검사
        // 함수실행

        // 비동기 데이터 처리
        $.ajax({
            url : '/schedule/write',
            method : 'POST',
            data : {
                title,
                content,
                startDate,
                startTime,
                finishDate,
                finishTime,
                location,
                priority,
                // allDay,
            },
            success : function(){
                console.log("작성하기 매우 잘됨!! 아주 기쁜 일!!!!");
                addModal.hide();
                location.reload();
            },
            error : function(){
                console.log("작성하기 안됨....");
            },
        });
    });
};

// 일정 추가하기 - 중요도
function getPriority(){
    const priorityTemp = document.querySelector('#priority');
    priorityTemp.innerHTML = '';
    $.ajax({
        url : '/schedule/priority',
        method : 'GET',
        success : function(priorityVoList){
            const priorityTag = document.querySelector('#priority');
            for(const vo of priorityVoList){
                const optionTag = document.createElement('option');
                optionTag.setAttribute('value', vo.no);
                optionTag.innerText = vo.name;
                priorityTag.appendChild(optionTag);
            }
        },
        error : function(){
            console.log('중요도 안나옴');
        } 
    });
}


// 일정 수정하기 함수(페이지)
function changeEvent(editBtn){
    const tag = editBtn.parentNode.parentNode.parentNode.parentNode;
        const sno = tag.getAttribute("sno");

        const editTag = document.querySelector('.edit-btn');
        window.location.href = `/schedule/edit?sno=${sno}`;
}

// 일정 수정하기 비동기 처리(ajax)
function updateEvent(event, revertFunc){
    $.ajax({
        url : '/schedule/update',
        method : 'PUT',
        contentType : 'application/json',
        data : JSON.stringify({
            id : event.id,
            start : event.start.format(),
            end : event.end ? event.end.format() : null,
            allDay : event.allDay
        }),
        success : function(){
            console.log("수정하기 성공");
        },
        error : function(){
            console.log("수정하기 실패");
        }
    });
}

// 일정 삭제하기 함수
// function delEvent(delBtn){
//     const tag = delBtn.parentNode.parentNode.parentNode.parentNode;
//     const sno = tag.getAttribute("sno");

//     const result = confirm("일정을 삭제하시겠습니까?");
//         if(result == false){
//             return;
//         }
//     $.ajax({
//         url : '/schedule/del',
//         method : 'GET',
//         data : {
//             sno
//         },
//         success : function(){
//             // no로 이벤트 객체 찾기
//             const event = calendar.getEventById(sno);

//             $("#detailModal").modal('hide');
//             // const dmodal = document.querySelector('.modal-content');
//             // event.remove();
//             // detailModal.remove();
//             location.reload();
//         },
//         error : function(){
//             console.log("삭제하기 실행 안됨");
//         }
//     });
// }

// 일정 삭제하기 (swal)
function delEvent(delBtn) {
    const tag = delBtn.parentNode.parentNode.parentNode.parentNode;
    const sno = tag.getAttribute("sno");
    console.log(sno);
    
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
          url : "/schedule/del",
          data :{
            sno
          },
          success : function(x){
            if(x == 1){
              Swal.fire({
                title: "삭제 완료!",
                icon: "success",
                confirmButtonText: "확인"
              })
              .then( () => {
                location.href = "/schedule/home";
              } );
            }else{
              Swal.fire({
                title: "삭제 실패...",
                icon: "error",
                confirmButtonText: "확인"
              })
              .then( () => {
                location.href = `/schedule/home`;
              } );
            }
          },
          error : function(x){
          }
        })
      }
    });
  }

// 유효성 검사
const faildatemsg1 = document.querySelector('.faildate-msg1');
const faildatemsg2 = document.querySelector('.faildate-msg2');
const faildatemsg3 = document.querySelector('.faildate-msg3');

// 시작, 종료 날짜 유효성 검사
// if(startDate > finishDate){
//     failmsg2.classList.add
// }

// 주소
function kakaopost() {
    new daum.Postcode({
      oncomplete: function (data) {
        document.querySelector("#zipcode").value = data.zonecode;
        document.querySelector("#address").value = data.address
      }
    }).open();
  }


