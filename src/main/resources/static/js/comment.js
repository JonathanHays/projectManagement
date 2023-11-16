window.onload = init;


function init() {

    let modal = document.getElementById('taskCommentModal');
    let modal2 = document.getElementById('taskCreateModal');
    let modal3 = document.getElementById("fileCreateModal");
    let modal4 = document.getElementById('taskEditModal');

    let span = document.getElementById("commentClose");
    let span2 = document.getElementById("taskClose");
    let span3 = document.getElementById("fileClose");
    let span4 = document.getElementById("editClose");

    let taskCommentForm = document.getElementById("taskCommentForm");
    taskCommentForm.addEventListener('submit', validateForm);


    let taskCreateForm = document.getElementById("taskCreateForm");
    taskCreateForm.addEventListener('submit', validateTaskForm);


    let fileCreateForm = document.getElementById("fileCreateForm");
    fileCreateForm.addEventListener('submit', validateFileForm);

    span.onclick = function () {
        modal.style.display = "none";
    }
    span2.onclick = function () {
        modal2.style.display = "none";
    }
    span3.onclick = function () {
        let imageInput = document.getElementById("imageInput");
        console.log(imageInput.files)
        
        modal3.style.display = "none";
    }

    span4.onclick = function () {

        modal4.style.display = "none";
    }

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        } else if (event.target == modal2) {
            modal2.style.display = "none";
        } else if (event.target == modal3) {
            modal3.style.display = "none";
        } else if (event.target == modal4) {
            modal4.style.display = "none";
        }

    }

}

function validateFileForm(form) {
    let imageInput = document.getElementById("imageInput");

    if (imageInput.files.item(0) == null) {
        window.alert("Must select a file");
        form.preventDefault();
    } else if (imageInput.files.item(0).size > 1000000) {
        window.alert("File is to large. Must be smaller than 1MB");
        form.preventDefault();
    }


}

function validateTaskForm(form) {
    let nameField = document.getElementById("name");

    if (nameField.value == "") {
        window.alert("Note cannot be blank");
        form.preventDefault();
    }

}

function validateForm(form) {
    let modal = document.getElementById('taskCommentModal');
    let notes = document.getElementById("notes");
    if (notes.value == "") {
        window.alert("Note cannot be blank");
        form.preventDefault();
    }

}


function taskFileModal(task, name) {
    let modal = document.getElementById('fileCreateModal');
    let taskId = document.getElementById('taskIdFile');
    let taskName = document.getElementById('taskNameFile')
    let urlId = document.getElementById("urlIdFile");
    urlId.setAttribute("value", window.location.pathname)
    taskName.innerHTML = name;
    taskId.setAttribute("value", task);
    modal.style.display = "block";
}

function taskCommentModal(task, name) {
    let modal = document.getElementById('taskCommentModal');
    let taskId = document.getElementById('taskCommentId');
    let taskName = document.getElementById('taskName')

    taskName.innerHTML = name;
    taskId.setAttribute("value", task);
    modal.style.display = "block";
}

function taskCreateModal(featureId, featureName) {
    let urlId = document.getElementById("urlId");
    let modal = document.getElementById("taskCreateModal")
    let name = document.getElementById("featureName");
    let featureIdField = document.getElementById("featureId");
    name.innerHTML = featureName;
    featureIdField.setAttribute("value", featureId)
    urlId.setAttribute("value", window.location.pathname)
    modal.style.display = "block";
}

function taskEditModal(id, name, size, status, priority, description) {
    let urlId = document.getElementById("editUrlId");
    let modal = document.getElementById("taskEditModal")
    let idInput = document.getElementById("editId");
    let nameInput = document.getElementById("editName");
    let sizeInput = document.getElementById("editSize");
    let statusInput = document.getElementById("editStatus");
    let priorityInput = document.getElementById("editPriority");
    let descriptionInput = document.getElementById("editDescription");
    descriptionInput.textContent = description;
    urlId.setAttribute("value", window.location.pathname);
    idInput.setAttribute("value", id);
    nameInput.setAttribute("value", name);

    if (size == "SMALL") {
        sizeInput.innerHTML = "<option value='SMALL' selected=\'selected\'>Small</option><option value='MEDIUM'>Medium</option><option value='LARGE'>Large</option><option value='XLARGER'>X-Large</option>";
    } else if (size == "MEDIUM") {
        sizeInput.innerHTML = "<option value='SMALL'>Small</option><option value='MEDIUM' selected=\'selected\'>Medium</option><option value='LARGE'>Large</option><option value='XLARGER'>X-Large</option>";
    } if (size == "LARGE") {
        sizeInput.innerHTML = "<option value='SMALL'>Small</option><option value='MEDIUM'>Medium</option><option value='LARGE' selected=\'selected\'>Large</option><option value='XLARGER'>X-Large</option>";
    } if (size == "XLARGER") {
        sizeInput.innerHTML = "<option value='SMALL'>Small</option><option value='MEDIUM'>Medium</option><option value='LARGE'>Large</option><option value='XLARGER' selected=\'selected\'>X-Large</option>";
    }

    if (priority == "LOW") {
        priorityInput.innerHTML = '<option value="LOW" selected=\"selected\">Low</option><option value="NORMAL">Normal</option><option value="HIGH">High</option><option value="URGENT">Urgent</option>';
    } else if (priority == "NORMAL") {
        priorityInput.innerHTML = '<option value="LOW">Low</option><option value="NORMAL" selected=\"selected\">Normal</option><option value="HIGH">High</option><option value="URGENT">Urgent</option>';
    } else if (priority == "HIGH") {
        priorityInput.innerHTML = '<option value="LOW">Low</option><option value="NORMAL">Normal</option><option value="HIGH" selected=\"selected\">High</option><option value="URGENT">Urgent</option>';
    } else if (priority == "URGENT") {
        priorityInput.innerHTML = '<option value="LOW">Low</option><option value="NORMAL">Normal</option><option value="HIGH">High</option><option value="URGENT" selected=\"selected\">Urgent</option>';
    }

    if (status == "BACKLOG") {
        statusInput.innerHTML = '<option value="BACKLOG" selected=\"selected\">Backlog</option><option value="READY">Ready</option><option value="INPROGRES">In-Progress</option><option value="TESTING">Testing</option><option value="COMPLETE">Complete</option>';
    } else if (status == "READY") {
        statusInput.innerHTML = '<option value="BACKLOG">Backlog</option><option value="READY" selected=\"selected\">Ready</option><option value="INPROGRES">In-Progress</option><option value="TESTING">Testing</option><option value="COMPLETE">Complete</option>';
    } else if (status == "INPROGRES") {
        statusInput.innerHTML = '<option value="BACKLOG">Backlog</option><option value="READY">Ready</option><option value="INPROGRES" selected=\"selected\">In-Progress</option><option value="TESTING">Testing</option><option value="COMPLETE">Complete</option>';
    } else if (status == "TESTING") {
        statusInput.innerHTML = '<option value="BACKLOG">Backlog</option><option value="READY">Ready</option><option value="INPROGRES">In-Progress</option><option value="TESTING" selected=\"selected\">Testing</option><option value="COMPLETE">Complete</option>';
    } else if (status == "COMPLETE") {
        statusInput.innerHTML = '<option value="BACKLOG">Backlog</option><option value="READY">Ready</option><option value="INPROGRES">In-Progress</option><option value="TESTING">Testing</option><option value="COMPLETE" selected=\"selected\">Complete</option>';
    }



    modal.style.display = "block";
}