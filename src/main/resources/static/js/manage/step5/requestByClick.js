async function save() {
    if (checkInputValidation(defaultForm)) {
        return;
    }

    let inputData = getRequestJson(defaultForm, chkParams);
    const request = new RequestHandler(defaultUrl + 'mileageSave'
        , 'POST'
        , inputData);
    const responseData = await request.fetchRequest();

    alert(responseData);
    location.href = '/manage/step5';
}

async function remove() {
    let inputData = getRequestJson('mileageId');

    const request = new RequestHandler(defaultUrl + 'mileageRemove'
        , 'DELETE'
        , inputData);
    const responseData = await request.fetchRequest();

    alert(responseData);
    location.href = '/manage/step5';
}

async function retrieval() {
    let inputData = getRequestJson('driveDate');

    const request = new RequestHandler(defaultUrl + 'mileageList'
        , 'POST'
        , inputData);
    const responseData = await request.fetchRequest();

    console.log(responseData);
}