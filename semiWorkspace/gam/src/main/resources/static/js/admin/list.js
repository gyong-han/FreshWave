
function paintPageArea(pvo){
    const pageArea = document.querySelector(".page-area");
    pageArea.innerHTML = "";

    //이전버튼
    if(pvo.startPage != 1){
        const aTag = document.createElement("a");
        aTag.setAttribute("href" , `/admin/list?pno=${pvo.startPage-1}`);
        aTag.innerText = "<";
        pageArea.appendChild(aTag);
    }

    //페이지버튼
    for(let i = pvo.startPage ; i <= pvo.endPage; ++i ){
        const aTag = document.createElement("a");
        aTag.setAttribute("href" , `/admin/list?pno=${i}`);
        aTag.innerText = i;
        pageArea.appendChild(aTag);
    }

    //다음버튼
    if(pvo.endPage != pvo.maxPage){
        const aTag = document.createElement("a");
        aTag.setAttribute("href" , `/admin/list?pno=${pvo.endPage + 1}`);
        aTag.innerText = '>';
        pageArea.appendChild(aTag);
    }

}

function loadBoardList(searchType, searchValue){
    const tbodyTag = document.querySelector("main table>tbody");

    const url = new URL(location);
    let pno = url.searchParams.get("pno");
    if(pno == null){
        pno = 1;
    }
    $.ajax({
        url : `/admin/list/data?pno=${pno}` ,
        data : {
            searchType ,
            searchValue ,
        } ,
        success : function(m){
            const employeeVoList = m.a;
            const pvo = m.b;
            paintPageArea(pvo);


            tbodyTag.innerHTML = "";

            for(const vo of employeeVoList){
                const trTag = document.createElement("tr");

                const tdTag01 = document.createElement('td');
                tdTag01.innerText = vo.empNo;
                trTag.appendChild(tdTag01);


                const tdTag02 = document.createElement('td');
                tdTag02.innerText = vo.memberName;
                trTag.appendChild(tdTag02);


                const tdTag03 = document.createElement('td');
                tdTag03.innerText = vo.deptName;
                trTag.appendChild(tdTag03);

                const tdTag04 = document.createElement('td');
                tdTag04.innerText = vo.jobName;
                trTag.appendChild(tdTag04);

                const tdTag05 = document.createElement('td');
                tdTag05.innerText = vo.email;
                trTag.appendChild(tdTag05);

                tbodyTag.appendChild(trTag);
            }
        } ,
        fail : function(){
            alert("목록 조회 실패)");
        } ,
    });

}

loadBoardList();

function formSearchType(x){
    const titleTag = document.querySelector("input[name=searchValue]");
    const categoryTag = document.querySelector("select[name=searchValue]");

    if(x.value == "memberName"){
        categoryTag.setAttribute("disabled" , true);
        titleTag.removeAttribute("disabled");
    }else{
        titleTag.setAttribute("disabled" , true);
        categoryTag.removeAttribute("disabled");
    }

}

function searchForm() {
    const searchType = document.querySelector("select[name=searchType]").value;

    const nameTagValue = document.querySelector("input[name=searchValue]").value;
    const deptTagValue = document.querySelector("select[name=searchValue]").value;

    let searchValue = "";
    if(searchType == "memberName"){
        searchValue = nameTagValue;
    }else{
        searchValue = deptTagValue;
    }

    loadBoardList(searchType , searchValue);

    return false;
}

const tbodyTag = document.querySelector("main .list-member tbody");

tbodyTag.addEventListener("click" , function(evt){
    if(evt.target.tagName != "TD"){return;}
    const no = evt.target.parentNode.children[0].innerText;
    location.href=`/admin/detail?no=${no}`;
});

