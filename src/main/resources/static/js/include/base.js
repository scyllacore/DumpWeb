function checkUndefined(val){
    if(typeof val === 'undefined'){
        return true;
    }
    return false;
}

function setActiveByCheckBox(activeInputs) {
    for (const idx in activeInputs) {
        const activeConfigParams = activeInputs[idx];
        const checkBoxElement = document.querySelector('input[name="' + activeConfigParams.checkBoxName + '"]');

        checkBoxElement.addEventListener('click', (event) => {
            let active = checkBoxElement.checked;

            setDisabled(checkBoxElement, activeConfigParams, active);
            initInput(activeConfigParams, active);
            setExceptInitInput(checkBoxElement, activeConfigParams, active);
        })
    }
}

function setDisabled(checkBoxElement, activeConfigParams, active) {

    if(checkUndefined(activeConfigParams.range)){
        return;
    }

    let start = activeConfigParams.range[0];
    let end = activeConfigParams.range[1];
    for (let i = start; i < end; i++) {
        const inputElement = document.querySelector('[name="' + activeConfigParams.inputNames[i] + '"]');
        if(activeConfigParams.type === 'active'){
            inputElement.disabled = !active;
        }else{
            inputElement.disabled = active;
        }
    }
}

function initInput(activeConfigParams) {

    if(checkUndefined(activeConfigParams.initRange)){
        return;
    }

    let initStart = activeConfigParams.initRange[0];
    let initEnd =  activeConfigParams.initRange[1];
    for (let i = initStart; i < initEnd; i++) {
        const inputElement = document.querySelector('[name="' + activeConfigParams.inputNames[i] + '"]');
        inputElement.value = "";
        inputElement.checked = false;
    }
}

function setExceptInitInput(checkBoxElement, activeConfigParams, active) {

    if(checkUndefined(activeConfigParams.exceptRange)){
        return;
    }

    const exceptActive = document.querySelector('input[name="' + activeConfigParams.exceptCheckBox + '"]').checked;

    if (active || exceptActive) {
        return;
    }

    let exceptStart = activeConfigParams.exceptRange[0];
    let exceptEnd = activeConfigParams.exceptRange[1];

    for (let i = exceptStart; i < exceptEnd; i++) {
        const inputElement = document.querySelector('[name="' + activeConfigParams.inputNames[i] + '"]');
        inputElement.disabled = true;
    }
}

function addCheckParam(inputData, names) {
    names.forEach(name => {
            const checkBox = document.querySelector('input[name="' + name + '"]');
            inputData[name] = checkBox.checked;
        }
    )
}

function checkInputValidation(form) {

    const formElement = document.querySelector('form[name="' + form + '"]');
    const formInput = formElement.querySelectorAll('input');

    console.log(formInput);

    for (let i = 0; i < formInput.length; i++) {
        let name = formInput[i].getAttribute('name');
        console.log(name);
        let value = formInput[i].value;
        let require = formInput[i].required;

        if (formInput[i].required && value === '') {
            alert(formInput[i].parentElement.querySelector('h3').innerHTML + '를 다시 확인해주세요');
            formInput[i].focus();
            return true;
        }

    }

    return false;
}


function redirectByDriveId() {
    const listRow = document.querySelector("table tbody");

    listRow.addEventListener("click", (event) => {
        let driveID = event.target.parentElement.getAttribute("data-drive-id")
        let url = "/dailyReport/carcareform" + "?driveID=" + driveID;
        window.location.href = url;
    });
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


