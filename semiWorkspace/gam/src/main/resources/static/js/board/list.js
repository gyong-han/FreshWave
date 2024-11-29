function paintPageArea(pvo){
    const pageArea = document.querySelector(".page-area");

    //이전 버튼
    if(pvo.startPage != 1){
        const aTag = document.createElement("a");
        aTag.setAttribute("href" , `/board/list?pno=${pvo.startPage-1}`);
        aTag.innerText = "<";
        pageArea.appendChild(aTag);
    }

    //페이지 버튼
    for(let i = pvo.startPage; i <= pvo.endPage; i++){
        const aTag = document.createElement("a");
        aTag.setAttribute("href" , `/board/list?pno=${i}`);
        aTag.innerText = i;
        pageArea.appendChild(aTag);
    }

    //다음 버튼
    if(pvo.endPage != pvo.maxPage){
        const aTag = document.createElement("a");
        aTag.setAttribute("href" , `/board/list?pno=${pvo.endPage + 1}`);
        aTag.innerText = ">";
        pageArea.appendChild(aTag);
    }
}

function loardBoardList(){

    // tbody 내용 채우기
    const tbodyTag = document.querySelector("main table > tbody");

    // url(pno)
    const url = new URL(location);
    let pno = url.searchParams.get("pno");
    if(pno == null){
        pno = 1;
    }

    // ajax
    $.ajax({
        url : `/board/list/data?pno=${pno}`,
        success : function(x){
            const boardVoList = x.a;
            const pvo = x.b;
            paintPageArea(pvo);

            tbodyTag.innerHTML = "";

            for(const vo of boardVoList){

                const trTag = document.createElement("tr");

                const tdTag1 = document.createElement("td");
                tdTag1.innerText = vo.no;
                trTag.appendChild(tdTag1);

                const tdTag2 = document.createElement("td");
                tdTag2.innerText = vo.title;
                trTag.appendChild(tdTag2);
                tdTag2.innerHTML = `<a href='/board/detail?bno=${vo.no}'>${vo.title}</a>`;
    

                const tdTag3 = document.createElement("td");
                tdTag3.innerText = vo.nick;
                trTag.appendChild(tdTag3);

                const tdTag4 = document.createElement("td");
                tdTag4.innerText = vo.enrollDate;
                trTag.appendChild(tdTag4);

                const tdTag5 = document.createElement("td");
                tdTag5.innerText = vo.hit;
                trTag.appendChild(tdTag5);

                tbodyTag.appendChild(trTag);
            }
        },
        fail : function(){
            alert("게시글 목록조회 실패")
        },
    });
}

loardBoardList();