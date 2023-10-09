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

function initInput(activeConfigParams) {

    if (checkUndefined(activeConfigParams.initRange)) {
        return;
    }

    let initStart = activeConfigParams.initRange[0];
    let initEnd = activeConfigParams.initRange[1];
    for (let i = initStart; i < initEnd; i++) {
        const inputElement = document.querySelector('[name="' + activeConfigParams.inputNames[i] + '"]');
        inputElement.value = "";
        inputElement.checked = false;
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

function checkUndefined(val) {
    if (typeof val === 'undefined') {
        return true;
    }
    return false;
}


/* */
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

    for (let i = 0; i < formInput.length; i++) {
        let value = formInput[i].value;

        if (formInput[i].required && value === '') {
            alert(formInput[i].parentElement.querySelector('h3').innerHTML + '를 다시 확인해주세요');
            formInput[i].focus();
            return true;
        }

    }

    return false;
}

/* */

function redirectById(url, idName) {
    const listRow = document.querySelector("table tbody");

    listRow.addEventListener("click", (event) => {
        let Id = event.target.parentElement.getAttribute('data-' + idName + '-id');
        let url = url + '?' + idName + '=' + Id;
        window.location.href = url;
    });
}

/* */

async function inputDataByParams(url,idName) {
    const idParam = getUrlIdParam(idName);

    if (idParam === null) {
        return;
    }

    const data={};
    data[idName] = idParam;

    const request = new RequestHandler(url
        , 'POST'
        , getRequestJson(data))
    const responseData = await request.fetchRequest();
    fillInput(responseData);
}

function getUrlIdParam(idName) {
    const queryString = window.location.search;
    const params = new URLSearchParams(queryString);
    return params.get(idName);
}

function fillInput(data) {
    for (const key in data) {

        if (data[key] === null || key === 'userIdIdxFk') {
            continue;
        }

        if (key === "item") {
            const optionList = document.querySelector('select[name="' + key + '"]');
            optionList.querySelector('option[value="' + data[key] + '"]').selected = true;
        } else if (typeof data[key] === "boolean") {
            document.querySelector('input[name="' + key + '"]').checked = data[key];
        } else {
            document.querySelector('[name=' + key + ']').value = data[key];
        }
    }
}


