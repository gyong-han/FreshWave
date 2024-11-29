//function loadProjectList(){
//
//    $.ajax({
//        url : "/project/list/data" ,
//        success : function(m){
//            const pvo = m.p;
//            const mvo = m.m;
//            const dvo = m.d;
//
//            const tbodyTag = document.querySelector("div[class=prj-list-area] tbody");
//
//                for(let i = 0; i = pvo.lenght; i++){
//                    const trTag = document.createElement('tr');
//
//                    const tdTag1 = document.createElement('td');
//                    tdTag1.innerText = "1";
//                    trTag.appendChild(tdTag1);
//
//                    const tdTag2 = document.createElement('td');
//                    tdTag2.innerText = "2";
//                    trTag.appendChild(tdTag2);
//
//                    const tdTag3 = document.createElement('td');
//                    tdTag3.innerText = "3";
//                    trTag.appendChild(tdTag3);
//
//                    const tdTag4 = document.createElement('td');
//                    tdTag4.innerText = "4";
//                    trTag.appendChild(tdTag4);
//
//                    const tdTag5 = document.createElement('td');
//                    tdTag5.innerText = "5";
//                    trTag.appendChild(tdTag5);
//
//                    const tdTag6 = document.createElement('td');
//                    tdTag6.innerText = "6";
//                    trTag.appendChild(tdTag6);
//
//                    tbodyTag.appendChild(trTag);
//                    }
//
//
//        } ,
//        fail : function(){
//            console.log("통신 실패 ...");
//        } ,
//    });
//}
//
//loadProjectList();

