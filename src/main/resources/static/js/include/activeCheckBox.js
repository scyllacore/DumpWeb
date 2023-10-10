function setActiveByCheckBox(activeInputs) {
    for (const idx in activeInputs) {
        const activeConfigParams = activeInputs[idx];
        const checkBoxElement = document.querySelector('input[name="' + activeConfigParams.checkBoxName + '"]');

        if(checkBoxElement.checked){
            setDisabled(checkBoxElement,activeConfigParams,checkBoxElement.checked);
        }

        checkBoxElement.addEventListener('change', (event) => {
            let active = checkBoxElement.checked;

            setDisabled(checkBoxElement, activeConfigParams, active);
            initInput(activeConfigParams, active);
            setExceptInitInput(checkBoxElement, activeConfigParams, active);
        })
    }
}

function setDisabled(checkBoxElement, activeConfigParams, active) {

    if (checkUndefined(activeConfigParams.range)) {
        return;
    }

    let start = activeConfigParams.range[0];
    let end = activeConfigParams.range[1];
    for (let i = start; i < end; i++) {
        const inputElement = document.querySelector('[name="' + activeConfigParams.inputNames[i] + '"]');
        if (activeConfigParams.type === 'active') {
            inputElement.disabled = !active;
        } else {
            inputElement.disabled = active;
        }
    }
}



function setExceptInitInput(checkBoxElement, activeConfigParams, active) {

    if (checkUndefined(activeConfigParams.exceptRange)) {
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

function setDisabledFalse(form){
    const formElement = document.querySelector('form[name="' + form + '"]');
    const formInput = formElement.querySelectorAll('input');
    const formSelector = formElement.querySelectorAll('select');

    for (let i = 0; i < formInput.length; i++) {
        formInput[i].disabled = false;
    }

    for (let i = 0; i < formSelector.length; i++) {
        formSelector[i].disabled = false;
    }
}


function checkUndefined(val) {
    if (typeof val === 'undefined') {
        return true;
    }
    return false;
}