function loadMemoList(){
    const tbodyTag = document.querySelector(".memo-list-area tbody");
    const hiddenTag = document.querySelector(".memo-list-area input[type=hidden]").value;



    const url = new URL(location);
    let pno = url.searchParams.get("pno");
    if(pno == null){
        pno = 1;
    }


$.ajax({
    url :   `/projectMemo/list?projectNo=${hiddenTag}&pno=${pno}` ,
    method : "post" ,
    data : {

        "key" : hiddenTag

    } ,
    success : function(m){
        console.log(m);
        const pvo = m.pvo;
        const x = m.memoList;
        paintPageArea(pvo, x)
        
        for(const vo of x){
            const trTag = document.createElement("tr");
            const tdTag1 = document.createElement("td");
            const tdTag2 = document.createElement("td");
            const tdTag3 = document.createElement("td");
            const tdTag4 = document.createElement("td");
            const tdTag5 = document.createElement("td");
            const tdTag6 = document.createElement("td");
            const inputTag = document.createElement("input");

            inputTag.type ="botton";
            inputTag.value = vo.name;  
            inputTag.setAttribute("readonly","true") 
            inputTag.onclick = function(){
                location.href=`/projectMemo/detail?projectNo=${vo.prjKey}&no=${vo.no}`;
            };
            tdTag1.className="td1";
            tdTag1.appendChild(inputTag);
            trTag.appendChild(tdTag1);
            tdTag2.innerText = vo.ing;
            trTag.appendChild(tdTag2);
            tdTag3.innerText = vo.writerName;
            trTag.appendChild(tdTag3);
            tdTag4.innerText = vo.priorityName;
            trTag.appendChild(tdTag4);
            tdTag5.innerText = vo.startDate;
            trTag.appendChild(tdTag5);
            tdTag6.innerText = vo.endDate;
            trTag.appendChild(tdTag6);

            tbodyTag.appendChild(trTag);
        }

    } ,
    error : function(){
        console.log("통신실패");
    } ,
})
}

loadMemoList();


function paintPageArea(pvo, x){
    const page = document.querySelector(".page-area");
    page.innerHTML = "";

    if(pvo.startPage != 1){
        const aTag = document.createElement("a");
        aTag.setAttribute("href" , `/projectMemo/list?projectNo=${x[0].prjKey}&pno=${pvo.startPage-1}`);
        aTag.innerText = " < ";
        page.appendChild(aTag);
    }
    
    for(let i = pvo.startPage; i <= pvo.endPage; i++){
        const aTag = document.createElement("a");
        aTag.setAttribute("href" , `/projectMemo/list?projectNo=${x[0].prjKey}&pno=${i}`);
        aTag.innerText = i;
        page.appendChild(aTag);
    }
    
    if(pvo.endPage != pvo.maxPage){
        const aTag = document.createElement("a");
        aTag.setAttribute("href" , `/projectMemo/list?projectNo=${x[0].prjKey}&pno=${pvo.endPage + 1}`);
        aTag.innerText = " > ";
        page.appendChild(aTag);
    }

}