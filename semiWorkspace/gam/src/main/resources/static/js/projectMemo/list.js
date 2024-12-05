const tbodyTag = document.querySelector(".memo-list-area tbody");
const hiddenTag = document.querySelector(".memo-list-area input[type=hidden]").value;


$.ajax({
    url :   `/projectMemo/list?projectNo=${hiddenTag}` ,
    method : "post" ,
    data : {

        "key" : hiddenTag

    } ,
    success : function(x){
        console.log(x);

        let i = 0;
        for(const vo of x){
            const trTag = document.createElement("tr");
            const tdTag1 = document.createElement("td");
            const tdTag2 = document.createElement("td");
            const tdTag3 = document.createElement("td");
            const tdTag4 = document.createElement("td");
            const tdTag5 = document.createElement("td");
            const tdTag6 = document.createElement("td");
            const tdTag7 = document.createElement("td");
            const inputTag = document.createElement("input");

            inputTag.type ="botton";
            inputTag.value = vo.name;   
            inputTag.onclick = function(){
                location.href=`/projectMemo/detail?projectNo=${vo.prjKey}&no=${vo.no}`;
            };

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