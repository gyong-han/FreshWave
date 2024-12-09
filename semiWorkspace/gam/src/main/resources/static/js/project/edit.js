document.addEventListener("DOMContentLoaded", function () {
    const startDate1 = document.querySelector(".edit-date input[name=startDate1]").value;
    const endDate1 = document.querySelector(".edit-date input[name=endDate1]").value;
    const startDate2Input = document.querySelector(".edit-date input[name=startDate2]");
    const endDate2Input = document.querySelector(".edit-date input[name=endDate2]");
    const startDateHidden = document.querySelector(".edit-date input[name=startDate]");
    const endDateHidden = document.querySelector(".edit-date input[name=endDate]");

    if (startDate1) {
        const formattedStartDate = `${startDate1.slice(0, 4)}-${startDate1.slice(4, 6)}-${startDate1.slice(6, 8)}`;
        startDate2Input.value = formattedStartDate;
        startDateHidden.value = startDate1;
        startDate2Input.addEventListener("change", function () {
            startDateHidden.value = startDate2Input.value.replaceAll("-", "");
        });
    }
    if (endDate1) {
        const formattedEndDate = `${endDate1.slice(0, 4)}-${endDate1.slice(4, 6)}-${endDate1.slice(6, 8)}`;
        endDate2Input.value = formattedEndDate;
        endDateHidden.value = endDate1;
        endDate2Input.addEventListener("change", function () {
            endDateHidden.value = endDate2Input.value.replaceAll("-", "");
        });
    }
    if (!startDate1) {
        startDate2Input.addEventListener("change", function () {
            startDateHidden.value = startDate2Input.value.replaceAll("-", "");
        });
    }
    if (!endDate1) {
        endDate2Input.addEventListener("change", function () {
            endDateHidden.value = endDate2Input.value.replaceAll("-", "");
        });
    }
});

function f01(){
    const selectTag = document.querySelectorAll("select[name=accessLevel]");
    const nameTag = document.querySelectorAll("input[name=memberName]");

    for(let i = 0; i < selectTag.length; i++){
        const nameTag1 = selectTag[i].parentNode.parentNode.children[1];

        nameTag.value = nameTag1;

    }
}


function delMember(x){
    const delMember = x.parentNode.parentNode.children[7].children[0].value;
    const key = x.parentNode.parentNode.children[8].children[0].value;

    const trTag = x.parentNode.parentNode;



    $.ajax({
        url : "/project/delMember" ,
        data : {
            "empNo" : delMember ,
            key
        } ,
        method : "post" ,
        success : function(){
            console.log("통신 성공");
            trTag.remove();
        } ,
        error : function(){
            console.log("통신 실패");
        } ,
    })

}
function addMember() {
     //이름 받아오기
    const empVoList = document.querySelector("#empVoList");

    // 사원정보 리스트
    let nameTag = document.querySelector("#memberAddName");
    // 입력받은 이름
//     사원 정보 리스트를 돌면서 입력받은 이름과 일치하면 해당번호 사번 출력
     let i = 0;
     let empNo = null;
     for(const vo of empVoList.children){
        if(vo.value == nameTag.value){
            empNo = empVoList.children[i].getAttribute('data-memberNo');

            break;
        }
        i++
     }
     //사번 넘겨주기
    $.ajax({
        url : "/project/addMember" ,
        method :  "post" ,
        data :  {
            empNo
        }   ,
        success : function(x){
                   const tableTag = document.querySelector("#addMember-area");
                   const tbodyTag = document.querySelector("#addMember-area tbody");
                   const trTag = document.createElement("tr");
                   const tdTag1 = document.createElement("td");
                   const tdTag2 = document.createElement("td");
                   const tdTag3 = document.createElement("td");
                   const tdTag4 = document.createElement("td");
                   const tdTag5 = document.createElement("td");
                   const tdTag6 = document.createElement("td");
                   const tdTag7 = document.createElement("td");
                   const selectTag = document.createElement("select");
                   const optionTag1 = document.createElement("option");
                   const optionTag2 = document.createElement("option");
                   const inputTag1 = document.createElement("input");
                   const inputTag2 = document.createElement("input");
                   const inputTag3 = document.createElement("input");
                   const inputTag4 = document.createElement("input");
                   const inputTag5 = document.createElement("input");
                   const inputTag6 = document.createElement("input");
                   const btnTag = document.createElement("button");

                   inputTag1.type = "hidden";
                   inputTag1.name = "profileNameArr"
                   inputTag1.value = x.profileName;

                   inputTag2.type = "hidden";
                   inputTag2.name = "memberNameArr"
                   inputTag2.value = x.memberName;

                   inputTag3.type = "hidden";
                   inputTag3.name = "jobNameArr"
                   inputTag3.value = x.jobName;

                   inputTag4.type = "hidden";
                   inputTag4.name = "deptNameArr"
                   inputTag4.value = x.deptName;

                   inputTag6.type = "hidden";
                   inputTag6.name = "empNoArr";
                   inputTag6.value = x.empNo;

                   tdTag1.innerText = x.profileName;
                   trTag.appendChild(tdTag1);
                   trTag.appendChild(inputTag1);


                   tdTag2.innerText = x.memberName;
                   trTag.appendChild(tdTag2);
                   trTag.appendChild(inputTag2);

                   tdTag3.innerText = "("+x.jobName+")";
                   trTag.appendChild(tdTag3);
                   trTag.appendChild(inputTag3);

                   tdTag4.innerText = x.deptName;
                   trTag.appendChild(tdTag4);
                   trTag.appendChild(inputTag4);

                   optionTag1.innerText = "수정";
                   optionTag1.value = "수정";

                   optionTag2.innerText = "읽기";
                   optionTag2.value = "읽기";

                   selectTag.name ="modiAuthArr";
                   selectTag.appendChild(optionTag1);
                   selectTag.appendChild(optionTag2);
                   tdTag5.appendChild(selectTag);
                   trTag.appendChild(tdTag5);

                   tdTag6.appendChild(inputTag6);
                   trTag.appendChild(tdTag6);

                   btnTag.type = "button";
                   btnTag.innerText = 'x';
                   btnTag.addEventListener("click", function () {
                          trTag.remove();
                      });
                   tdTag7.appendChild(btnTag);
                   trTag.appendChild(tdTag7);

                   tbodyTag.appendChild(trTag);

                   nameTag.value = "";
        }  ,
        error :  function(){
               console.log("통신 실패");
        }   ,
    })
}