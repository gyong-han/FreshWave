function removeHyphen(){
    const startTag1 = document.querySelector(".start").value;
    const endTag1 = document.querySelector(".end").value;

    const startTag = startTag1.replaceAll("-" , "");
    const endTag = endTag1.replaceAll("-" , "");

    document.querySelector("#start").value = startTag;
    document.querySelector("#end").value = endTag;
}