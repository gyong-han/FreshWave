function loadBusinessList(){
    const tbodyTag = document.querySelector("table>tbody");

    $.ajax({
        url : "/business/list/data",
        success : function(businessVoList){
            for(const vo of businessVoList){
                const trTag = document.createElement("tr");
                const tdTag01 = document.createElement("td");
                const aTag = document.createElement("a");
                aTag.setAttribute("href",`/business/detail?bno=${vo.no}`);
                aTag.innerText=vo.name;
                tdTag01.appendChild(aTag);
                trTag.appendChild(tdTag01);

                const tdTag02 = document.createElement("td");
                tdTag02.innerText=vo.ceo;
                trTag.appendChild(tdTag02);

                const tdTag03 = document.createElement("td");
                tdTag03.innerText=vo.phone;
                trTag.appendChild(tdTag03);

                const tdTag04 = document.createElement("td");
                tdTag04.innerText=vo.managerName;
                trTag.appendChild(tdTag04);

                const tdTag05 = document.createElement("td");
                tdTag05.innerText=vo.deptName;
                trTag.appendChild(tdTag05);

                const tdTag06 = document.createElement("td");
                tdTag06.innerText=vo.startDate + "~" + vo.endDate;
                trTag.appendChild(tdTag06);

                tbodyTag.appendChild(trTag);
            }
        },
        fail : function(){
            console.log("통신 실패...");
        }
    })
}

loadBusinessList();