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