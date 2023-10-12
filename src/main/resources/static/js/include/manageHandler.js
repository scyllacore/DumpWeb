class ManageHandler {

    inputHandler = new InputHandler();
    activeInputHandler = {};
    responseHandler = new ResponseHandler();

    constructor(paramContainer) {
        this.paramContainer = paramContainer;
        this.run();
    }

    async run() {
        for (const key in this.paramContainer) {
            const paramObj = this.paramContainer[key];

            if(typeof paramObj.dataIdNames !== 'undefined') {
                paramObj.dataIdNamesAddingSuffix = paramObj.dataIdNames + 'Id';
            }

            await this.inputHandler.inputDataByUrlParams(paramObj.url + '/fetch/' + paramObj.dataIdNames + 'Details'
                , paramObj.dataIdNamesAddingSuffix);

            if(typeof paramObj.redirectUrl !== 'undefined') {
                this.inputHandler.urlHandler.redirectByElementValue(paramObj.redirectUrl
                    , paramObj.dataIdNamesAddingSuffix);
            }

            if (typeof paramObj.inputElementNames !== 'undefined') {
                const activeInputInfo = new ActiveInputInfo(paramObj.inputElementNames, paramObj.activeInputConfigParams);
                this.activeInputHandler[key] = new ActiveInputHandler(activeInputInfo);
                this.activeInputHandler[key].activateInput();
            }
        }
    }

    async save(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        if (this.inputHandler.checkValidation(paramObj.form)) {
            return;
        }
        this.activeInputHandler[containerKey].setDisabledFalse(paramObj.form);

        let inputData = this.inputHandler.jsonHandler
            .getRequestJson(paramObj.form, paramObj.checkBoxElement);


        const requestHandler = new RequestHandler(paramObj.url + '/fetch/' + paramObj.dataIdNames + 'Save'
            , 'POST', inputData);
        const responseData = await requestHandler.fetchRequest();

        alert(responseData);
        location.href = paramObj.url;
    }

    async remove(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        const inputData = this.inputHandler.jsonHandler.getRequestJson(paramObj.dataIdNames + 'Id');

        const requestHandler = new RequestHandler(paramObj.url + '/fetch/' + paramObj.dataIdNames + 'Remove'
            , 'DELETE'
            , inputData);

        const responseData = await requestHandler.fetchRequest();
        alert(responseData);
        location.href = paramObj.url;
    }

    async listRetrieval(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        let inputData = this.inputHandler.jsonHandler.getRequestJson(paramObj.form, paramObj.checkBoxElement);

        const requestHandler = new RequestHandler(paramObj.url + '/fetch/' + paramObj.dataIdNames + 'List'
            , 'POST'
            , inputData);
        const responseData = await requestHandler.fetchRequest();

        let inputObj = JSON.parse(inputData);

        const summaryElementClassName = 'summary-wrapper';
        this.responseHandler.printListSummary(responseData, summaryElementClassName);

        if (typeof inputObj.sortingCriteria === 'undefined') {
            inputObj.sortingCriteria = paramObj.defaultSortingCriteria;
        }

        this.responseHandler.printList(responseData
            , paramObj.listElementClassNames
            , inputObj.sortingCriteria
            , paramObj.dataIdNamesAddingSuffix);

    }

    async modifyPaymentChkInBulk(containerKey, type) {
        const paramObj = this.paramContainer[containerKey];

        if (type === 'approve') {
            type = true;
        } else {
            type = false;
        }

        let inputData = this.inputHandler.jsonHandler.getRequestJson('optionForm');
        inputData.push(JSON.stringify({paymentBtnFlag: type}));

        const requestHandler = new RequestHandler(paramObj.url + '/fetch/' + 'paymentInBulk'
            , 'POST'
            , inputData)
        const responseData = await requestHandler.fetchRequest();
        alert(responseData);
    }
}