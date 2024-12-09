
function loadProjectMemoList(){

    const outer = document.querySelectorAll(".outer");
        const url = new URL(location);
        let key = url.searchParams.get("projectNo");
    const waitDiv = document.querySelector("#waitDiv");
    const ingDiv = document.querySelector("#ingDiv");
    const comDiv = document.querySelector("#comDiv");
    const h1Tag = document.querySelector("h1");

    $.ajax({

        url : "/projectMemo/cardListData" ,
        data : {
            key
        } ,
        success : function(m){
            console.log("통신성공");
            console.log(m);
            const wait = m.wait;
            const ing = m.ing;
            const com = m.com;
            const prjName = m.name;

            h1Tag.innerText = prjName.prjName;

            for(const vo of wait){
               const div1 = document.createElement("div");
               const div2 = document.createElement("div");
               const div3 = document.createElement("div");
               const div4 = document.createElement("div");
               const div5 = document.createElement("div");
               const div6 = document.createElement("div");
               const div7 = document.createElement("div");
               const btnTag = document.createElement("button");
            
               div1.innerText = vo.name;

               btnTag.type = "button";
               btnTag.value = vo.no;
               btnTag.innerText ="x";
               btnTag.onclick = function(){
                    del(this);
               };
               
               div2.appendChild(btnTag);

               div3.innerText = vo.priorityName;
               if(vo.priorityName == "HIGH"){
                div6.className = "high";
                }
                if(vo.priorityName == "MIDDLE"){
                    div6.className = "mid";
                }
                if(vo.priorityName == "LOW"){
                    div6.className = "low";
                }

               let date = `${vo.startDate}"~"${vo.endDate}`;
               div4.innerText = date;

               div1.className="top1";
               div2.className="top2";


               div7.appendChild(div3);
               div7.appendChild(div4);
               div7.className="span";

               div6.appendChild(div1);
               div6.appendChild(div2);
               div6.appendChild(div7);

               div5.className="inner"
               waitDiv.appendChild(div6);
            }

            for(const vo of ing){
                const div1 = document.createElement("div");
                const div2 = document.createElement("div");
                const div3 = document.createElement("div");
                const div4 = document.createElement("div");
                const div5 = document.createElement("div");
                const div6 = document.createElement("div");
                const div7 = document.createElement("div");
                const btnTag = document.createElement("button");
             
                div1.innerText = vo.name;
 
                btnTag.type = "button";
                btnTag.value = vo.no;
                btnTag.innerText ="x";
                btnTag.onclick = function(){
                     del(this);
                };
                
                div2.appendChild(btnTag);
 
                div3.innerText = vo.priorityName;

                if(vo.priorityName == "HIGH"){
                 div6.className = "high";
                 }
                 if(vo.priorityName == "MIDDLE"){
                     div6.className = "mid";
                 }
                 if(vo.priorityName == "LOW"){
                     div6.className = "low";
                 }
                
               
               
                let date = `${vo.startDate}"~"${vo.endDate}`;
                div4.innerText = date;

                div1.className="top1";
                div2.className="top2";


                div7.appendChild(div3);
                div7.appendChild(div4);
                div7.className="span";


                div6.appendChild(div1);
                div6.appendChild(div2);
                div6.appendChild(div7);
                div5.className="inner"
                ingDiv.appendChild(div6);
                
            }

            for(const vo of com){
                const div1 = document.createElement("div");
                const div2 = document.createElement("div");
                const div3 = document.createElement("div");
                const div4 = document.createElement("div");
                const div5 = document.createElement("div");
                const div6 = document.createElement("div");
                const div7 = document.createElement("div");
                const btnTag = document.createElement("button");
             
                div1.innerText = vo.name;
 
                btnTag.type = "button";
                btnTag.value = vo.no;
                btnTag.innerText = "x";
                btnTag.onclick = function(){
                     del(this);
                };
                
                div2.appendChild(btnTag);
 
                div3.innerText = vo.priorityName;

                if(vo.priorityName == "HIGH"){
                    div6.className = "high";
                    }
                    if(vo.priorityName == "MIDDLE"){
                        div6.className = "mid";
                    }
                    if(vo.priorityName == "LOW"){
                        div6.className = "low";
                    }


                let date = `${vo.startDate}"~"${vo.endDate}`;
                div4.innerText = date;
 

                div7.appendChild(div3);
                div7.appendChild(div4);
                div7.className = "bot";

                div1.className="top1";
                div2.className="top2";
                div7.className="span";

                div6.appendChild(div1);
                div6.appendChild(div2);
                div6.appendChild(div7);
                div5.className="inner"
                comDiv.appendChild(div6);
                
            }

        } ,
        error : function(){
            console.log("통신실패");
        } ,
    });    
}

loadProjectMemoList();

function del(x){
    const url = new URL(location);
    let key = url.searchParams.get("projectNo");
    const no = x.value;
    $.ajax({
        url : "/projectMemo/delete"  ,
        data : {
            "projectNo" : key ,
            no
        } ,
        success : function(){
            console.log("통신성공");
            location.reload()
        }  ,
        error :  function(){
            console.log("통신실패");
        }   ,
    })
}