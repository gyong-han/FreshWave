document.addEventListener("DOMContentLoaded" , function(){
    const pmTag = document.querySelectorAll("input[name=pmAccess]");
    const pmTdTag = document.querySelectorAll("td[class=pmAccess]");
    if (pmTag == 'Y') {
        pmTdTag.innerText = "수정";
    }
    if (pmTag == 'N') {
        pmTdTag.innerText = "읽기";
    }
})
