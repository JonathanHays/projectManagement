window.onload = init;


function init() {
    let fileCreateForm = document.getElementById("fileCreateForm");
    fileCreateForm.addEventListener('submit', validateFileForm);

    let modal = document.getElementById('taskImageModal');
    // let span = document.getElementById("imageClose");

    // span.onclick = function () {
    //     modal.style.display = "none";
    // }

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
}



function validateFileForm(form){
    let imageInput = document.getElementById("imageInput");

    if(imageInput.files.item(0)==null){
        window.alert("Must select a file");
        form.preventDefault();
    }else if(imageInput.files.item(0).size>1000000){
        window.alert("File is to large. Must be smaller than 1MB");
        form.preventDefault();
    } 


}

function showImageModal(){
    let modal = document.getElementById("taskImageModal");

    modal.style.display="block";
}
