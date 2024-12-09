document.querySelector("#f").addEventListener('change', function() {
    const fileTag = document.querySelector("#f");
    const labelTag = document.querySelector("#file-label");
    const spanTag = document.querySelector("#fileName");

    fileTag.style.display = "inline";
    fileTag.setAttribute("name","f");
    labelTag.style.display = "none";
    spanTag.style.display = 'none';
  });
