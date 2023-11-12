class Step9BaseHandler {

    requestHandler = new RequestHandler();
    inputHandler = new InputHandler();
    jsonHandler = new JsonHandler();
    inputActiveHandler = new InputActiveHandler();
    objHandler = new ObjectHandler();
    htmlModifier = new HtmlModifier();

    activeInputElementNames = ['submitterRetrievalBtn', 'driveReportRetrievalBtn', 'driveDate', 'fromSite', 'toSite', 'item', 'quantity', 'unitPrice', 'progress', 'memo']

    constructor() {
        this.run();
    }

    async run() {
        await this.loadInputDataByUrlParams();
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

    createDriveReportFormObj() {
        return this.objHandler.createFormObj('driveReportForm');
    }

    addDriveReport(groupList) {
        if (this.checkSaveValidation()) {
            return true;
        }

        const obj = this.createDriveReportFormObj();
        this.objHandler.changeOnToTrue(obj);

        const idx = objHandler.selectElementByName('driveReportIdx').value;

        if (idx !== '-1') {
            groupList[parseInt(idx)] = obj;
        } else {
            groupList.push(obj);
        }

        return false;
    }

    removeDriveReport(groupList) {
        const idx = this.objHandler.selectElementByName('driveReportIdx').value;

        if (idx === '-1') {
            popUpHandler.closePopUp('drive-report');
            return;
        }

        groupList.splice(idx, 1);
    }


    async loadInputDataByUrlParams() {
        const driveReportId = new UrlHandler().getUrlParameterValue('driveReportId');

        if (driveReportId === null) {
            return;
        }

        const reqData = this.jsonHandler.convertObjectToJson({driveReportId: driveReportId});
        const inputData = await this.requestHandler
            .post('/manage/step9' + '/fetch' + '/driveReportDetails', reqData);

        this.inputHandler.fillInput(inputData);
    }


    checkSaveValidation() {
        const driveReportEle = this.objHandler.selectElementByName('driveReportForm');
        const requiredInputs = driveReportEle.querySelectorAll('input[required]');
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
            .get('/manage/step9' + '/fetch' + '/submitterList');

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

            this.objHandler.selectElementByName('groupSubmitterIdFk').value = submitterId;
            this.objHandler.selectElementByName('groupReceiver').value = submitterTel;

            popUpHandler.closePopUp('submitter-search-result');
        });
    }

}