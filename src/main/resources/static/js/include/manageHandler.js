class ManageHandler {

    inputHandler = new InputHandler();
    activeInputHandler = new ActiveInputHandler();
    responseHandler = new ResponseHandler();

    constructor(defaultUrl = ''
        , defaultForm = ''
        , activeInputInfo = new ActiveInputInfo()
        , checkBoxElement = []
        , dataIdNames = []
        , listElementClassNames = []
    ) {
        this.defaultUrl = defaultUrl;
        this.defaultForm = defaultForm;
        this.activeInputHandler = new ActiveInputHandler(activeInputInfo);
        this.checkBoxElement = checkBoxElement;
        this.dataIdNames = dataIdNames;
        this.listElementClassNames = listElementClassNames;

        this.inputHandler.inputDataByUrlParams(defaultUrl + '/fetch/' + dataIdNames + 'Details', dataIdNames + 'Id');
        this.activeInputHandler.activateInput();
        this.inputHandler.urlHandler.redirectByElementValue(defaultUrl, dataIdNames + 'Id');
    }

    async save() {
        if (this.inputHandler.checkValidation(this.defaultForm)) {
            return;
        }
        this.activeInputHandler.setDisabledFalse(this.defaultForm);

        let inputData = this.inputHandler.jsonHandler.getRequestJson(this.defaultForm, this.checkBoxElement);


        const requestHandler = new RequestHandler(defaultUrl + '/fetch/' + dataIdNames + 'Save'
            , 'POST', inputData);
        const responseData = await requestHandler.fetchRequest();

        alert(responseData);
        location.href = this.defaultUrl;
    }

    async remove() {
        const inputData = this.inputHandler.jsonHandler.getRequestJson(this.dataIdNames + 'Id');

        const requestHandler = new RequestHandler(defaultUrl + '/fetch/' + this.dataIdNames + 'Remove'
            , 'DELETE'
            , inputData);

        const responseData = await requestHandler.fetchRequest();
        alert(responseData);
        location.href = this.defaultUrl;
    }

    async listRetrieval() {
        let inputData = this.inputHandler.jsonHandler.getRequestJson(this.defaultForm);

        const summaryElementClassName = 'summary-wrapper';

        const requestHandler = new RequestHandler(this.defaultUrl + '/fetch/' + this.dataIdNames + 'List'
            , 'POST'
            , inputData);
        const responseData = await requestHandler.fetchRequest();

        let inputObj = JSON.parse(inputData);

        this.responseHandler.printListSummary(responseData, summaryElementClassName);

        if (typeof inputObj.sortingCriteria === 'undefined') {
            inputObj.sortingCriteria = defaultSortingCriteria;
        }

        for (let idx in this.listElementClassNames) {
            this.responseHandler.printList(responseData, this.listElementClassNames[idx], inputObj.sortingCriteria, this.dataIdNames);
        }
    }
}