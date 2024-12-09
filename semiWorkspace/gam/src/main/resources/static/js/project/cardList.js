function loadProjectList(){
    const cardContainer = document.querySelector(".card-list");
    
    $.ajax({

        url : "/project/cardListData" ,
        success : function(x){
            console.log("통신성공");
            console.log(x);

            let i = 0;
            for(const vo of x){
                //제목 , 우선순위 , 날짜 , 버튼
                const card = document.createElement("div");
                
                const name = document.createElement("div");
                const button = document.createElement("div");
                const hidden = document.createElement("div");
                const priority = document.createElement("div");
                const date = document.createElement("div");
                const div1 = document.createElement("div");
                const div2 = document.createElement("div");
                const div3 = document.createElement("div");
                const aTag1 = document.createElement("a");
                const aTag2 = document.createElement("a");

                
                aTag1.setAttribute("href", `/project/detail?projectNo=${vo.key}`);
                aTag2.setAttribute("href", `/project/edit?projectNo=${vo.key}`);
                aTag1.innerText = "상세 조회";
                aTag2.innerText = "수정";
                div1.appendChild(aTag1);
                div2.appendChild(aTag2);
                hidden.appendChild(div1);
                hidden.appendChild(div2);
                hidden.className = "hidden-area";
                hidden.style.display = "none";


                button.innerText= "..."
                button.className ="top2";
                button.addEventListener("click" , function(x){
                    event.stopPropagation();
                        const hiddenContent = button.parentNode.querySelector(".hidden-area");

                        if (hiddenContent.style.display === "none") {
                            hiddenContent.style.display = "grid";
                        } else {
                            hiddenContent.style.display = "none";
                        }

                });


                name.innerText =vo.name;
                name.className = "top1";

                priority.innerText=vo.priorityName;
                div3.className = "bot";
                date.innerText = `${vo.startDate}"~"${vo.endDate}`;
                div3.appendChild(priority);
                div3.appendChild(date);



                card.appendChild(name);
                card.appendChild(button);
                card.appendChild(hidden);
                card.appendChild(div3);

                card.addEventListener("click" , function(){
                    location.href=`/projectMemo/list?projectNo=${vo.key}`;
                });


                cardContainer.appendChild(card);


                if(vo.priorityName == "HIGH"){
                    card.className = "high";
                }
                if(vo.priorityName == "MIDDLE"){
                    card.className = "mid";
                }
                if(vo.priorityName == "LOW"){
                    card.className = "low";
                }



                i++;
                if(i == 6){
                    break;
                }
            }
        } ,
        error : function(){
            console.log("통신실패");
        } ,
    });    
}

loadProjectList();

