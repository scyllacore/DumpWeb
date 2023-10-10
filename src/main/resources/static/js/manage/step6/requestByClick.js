async function retrieval() {
    let inputData = getRequestJson('optionForm');

    const request = new RequestHandler(defaultUrl + '/fetch/mileageList'
        , 'POST'
        , inputData);
    const responseData = await request.fetchRequest();

    let inputObj = JSON.parse(inputData);

    printList(responseData,inputObj.sortingCriteria,idName);
}

async function approveInBulk() {
    let inputData = getRequestJson('optionForm');

    const request = new RequestHandler(defaultUrl + '/fetch/paymentApproval'
        , 'POST'
        , inputData);
    const responseData = await request.fetchRequest();
    alert(responseData);
}

async function cancelInBulk() {
    let inputData = getRequestJson('optionForm');

    const request = new RequestHandler(defaultUrl + '/fetch/paymentCancel'
        , 'POST'
        , inputData);
    const responseData = await request.fetchRequest();
    alert(responseData);
}