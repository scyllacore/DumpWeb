function getJsonForm(form) {
    const inputEntry = new FormData(document.querySelector('form[name="' + form + '"]')).entries();
    const JsonForm = JSON.stringify(Object.fromEntries(inputEntry));
    return JsonForm;
}

function createFetchRequest(argUrl, argOptions) {
    const url = argUrl;
    const options = argOptions;

    const req = new Request(url, options);
    return req.requestData();
}

function handleRequest(argUrl, argMethod, argInputData, argHeaderContentType = 'application/json') {
    return createFetchRequest(
        argUrl,
        {
            method: argMethod,
            body: argInputData,
            headers: {'Content-Type': argHeaderContentType}
        }
    )
}
