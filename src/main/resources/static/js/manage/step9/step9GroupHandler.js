class Step9GroupHandler {

    requestHandler = new RequestHandler();
    inputHandler = new InputHandler();
    jsonHandler = new JsonHandler();
    urlHandler = new UrlHandler();
    inputActiveHandler = new InputActiveHandler();
    objHandler = new ObjectHandler();
    htmlModifier = new HtmlModifier();

    activeInputElementNames = ['groupSubmitterRetrievalBtn', 'groupReportRetrievalBtn', 'groupTitle', 'groupMemo', 'newDriveReport']

    groupList = [];

    constructor() {
        this.run();
    }

    async run() {
        await this.loadInputDataByUrlParams();
        this.redirectByDriveReportId();
        this.handleInputActiveByGroupPaymentChk();
        this.inputSubmitter()
        this.inputDriveReport();
    }

    handleInputActiveByGroupPaymentChk() {
        if (this.inputHandler.getChecked('groupPaymentChk')) {
            this.inputActiveHandler.disableInputs(this.activeInputElementNames);
        }

        const chkBox = this.objHandler.selectElementByName('groupPaymentChk');

        chkBox.addEventListener('change', event => {
            if (this.inputHandler.getChecked('groupPaymentChk')) {
                this.inputActiveHandler.disableInputs(this.activeInputElementNames);
            } else {
                this.inputActiveHandler.activateInputs(this.activeInputElementNames)
            }
        })
    }


    async loadInputDataByUrlParams() {
        const groupReportId = new UrlHandler().getUrlParameterValue('groupReportId');

        if (groupReportId === null) {
            return;
        }

        const reqData = this.jsonHandler.convertObjectToJson({groupReportId: groupReportId});
        const inputData = await this.requestHandler
            .post('/manage/step9' + '/fetch' + '/groupDriveReportDetails', reqData);

        this.inputHandler.fillInput(inputData);
    }

    redirectByDriveReportId() {
        this.urlHandler.redirectByElementData('/manage/step9', 'group-report-tuple', 'groupReportId');
    }

    createGroupDriveReportFormObj() {
        return this.objHandler.createFormObj('groupDriveReportForm');
    }

    async save() {
        if (this.checkSaveValidation()) {
            return;
        }

        this.inputActiveHandler.activateInputs(this.activeInputElementNames);

        const requestObj = this.createGroupDriveReportFormObj();
        this.objHandler.changeOnToTrue(requestObj);

        const responseData = await this.requestHandler.post('/manage/step9' + '/fetch' + '/groupDriveReportSave'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = '/manage/step9'
    }

    checkSaveValidation() {
        const groupDriveReportForm = this.objHandler.selectElementByName('groupDriveReportForm');
        const requiredInputs = groupDriveReportForm.querySelectorAll('input[required]');
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

        const requestObj = this.createGroupDriveReportFormObj();

        const responseData = await this.requestHandler.post('/manage/step9' + '/fetch' + '/groupDriveReportRemove'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = '/manage/step9'
    }

    async groupReportsRetrieval() {
        const requestObj = this.createGroupDriveReportFormObj();
        this.objHandler.changeOnToTrue(requestObj);

        const responseData = await this.requestHandler.post('/manage/step9' + '/fetch' + '/groupDriveReportList'
            , this.jsonHandler.convertObjectToJson(requestObj));

        this.htmlModifier.printList('group-report-key', 'group-report-tuple', responseData);

        const tBodyEleChild = this.objHandler.selectElementByClass('group-report-tuple').children;

        responseData.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'groupReportId', data['groupReportId']);
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

            this.objHandler.selectElementByName('submitterIdFk').value = submitterId;
            this.objHandler.selectElementByName('receiver').value = submitterTel;

            popUpHandler.closePopUp('submitter-search-result');
        });
    }

    async receiverListRetrieval() {
        const responseData = await this.requestHandler
            .get('/manage/step9' + '/fetch' + '/submitterList');

        this.htmlModifier.printList('submitter-key', 'submitter-tuple', responseData);

        const tBodyEleChild = this.objHandler.selectElementByClass('submitter-tuple').children;

        responseData.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'groupSubmitterId', data['groupSubmitterId']);
        })
    }

    inputDriveReport(){
        const tableBody = objHandler.selectElementByClass('group-table-tuple');
        tableBody.addEventListener("click", (event) => {
            const idx = event.target.parentElement.children[0].innerHTML;
            const driveReport = this.groupList[parseInt(idx)-1];
            objHandler.selectElementByName('driveReportIdx').value = parseInt(idx)-1;
            const inputHandler = new InputHandler();

            inputHandler.fillInput(driveReport);
            popUpHandler.openPopUp('drive-report');
        });
    }

}