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
    const delMember = x.parentNode.parentNode.children[4].children[0].value;
    const key = x.parentNode.parentNode.children[5].children[0].value;



    $.ajax({
        url : "/project/delMember" ,
        data : {
            "empNo" : delMember ,
            key
        } ,
        method : "post" ,
        success : function(){
            console.log("통신 성공");
        } ,
        error : function(){
            console.log("통신 실패");
        } ,
    })

}