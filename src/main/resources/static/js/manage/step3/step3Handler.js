class Step3Handler {

    requestHandler = new RequestHandler();
    inputHandler = new InputHandler();
    jsonHandler = new JsonHandler();
    urlHandler = new UrlHandler();
    inputActiveHandler = new InputActiveHandler();

    constructor() {
        this.run();
    }

    async run() {


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


}