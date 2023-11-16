window.onload = init;

function init() {
    let modal = document.getElementById('w3Modal');
    let span = document.getElementsByClassName("close1")[0];
    
    span.onclick = function () {
        modal.style.display = "none";
    }

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
}

function bsModal(name) {
    let projectName = document.getElementById('projectName1');
    projectName.innerHTML = name;
}

function w3Modal(name) {
    let modal = document.getElementById('w3Modal');
    let projectName = document.getElementById('projectName');
    projectName.innerHTML = name;
    modal.style.display = "block";
}