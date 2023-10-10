function getRequestJson(element, addingParams) {

    if (typeof element === 'object') {
        return JSON.stringify(element);
    }

    const el = document.querySelector('[name="' + element + '"]');
    let obj = {};

    if (el.tagName === 'FORM') {
        const entry = new FormData(el).entries();
        obj = Object.fromEntries(entry);
        addCheckedInput(obj, addingParams);
    } else if (el.tagName === 'INPUT') {
        obj[el.getAttribute('name')] = el.value;
    }

    return JSON.stringify(obj);
}

function addCheckBoxInput(inputData, names = []) {
    names.forEach(name => {
            const checkBox = document.querySelector('input[name="' + name + '"]');
            inputData[name] = checkBox.checked;
        }
    )
}

class RequestHandler {
    constructor(argUrl, argMethod, argInputData, argHeaderContentType = 'application/json') {
        const options = {
            method: argMethod,
            body: argInputData,
            headers: {'Content-Type': argHeaderContentType}
        }

        this.request = new Request(argUrl, options);
    }

    fetchRequest() {
        return this.request.requestData();
    }
}