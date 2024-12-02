const fileTag = document.querySelector("input[name=f]");
fileTag.addEventListener("change" , preview);

function preview(evt){
    const previewArea = document.querySelector(".preview-area");
    previewArea.innerHTML = "";
    for(let i =0; i < evt.target.files.length; i++){
        const f = evt.target.files[i];
        const fr = new FileReader();
        fr.onload = function(evt){
            const dataUrl = evt.target.result;

            const imgTag = document.createElement("img");
            imgTag.setAttribute("src", dataUrl);
            imgTag.setAttribute("width", "100");
            imgTag.setAttribute("height", "100");
            previewArea.appendChild(imgTag);
        };
        fr.readAsDataURL(f);
    }
}