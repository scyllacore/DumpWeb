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

            paramObj.dataIdNameAddingSuffix = paramObj.dataIdName + 'Id';

            await this.inputHandler.inputDataByUrlParams(defaultParams.url + '/fetch/' + paramObj.dataIdName + 'Details'
                , paramObj.dataIdNameAddingSuffix);


            this.inputHandler.urlHandler.redirectByElementValue(paramObj.redirectUrl
                , paramObj.dataIdNameAddingSuffix);

            activateInput(this.activeInputHandler, key, paramObj);

            this.recommendKeywordRetrieval(defaultParams.url, paramObj);
        }

        function activateInput(activeInputHandler, key, paramObj) {
            if (typeof paramObj.activeInputElementNames === 'undefined') {
                return;
            }

            const activeInputInfo =
                new ActiveInputInfo(paramObj.activeInputElementNames, paramObj.activeInputConfigParams);
            activeInputHandler[key] = new ActiveInputHandler(activeInputInfo);
            activeInputHandler[key].activateInput();
        }
    }

    async save(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        if (this.inputHandler.checkValidation(paramObj.formName)) {
            return;
        }
        this.activeInputHandler[containerKey].setDisabledFalse(paramObj.formName);

        let inputData = this.inputHandler.jsonHandler
            .getRequestJson(paramObj.formName, paramObj.inputCheckBoxElements);

        const responseData = await this.requestHandler
            .post(defaultParams.url + '/fetch/' + paramObj.dataIdName + 'Save', inputData);
        alert(responseData);

        location.href = defaultParams.url;
    }

    async remove(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        const inputData = this.inputHandler.jsonHandler.getRequestJson(paramObj.dataIdName + 'Id');

        const responseData = await this.requestHandler
            .delete(defaultParams.url + '/fetch/' + paramObj.dataIdName + 'Remove', inputData);

        alert(responseData);
        location.href = defaultParams.url;
    }

    async listRetrieval(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        let inputData = this.inputHandler.jsonHandler.getRequestJson(paramObj.formName, paramObj.inputCheckBoxElements);

        const responseData = await this.requestHandler
            .post(defaultParams.url + '/fetch/' + paramObj.dataIdName + 'List', inputData);

        let inputObj = JSON.parse(inputData);

        const summaryElementClassName = 'list-summary-wrapper';
        this.responseHandler.printListSummary(responseData, summaryElementClassName);

        if (typeof inputObj.sortingCriteria === 'undefined') {
            inputObj.sortingCriteria = paramObj.defaultSortingCriteria;
        }

        this.responseHandler.printList(responseData
            , paramObj.listElementClassName
            , inputObj.sortingCriteria
            , paramObj.dataIdNameAddingSuffix);

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
            .put(defaultParams.url + '/fetch/' + 'paymentInBulk', inputData);

        alert(responseData);
    }

    async submitterListRetrieval(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        const responseData = await this.requestHandler
            .get(defaultParams.url + '/fetch/' + paramObj.dataIdName + 'List');

        this.responseHandler.printSubmitterList(responseData
            , paramObj.listElementClassName
            , paramObj.defaultSortingCriteria
            , paramObj.dataIdNameAddingSuffix);
    }

    async recommendKeywordRetrieval(url, paramObj) {
        if (typeof paramObj.recommendKeywordList === 'undefined') {
            return;
        }

        const responseData = await new RequestHandler()
            .get(url + '/fetch/' + 'recommendKeyword' + 'List');

        this.responseHandler.printRecommendKeywordList(responseData);
    }

}