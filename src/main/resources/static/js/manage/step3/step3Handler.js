class Step3Handler {

    requestHandler = new RequestHandler();
    inputHandler = new InputHandler();
    jsonHandler = new JsonHandler();
    urlHandler = new UrlHandler();
    inputActiveHandler = new InputActiveHandler();
    objHandler = new ObjectHandler();
    htmlModifier = new HtmlModifier();

    activeInputElementNames = ['submitterRetrievalBtn', 'driveReportRetrievalBtn', 'driveDate', 'fromSite', 'toSite', 'item', 'quantity', 'unitPrice', 'progress', 'memo']


    constructor() {
        this.run();
    }

    async run() {
        this.loadInputDataByUrlParams();
        this.redirectByDriveReportId();
        this.handleInputActiveByPaymentChk()
    }

    handleInputActiveByPaymentChk() {
        if (this.inputHandler.getChecked('paymentChk')) {
            this.inputActiveHandler.disableInputs(this.activeInputElementNames);
        }

        document.addEventListener('change', event => {
            if (this.inputHandler.getChecked('paymentChk')) {
                this.inputActiveHandler.disableInputs(this.activeInputElementNames);
            } else {
                this.inputActiveHandler.activateInputs(this.activeInputElementNames)
            }
        })
    }


    async loadInputDataByUrlParams() {
        const driveReportId = new UrlHandler().getUrlParameterValue('driveReportId');

        if (driveReportId === null) {
            return;
        }

        const reqData = this.jsonHandler.convertObjectToJson({driveReportId: driveReportId});
        const inputData = this.requestHandler
            .post('/manage/step3' + '/fetch' + '/driveReportSave', reqData);

        this.inputHandler.fillInput(inputData);
    }

    async redirectByDriveReportId() {
        this.urlHandler.redirectByElementData('/manage/step3', 'drive-report-tuple', 'driveReportId');
    }

    createDriveReportFormObj() {
        return this.objHandler.createFormObj('driveReportForm');
    }

    async save() {
        this.inputActiveHandler.activateInputs(this.activeInputElementNames);

        const requestObj = this.createDriveReportFormObj();

        const responseData = await this.requestHandler.post('/manage/step3' + '/fetch' + '/driveReportSave'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = '/manage/step3'
    }

    async remove(containerKey) {
        this.inputActiveHandler.activateInputs(this.activeInputElementNames);

        const requestObj = this.createDriveReportFormObj();

        const responseData = await this.requestHandler.post('/manage/step3' + '/fetch' + '/driveReportRemove'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = defaultParams.url;
    }

    async driveReportsRetrieval() {
        const requestObj = this.createDriveReportFormObj();

        const responseData = await this.requestHandler.post('/manage/step3' + '/fetch' + '/driveReportList'
            , this.jsonHandler.convertObjectToJson(requestObj));

        console.log(responseData);

/*        this.htmlModifier.moveColumnToTheFront('reportNo');*/
        this.htmlModifier.printList('drive-report-key','drive-report-tuple', responseData);
/*        this.htmlModifier.addRedLineToTableByDifferentValue('drive-report-tuple');*/
    }

    async receiverListRetrieval() {
        const responseData = await this.requestHandler
            .get('/manage/step3' + '/fetch' + '/submitterList');

        this.htmlModifier.printList('submitter-key','submitter-tuple', responseData);
    }

}