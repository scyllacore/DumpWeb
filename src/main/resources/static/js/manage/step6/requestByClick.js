async function retrieval() {
    let inputData = getRequestJson('optionForm');

    const request = new RequestHandler(defaultUrl + '/fetch/mileageList'
        , 'POST'
        , inputData);
    const responseData = await request.fetchRequest();

    let inputObj = JSON.parse(inputData);

    printList(responseData,inputObj.sortingCriteria,idName);
}