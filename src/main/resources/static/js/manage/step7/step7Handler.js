class Step7Handler {

    requestHandler = new RequestHandler();
    inputHandler = new InputHandler();
    jsonHandler = new JsonHandler();
    urlHandler = new UrlHandler();
    inputActiveHandler = new InputActiveHandler();
    objHandler = new ObjectHandler();
    htmlModifier = new HtmlModifier();

    activeInputElementNames = ['driverRetrievalBtn', 'driveReportRetrievalBtn', 'driveDate', 'fromSite', 'toSite', 'item', 'quantity', 'unitPrice', 'memo', 'postingChk'];


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
            .post('/manage/step7' + '/fetch' + '/driveReportDetails', reqData);

        this.inputHandler.fillInput(inputData);
    }

    redirectByDriveReportId() {
        this.urlHandler.redirectByElementData('/manage/step7', 'drive-report-tuple', 'driveReportId');
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

        const responseData = await this.requestHandler.post('/manage/step7' + '/fetch' + '/driveReportSave'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = '/manage/step7'
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

    async remove() {
        this.inputActiveHandler.activateInputs(this.activeInputElementNames);

        const requestObj = this.createDriveReportFormObj();

        const responseData = await this.requestHandler.post('/manage/step7' + '/fetch' + '/driveReportRemove'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = defaultParams.url;
    }

    async driveReportsRetrieval() {
        const requestObj = this.createDriveReportFormObj();
        this.objHandler.changeOnToTrue(requestObj);

        const responseData = await this.requestHandler.post('/manage/step7' + '/fetch' + '/driveReportList'
            , this.jsonHandler.convertObjectToJson(requestObj));

        this.htmlModifier.printList('drive-report-key', 'drive-report-tuple', responseData);

        const tBodyEleChild = this.objHandler.selectElementByClass('drive-report-tuple').children;

        responseData.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'driveReportId', data['driveReportId']);
        })
    }

    async receiverListRetrieval() {
        const responseData = await this.requestHandler
            .get('/manage/step7' + '/fetch' + '/driverList');

        this.htmlModifier.printList('driver-key', 'driver-tuple', responseData);

        const tBodyEleChild = this.objHandler.selectElementByClass('driver-tuple').children;

        responseData.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'driverId', data['driverId']);
        })
    }

    inputSubmitter() {
        const tableEle = this.objHandler.selectElementByClass('driver-tuple');

        tableEle.addEventListener("click", (event) => {
            const parentEle = event.target.parentElement;
            const driverId = parentEle.getAttribute('data-' + 'driverId');
            const driverTel = parentEle.children[1].innerHTML;

            this.objHandler.selectElementByName('driverIdFk').value = driverId;
            this.objHandler.selectElementByName('receiver').value = driverTel;

            popUpHandler.closePopUp('driver-search-result');
        });
    }

}