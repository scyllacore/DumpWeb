async function save() {
    if (checkInputValidation(defaultForm)) {
        return;
    }

    setDisabledFalse(defaultForm);

    let inputData = getRequestJson(defaultForm, chkParams);

    const request = new RequestHandler(defaultUrl + '/fetch/mileageSave'
        , 'POST'
        , inputData);
    const responseData = await request.fetchRequest();

    alert(responseData);
    location.href = '/manage/step5';
}

async function remove() {
    let inputData = getRequestJson('mileageId');

    const request = new RequestHandler(defaultUrl + '/fetch/mileageRemove'
        , 'DELETE'
        , inputData);
    const responseData = await request.fetchRequest();

    alert(responseData);
    location.href = '/manage/step5';
}

async function retrieval() {
    let inputData = getRequestJson('driveDate');

    const request = new RequestHandler(defaultUrl + '/fetch/mileageList'
        , 'POST'
        , inputData);
    const responseData = await request.fetchRequest();

    console.log(responseData);
    printList(responseData,'driveDate',idName);
}