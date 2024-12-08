function paintPageArea(pvo){
    const pageArea = document.querySelector('.page-area');
    pageArea.innerHTML = '';

    // 이전버튼
    if(pvo.startPage != 1){
        const preTag = document.createElement('button');
        preTag.setAttribute('class', 'page-btn');
        preTag.setAttribute('onclick', `return loadReservationList(${pvo.startPage-1});`);
        preTag.innerText = '<';
        pageArea.appendChild(preTag);
    }

    // 페이지 버튼
    for(let i = pvo.startPage; i <= pvo.endPage; i++){
        const btnTag = document.createElement('button');
        btnTag.setAttribute('class', 'page-btn');
        btnTag.setAttribute('onclick', `loadReservationList(${i});`);
        btnTag.innerText = i;
        pageArea.appendChild(btnTag);
    }

    // 다음버튼
    if(pvo.endPage != pvo.maxPage){
        const nextTag = document.createElement('button');
        nextTag.setAttribute('class', 'page-btn');
        nextTag.setAttribute('onclick', `return loadReservationList(${pvo.endPage+1});`)
        nextTag.innerText = '>';
        pageArea.appendChild(nextTag);
    }
}

function loadReservationList(rno){
    const tbodyTag = document.querySelector('.list-table tbody');
    
    //searchType, searchValue 준비
    const searchType = document.querySelector('select[name=searchType]').value;
    const nameValue = document.querySelector('input[name=searchValue]').value;

    let searchValue = '';


    // rno
    if(!rno){
        url = new URL(location);
        rno = url.searchParams.get("rno");
        if(rno == null){
            rno = 1;
        }
    }
    
    if(searchType == 'title'){
        searchValue = nameValue;
    }else if(searchType == 'writerName'){
        searchValue = nameValue;
    }
    
    // tbody 채우기
    $.ajax({
        url : `/reservation/list/data?rno=${rno}`,
        data : {
            searchType,
            searchValue
        },
        method : 'GET',
        success : function(x){
            const reservationVoList = x.r;
            const pvo = x.p;

            paintPageArea(pvo);
            
            tbodyTag.innerHTML = '';

            for(const vo of reservationVoList){
                const trTag = document.createElement('tr');

                const noTag = document.createElement('td');
                noTag.innerText = vo.no;
                trTag.appendChild(noTag);

                const titleTag = document.createElement('td');
                titleTag.innerText = vo.title;
                trTag.appendChild(titleTag);

                const rdateTag = document.createElement('td');
                const date = vo.rdate;
                rdateTag.innerText = date;
                trTag.appendChild(rdateTag);

                const nameTag = document.createElement('td');
                nameTag.innerText = vo.writerName;
                trTag.appendChild(nameTag);

                const enrollDateTag = document.createElement('td');
                enrollDateTag.innerText = vo.enrollDate.slice(0,10);
                trTag.appendChild(enrollDateTag);

                tbodyTag.appendChild(trTag);
            }
        },
        error : function(){
            console.log("유선문의 목록조회 실패");
            
        }
    });
    
}

loadReservationList();

function submitSearchForm(){

    loadReservationList();
    return false;
}

// 상세조회로 넘어가기
const tbodyTag = document.querySelector('.content-wrapper table tbody');

tbodyTag.addEventListener("click", function(evt){
    if(evt.target.tagName != "TD"){
        return;
    }
    const rno = evt.target.parentNode.children[0].innerText;
    console.log(rno);
    
    location.href = `/reservation/detail?rno=${rno}`;
});