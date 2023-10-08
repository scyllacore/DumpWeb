function getFormJson(form, addingParams) {
    const entry = new FormData(document.querySelector('form[name="' + form + '"]')).entries();

    const obj = Object.fromEntries(entry);
    addCheckParam(obj, addingParams);

    return JSON.stringify(obj);
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