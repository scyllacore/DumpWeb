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

function setActiveByCheckBox(activeInputs) {
    for (const key in activeInputs) {
        const checkBoxElement = document.querySelector('input[name="' + activeInputs[key].checkBoxName + '"]');

        checkBoxElement.addEventListener('click', (event) => {
            let active = checkBoxElement.checked;

            let start = activeInputs[key].range[0];
            let end = activeInputs[key].range[1];
            for (let i = start; i < end; i++) {
                const inputElement = document.querySelector('[name="' + activeInputs[key].inputNames[i] + '"]');
                inputElement.disabled = !active;
            }

            let initStart = activeInputs[key].initRange[0];
            let initEnd = activeInputs[key].initRange[1];
            for (let i = initStart; i < initEnd; i++) {
                const inputElement = document.querySelector('[name="' + activeInputs[key].inputNames[i] + '"]');
                inputElement.value = "";
                inputElement.checked = false;
            }
        })
    }
}

function setDisableByCheckBox(LockedInput) {
    for (const key in LockedInput) {
        const checkBoxElement = document.querySelector('input[name="' + LockedInput[key].checkBoxName + '"]');

        checkBoxElement.addEventListener('click', (event) => {
            let active = checkBoxElement.checked;

            let start = LockedInput[key].range[0];
            let end = LockedInput[key].range[1];

            for (let i = start; i < end; i++) {
                const inputElement = document.querySelector('[name="' + LockedInput[key].inputNames[i] + '"]');
                inputElement.disabled = active;
            }

            const exceptActive = document.querySelector('input[name="' + LockedInput[key].exceptCheckBox + '"]').checked;

            if (active || exceptActive) {
                return;
            }

            let exceptStart = LockedInput[key].exceptRange[0];
            let exceptEnd = LockedInput[key].exceptRange[1];

            for (let i = exceptStart; i < exceptEnd; i++) {
                const inputElement = document.querySelector('[name="' + LockedInput[key].inputNames[i] + '"]');
                inputElement.disabled = true;
            }
        })
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
    const formInput = formElement.getElementsByTagName('input');

    for (let i = 0; formInput.length; i++) {
        let name = formInput[i].getAttribute('name');
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


document.addEventListener("DOMContentLoaded", () => {
    const queryString = window.location.search;
    const params = new URLSearchParams(queryString);

    let driveId = params.get("driveId");

    if (driveId !== null) {
        requestDetailsByDriveID(driveId);
    }

    redirectByDriveId();
});

