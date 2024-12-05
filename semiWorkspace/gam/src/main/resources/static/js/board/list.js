function paintPageArea(pvo){
    
    const pageArea = document.querySelector(".page-area");
    pageArea.innerHTML = "";

    //이전 버튼
    if(pvo.startPage != 1){
        const btnTag = document.createElement("button");
        btnTag.innerText = "<";
        btnTag.setAttribute("onclick" , `return loadBoardList(${pvo.startPage - 1});`);
        pageArea.appendChild(btnTag);
    }

    //페이지 버튼
    for(let i = pvo.startPage; i <= pvo.endPage; i++){
        const btnTag = document.createElement("button");
        btnTag.setAttribute("href" , `/board/list?pno=${i}`);
        btnTag.innerText = i;
        btnTag.setAttribute("onclick" , `return loadBoardList(${i});`);
        pageArea.appendChild(btnTag);
    }

    //다음 버튼
    if(pvo.endPage != pvo.maxPage){
        const btnTag = document.createElement("button");
        btnTag.innerText = ">";
        btnTag.setAttribute("onclick" , `return loadBoardList(${pvo.endPage + 1});`);
        pageArea.appendChild(btnTag);
    }
}

function loadBoardList(pno){
    //데이터 가져오기 (searchType , searchValue)
    const searchType = document.querySelector("select[name=searchType]").value;
    const titleTagValue = document.querySelector("input[name=searchValue]").value;
    const nickTagValue = document.querySelector("input[name=searchValue]").value;
    let searchValue = "";
    if(searchType == "title"){
        searchValue = titleTagValue;
    }else{
        searchValue = nickTagValue;
    }

    // tbody 내용 채우기
    const tbodyTag = document.querySelector("main table > tbody");

    // url 에서 pno 가져오기
    console.log("pno : " , pno);
    console.log("!pno : " , !pno);
    if(!pno){
        console.log("if 통과함 ~~~");
        const url = new URL(location);
        pno = url.searchParams.get("pno");
        if(pno == null){
            pno = 1;
        }
    }
    

    // ajax
    $.ajax({
        url : `/board/list/data?pno=${pno}`,
        data : {
            searchType , 
            searchValue ,
        },
        success : function(x){
            const boardVoList = x.a;
            const pvo = x.b;
            paintPageArea(pvo);

            console.log(boardVoList);
            

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

loadBoardList();

function submitSearchForm(){
    loadBoardList();
    return false;
}
    
