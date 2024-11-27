const tbodyTag = document.querySelector(".list-table tbody");
    //상세조회 가능 기능
    tbodyTag.addEventListener("click",function(evt){
        if(evt.target.tagName != "TD"){
            return;
        }
        const no = evt.target.parentNode.children[0].innerText;
        location.href=`/store/detail?no=${no}`
    })