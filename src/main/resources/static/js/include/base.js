function getUrlCheckParam(id) {
    const checkBox = document.getElementById(id);
    return "&" + id + "=" + checkBox.checked;
}

function redirectByDriveId() {
    const listRow = document.querySelector("table tbody");

    listRow.addEventListener("click", (event) => {
        let driveID = event.target.parentElement.getAttribute("data-drive-id")
        let url = "/dailyReport/carcareform" + "?driveID=" + driveID;
        window.location.href = url;
    });
}

function setInputActiveByCheckBox(activeInput) {

    const checkBoxElement = document.querySelector('input[name="' + activeInput.checkBoxName + '"]');

    checkBoxElement.addEventListener('change', (event) => {
        activeInput.inputName.forEach(tagName => {
            const inputElement = document.querySelector('input[name="' + tagName + '"]');

            inputElement.value = "";
            inputElement.checked = false;
            inputElement.disabled = !inputElement.disabled;
        })
    })
}

function addCheckParam(inputData, names) {
    names.forEach(name => {
            const checkBox = document.querySelector('input[name="' + name + '"]');
            inputData[name] = checkBox.checked;
        }
    )
}

function checkInputValidation(form){

    const formElement=document.querySelector('form[name="' + form + '"]');
    const formInput= formElement.getElementsByTagName('input');

    for(let i=0; formInput.length; i++){
        let name = formInput[i].getAttribute('name');
        let value = formInput[i].value;
        let require = formInput[i].required;

        if(formInput[i].required && value === ''){
            alert(formInput[i].parentElement.querySelector('h3').innerHTML +'를 다시 확인해주세요');
            formInput[i].focus();
            return true;
        }

    }

    return false;
}


document.addEventListener("DOMContentLoaded", () => {
    const queryString = window.location.search;
    const params = new URLSearchParams(queryString);

    let driveId = params.get("driveId");

    if (driveId !== null) {
        requestDetailsByDriveID(driveId);
    }

    redirectByDriveId();
});

