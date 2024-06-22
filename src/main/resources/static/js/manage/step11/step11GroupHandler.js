class Step11GroupHandler {

    requestHandler = new RequestHandler();
    inputHandler = new InputHandler();
    jsonHandler = new JsonHandler();
    urlHandler = new UrlHandler();
    inputActiveHandler = new InputActiveHandler();
    objHandler = new ObjectHandler();
    htmlModifier = new HtmlModifier();

    activeInputElementNames = ['groupReceiver', 'groupDriverRetrievalBtn', 'groupReportRetrievalBtn', 'groupTitle', 'groupMemo', 'imageUploadBtn', 'newOrderReport']

    groupList = [];

    uploadingFileData = null;

    constructor() {
        this.run();
    }

    async run() {
        await this.loadInputDataByUrlParams();
        this.redirectByDriveReportId();
        this.handleInputActiveByGroupPaymentChk();
        this.inputDriver();
        this.inputOrderReport();
        this.uploadFile();
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
        const groupReportId = this.urlHandler.getUrlParameterValue('groupReportId');

        if (groupReportId === null) {
            return;
        }

        const reqData = this.jsonHandler.convertObjectToJson({groupReportId: groupReportId});
        const inputData = await this.requestHandler
            .post('/manage/step11' + '/fetch' + '/groupOrderReportDetails', reqData);

        if (inputData['fileIdFk'] !== null) {
            const photoViewEle = objHandler.selectElementByClass('photo-view');
            photoViewEle.src = '/image/' + inputData['fileIdFk'];
            photoViewEle.style.display = 'flex';
        }

        this.groupList = inputData['driveReports'];

        this.inputHandler.fillInput(inputData);

        this.htmlModifier
            .printList('group-table-key', 'group-table-tuple', step11GroupHandler.groupList, 'driveReportId');

        const tBodyEleChild = this.objHandler.selectElementByClass('group-table-tuple').children;

        this.groupList.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'idx', idx);
        })
    }

    redirectByDriveReportId() {
        this.urlHandler.redirectByElementData('/manage/step11', 'group-report-tuple', 'groupReportId');
    }

    createGroupDriveReportFormObj() {
        return this.objHandler.createFormObj('groupOrderReportForm');
    }

    async save() {
        if (this.checkSaveValidation()) {
            return;
        }

        this.inputActiveHandler.activateInputs(this.activeInputElementNames);

        const requestObj = this.createGroupDriveReportFormObj();

        this.objHandler.changeOnToTrue(requestObj);
        requestObj['driveReports'] = this.groupList;

        const requestForm = new FormData();
        requestForm.append('dto', new Blob([this.jsonHandler.convertObjectToJson(requestObj)], {type: 'application/json'}));
        requestForm.append('imageFile', objHandler.selectElementByName('imageFile').files[0]);

        const responseData = await this.requestHandler.postFormData('/manage/step11' + '/fetch' + '/groupOrderReportSave'
            , requestForm);

        alert(responseData);
        location.href = '/manage/step11'
    }

    checkSaveValidation() {
        const groupDriveReportForm = this.objHandler.selectElementByName('groupOrderReportForm');
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

        const responseData = await this.requestHandler.delete('/manage/step11' + '/fetch' + '/groupOrderReportRemove'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = '/manage/step11'
    }

    async groupReportsRetrieval() {
        const requestObj = this.createGroupDriveReportFormObj();
        this.objHandler.changeOnToTrue(requestObj);

        const responseData = await this.requestHandler.post('/manage/step11' + '/fetch' + '/groupOrderReportList'
            , this.jsonHandler.convertObjectToJson(requestObj));

        this.htmlModifier.printList('group-report-key', 'group-report-tuple', responseData, 'groupReportId');

        const tBodyEleChild = this.objHandler.selectElementByClass('group-report-tuple').children;

        responseData.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'groupReportId', data['groupReportId']);
        })
    }

    inputDriver() {
        const tableEle = this.objHandler.selectElementByClass('driver-tuple');

        tableEle.addEventListener("click", (event) => {
            const parentEle = event.target.parentElement;
            const driverId = parentEle.getAttribute('data-' + 'driverId');
            const driverCarNo = parentEle.children[1].innerHTML;

            this.objHandler.selectElementByName('groupDriverIdFk').value = driverId;
            this.objHandler.selectElementByName('groupReceiver').value = driverCarNo;
            this.objHandler.selectElementByName('groupCarNo').value = driverCarNo;

            popUpHandler.closePopUp('driver-search-result');
        });
    }

    async receiverListRetrieval() {
        const responseData = await this.requestHandler
            .get('/manage/step11' + '/fetch' + '/driverList');

        this.htmlModifier.printList('driver-key', 'driver-tuple', responseData, 'driverId');

        const tBodyEleChild = this.objHandler.selectElementByClass('driver-tuple').children;

        responseData.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'driverId', data['driverId']);
        })
    }

    inputOrderReport() {
        const tableBody = objHandler.selectElementByClass('group-table-tuple');
        tableBody.addEventListener("click", (event) => {
            const idx = event.target.parentElement.getAttribute('data-' + 'idx');

            const driveReport = this.groupList[parseInt(idx)];
            objHandler.selectElementByName('driveReportIdx').value = parseInt(idx);
            const inputHandler = new InputHandler();

            inputHandler.fillInput(driveReport);
            popUpHandler.openPopUp('order-report');
        });
    }

    uploadFile() {
        const imageFileInputEle = objHandler.selectElementByName('imageFile');
        const photoViewEle = objHandler.selectElementByClass('photo-view');


        imageFileInputEle.addEventListener('change', function () {
            const fileList = this.files;
            const file = fileList[0];

            if (!file) {
                return;
            }

            photoViewEle.src = URL.createObjectURL(file);
            photoViewEle.style.display = 'flex';
        })
    }


}