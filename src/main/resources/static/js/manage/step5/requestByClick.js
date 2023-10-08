async function save() {
    if (checkInputValidation(defaultForm)) {
        return;
    }

    let inputData = getFormJson(defaultForm, chkParams);
    const request = new RequestHandler(defaultUrl + 'mileageSave'
        , 'POST'
        , inputData);
    const responseData = await request.fetchRequest();

    alert(responseData);
}

function retrieval() {

}