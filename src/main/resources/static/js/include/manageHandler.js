class ManageHandler {

    inputHandler = new InputHandler();
    activeInputHandler = {};
    requestHandler = new RequestHandler();
    responseHandler = new ResponseHandler();

    constructor(paramContainer) {
        this.paramContainer = paramContainer;
        this.run();
    }

    async run() {
        for (const key in this.paramContainer) {
            const paramObj = this.paramContainer[key];

            if (typeof paramObj.dataIdNames !== 'undefined') {
                paramObj.dataIdNamesAddingSuffix = paramObj.dataIdNames + 'Id';
            }

            await this.inputHandler.inputDataByUrlParams(paramObj.url + '/fetch/' + paramObj.dataIdNames + 'Details'
                , paramObj.dataIdNamesAddingSuffix);


            this.inputHandler.urlHandler.redirectByElementValue(paramObj.redirectUrl
                , paramObj.dataIdNamesAddingSuffix);

            activateInput(this.activeInputHandler, key, paramObj);
        }

        function activateInput(activeInputHandler, key, paramObj) {
            if (typeof paramObj.inputElementNames === 'undefined') {
                return;
            }

            const activeInputInfo =
                new ActiveInputInfo(paramObj.inputElementNames, paramObj.activeInputConfigParams);
            activeInputHandler[key] = new ActiveInputHandler(activeInputInfo);
            activeInputHandler[key].activateInput();
        }
    }

    async save(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        if (this.inputHandler.checkValidation(paramObj.form)) {
            return;
        }
        this.activeInputHandler[containerKey].setDisabledFalse(paramObj.form);

        let inputData = this.inputHandler.jsonHandler
            .getRequestJson(paramObj.form, paramObj.checkBoxElements);

        const responseData = await this.requestHandler
            .post(paramObj.url + '/fetch/' + paramObj.dataIdNames + 'Save', inputData);
        alert(responseData);

        location.href = paramObj.url;
    }

    async remove(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        const inputData = this.inputHandler.jsonHandler.getRequestJson(paramObj.dataIdNames + 'Id');

        const responseData = await this.requestHandler
            .delete(paramObj.url + '/fetch/' + paramObj.dataIdNames + 'Remove', inputData);

        alert(responseData);
        location.href = paramObj.url;
    }

    async listRetrieval(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        let inputData = this.inputHandler.jsonHandler.getRequestJson(paramObj.form, paramObj.checkBoxElements);

        const responseData = await this.requestHandler
            .post(paramObj.url + '/fetch/' + paramObj.dataIdNames + 'List', inputData);

        let inputObj = JSON.parse(inputData);

        const summaryElementClassName = 'list-summary-wrapper';
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

        const property = {paymentBtnFlag: type};

        let inputData = this.inputHandler.jsonHandler.getRequestJson('optionForm', undefined, property);

        const responseData = await this.requestHandler
            .put(paramObj.url + '/fetch/' + 'paymentInBulk', inputData);

        alert(responseData);
    }

    async submitterListRetrieval(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        const responseData = await this.requestHandler
            .get(paramObj.url + '/fetch/' + paramObj.dataIdNames + 'List');


        const summaryElementClassName = 'list-summary-wrapper';
        this.responseHandler.printListSummary(responseData, summaryElementClassName);

        this.responseHandler.printSubmitterList(responseData
            , paramObj.listElementClassNames
            , paramObj.defaultSortingCriteria
            , paramObj.dataIdNamesAddingSuffix);
    }

}