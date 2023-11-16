window.onload = init;


function init() {

    let modal = document.getElementById("fileCreateModal");
    let span =document.getElementById("fileClose");
    let fileCreateForm = document.getElementById("fileCreateForm");
    fileCreateForm.addEventListener('submit', validateFileForm);

    span.onclick = function () {
        modal.style.display = "none";
    }
   

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
            let imageInput = document.getElementById("imageInput");
            console.log(imageInput);
        } 
    }

}

function validateFileForm(form){
    let imageInput = document.getElementById("imageInput");
    console.log(imageInput)
    if(imageInput.files.item(0)==null){
        window.alert("Must select a file");
        form.preventDefault();
    }else if(imageInput.files.item(0).size>1000000){
        window.alert("File is to large. Must be smaller than 1MB");
        form.preventDefault();
    }


}




function taskFileModal(task, name) {
    let modal = document.getElementById('fileCreateModal');
    let taskId = document.getElementById('taskIdFile');
    let taskName = document.getElementById('taskNameFile')
    let urlId = document.getElementById("urlIdFile");
    urlId.setAttribute("value",window.location.pathname)
    taskName.innerHTML = name;
    taskId.setAttribute("value", task);
    modal.style.display = "block";
}

