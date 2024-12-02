$(document).ready(function(){
    $('#sortDept').on('click', function(){
        //정렬 컬럼
        const column = $(this).data('column');
        //현재 정렬 방향
        let order = $(this).data('order');

        // 정령 방향 토글
        order = order === 'asc' ? 'desc' : 'asc';
        $(this).data('order', order);

        // 아이콘 변경
        $(this).text(order === 'asc' ? '부서 ▲' : '부서 ▼');

        // 서버 요청
        const query = `?sortColumn=${column}&sortOrder=${order}`;
        window.location.href = `/notice/list${query}`;
    });
});