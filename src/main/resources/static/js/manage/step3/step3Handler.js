class Step3Handler {

    requestHandler = new RequestHandler();
    inputHandler = new InputHandler();
    jsonHandler = new JsonHandler();
    urlHandler = new UrlHandler();
    inputActiveHandler = new InputActiveHandler();
    objHandler = new ObjectHandler();
    htmlModifier = new HtmlModifier();

    constructor() {
        this.run();
    }

    async run() {
        this.loadInputDataByUrlParams();
        this.redirectByDriveReportId();
        this.setInputDisabled();
    }

    async loadInputDataByUrlParams() {
        const driveReportId = new UrlHandler().getUrlParameterValue('driveReportId');

        if (driveReportId === null) {
            return;
        }

        const reqData = this.jsonHandler.convertObjectToJson({driveReportId: driveReportId});
        const inputData = this.requestHandler
            .post('/manage/step3' + '/fetch/' + 'driveReportSave', reqData);

        this.inputHandler.fillInput(inputData);
    }

    async redirectByDriveReportId() {
        this.urlHandler.redirectByElementData('/manage/step3', 'drive-report-tuple', 'driveReportId');
    }

    setInputDisabled() {
        const activeInputElementNames = ['submitterRetrievalBtn', 'driveReportRetrievalBtn', 'driveDate', 'fromSite', 'toSite', 'item', 'quantity', 'unitPrice', 'progress', 'memo']
        this.inputActiveHandler.disableInputsByCheckBox('paymentChk', activeInputElementNames);
    }

    createDriveReportFormObj() {
        return this.objHandler.convertFormIntoObject(
            this.objHandler.selectElementByName('driveReportForm'));
    }

    async save() {
        const requestObj = this.getDriveReportFormObj();

        const responseData = await this.requestHandler.post('/manage/step3' + '/fetch/' + 'driveReportSave'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = '/manage/step3'
    }

    async remove(containerKey) {
        const requestObj = this.createDriveReportFormObj();

        const responseData = await this.requestHandler.post('/manage/step3' + '/fetch/' + 'driveReportRemove'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = defaultParams.url;
    }

    async driveReportsRetrieval() {
        const requestObj = this.createDriveReportFormObj();

        const responseData = await this.requestHandler.post('/manage/step3' + '/fetch/' + 'driveReportList'
            , this.jsonHandler.convertObjectToJson(requestObj));

        this.htmlModifier.moveColumnToTheFront(requestObj.sortingCriteria);
        this.htmlModifier.printList('drive-report-tuple', responseData);
        this.htmlModifier.addRedLineToTableByDifferentValue('drive-report-tuple');
    }

    async receiverListRetrieval() {
        const responseData = await this.requestHandler
            .get('/manage/step3' + '/fetch/' + 'submitterList');

        this.htmlModifier.printList('submitter-tuple', responseData);
    }

}