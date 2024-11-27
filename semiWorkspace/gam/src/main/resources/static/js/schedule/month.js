document.addEventListener('DOMContentLoaded', function() {
    let calendarId = document.querySelector('#calendar');
    let calendar = new FullCalendar.Calendar(calendarId, {

        height : '800px',                        // calendar 높이 설정
        width : '1000px',                        // calendar 넓이 설정
        expandRows : true,                       // 화면에 맞게 높이 재설정
        slotMinTime : '00:00',                   // Day 캘린더에서 시작 시간
        slotMaxTime : '23:59',                   // Day 캘린더에서 종료 시간

        timeZone: 'UTC',                    // 타임존 설정
        headerToolbar: {
            left : 'dayGridMonth,dayGridWeek,dayGridDay',
            center : 'title',
            right : 'today prev,next'
          },                                 // 버튼 눌러서 월, 주, 일 이동(현재, 이전, 다음 이동)
        initialView : 'dayGridMonth',        // 기본 화면 설정(month)
        editable : true,                     // drag & drop on
        allDay : false,                      // 하루종일 설정(기본으로 탑재 여부)
        navlink : true,                      // 상세보기 들어가게 해주는 기능
        selectable : true,                   // 여러 날짜 선택 가능하게
        events: 'https://fullcalendar.io/api/demo-feeds/events.json'
    });
    calendar.render();
});
