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
        await this.loadInputDataByUrlParams();
        this.redirectByDriveReportId();
        this.handleInputActiveByPaymentChk();
        this.inputSubmitter()
    }

    handleInputActiveByPaymentChk() {
        if (this.inputHandler.getChecked('paymentChk')) {
            this.inputActiveHandler.disableInputs(this.activeInputElementNames);
        }

        const chkBox = this.objHandler.selectElementByName('paymentChk');

        chkBox.addEventListener('change', event => {
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
        const inputData = await this.requestHandler
            .post('/manage/step3' + '/fetch' + '/driveReportDetails', reqData);

        this.inputHandler.fillInput(inputData);
    }

    redirectByDriveReportId() {
        this.urlHandler.redirectByElementData('/manage/step3', 'drive-report-tuple', 'driveReportId');
    }

    createDriveReportFormObj() {
        return this.objHandler.createFormObj('driveReportForm');
    }

    async save() {
        if (this.checkSaveValidation()) {
            return;
        }

        this.inputActiveHandler.activateInputs(this.activeInputElementNames);

        const requestObj = this.createDriveReportFormObj();

        this.objHandler.changeOnToTrue(requestObj);

        const responseData = await this.requestHandler.post('/manage/step3' + '/fetch' + '/driveReportSave'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = '/manage/step3';
    }

    checkSaveValidation() {
        const requiredInputs = document.querySelectorAll('input[required]');
        const requiredNames = [];

        requiredInputs.forEach(input => {
            requiredNames.push(input.name);
        })

        for (let name of requiredNames) {
            if (this.inputHandler.checkValidInput(name)) {
                return true;
            }
        }

        return false;
    }

    async remove(containerKey) {
        this.inputActiveHandler.activateInputs(this.activeInputElementNames);

        const requestObj = this.createDriveReportFormObj();

        const responseData = await this.requestHandler.post('/manage/step3' + '/fetch' + '/driveReportRemove'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = '/manage/step3';
    }

    async driveReportsRetrieval() {
        const requestObj = this.createDriveReportFormObj();

        this.objHandler.changeOnToTrue(requestObj);

        const responseData = await this.requestHandler.post('/manage/step3' + '/fetch' + '/driveReportList'
            , this.jsonHandler.convertObjectToJson(requestObj));

        this.htmlModifier.printList('drive-report-key', 'drive-report-tuple', responseData);

        const tBodyEleChild = this.objHandler.selectElementByClass('drive-report-tuple').children;

        responseData.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'driveReportId', data['driveReportId']);
        })
    }

    async receiverListRetrieval() {
        const responseData = await this.requestHandler
            .get('/manage/step3' + '/fetch' + '/submitterList');

        this.htmlModifier.printList('submitter-key', 'submitter-tuple', responseData);

        const tBodyEleChild = this.objHandler.selectElementByClass('submitter-tuple').children;

        responseData.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'submitterId', data['submitterId']);
        })
    }

    inputSubmitter() {
        const tableEle = this.objHandler.selectElementByClass('submitter-tuple');

        tableEle.addEventListener("click", (event) => {
            const parentEle = event.target.parentElement;
            const submitterId = parentEle.getAttribute('data-' + 'submitterId');
            const submitterTel = parentEle.children[2].innerHTML;

            this.objHandler.selectElementByName('submitterIdFk').value = submitterId;
            this.objHandler.selectElementByName('receiver').value = submitterTel;

            popUpHandler.closePopUp('submitter-search-result');
        });
    }

}