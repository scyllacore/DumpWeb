class Step5Handler {

    requestHandler = new RequestHandler();
    inputHandler = new InputHandler();
    jsonHandler = new JsonHandler();
    urlHandler = new UrlHandler();
    inputActiveHandler = new InputActiveHandler();
    objHandler = new ObjectHandler();
    htmlModifier = new HtmlModifier();

    activeInputElementNames1 = ['retrievalBtn', 'driveDate', 'item', 'lastKm', 'usedAmount', 'usedOil', 'memo', 'replActiveChk', 'replDate', 'replKm', 'replChk']
    activeInputElementNames2 = ['replDate', 'replKm', 'replChk']


    constructor() {
        this.run();
    }

    async run() {
        this.setDefaultDate();
        await this.loadInputDataByUrlParams();
        this.redirectByMileageId();
        this.handleInputActiveByPaymentChk();
    }

    setDefaultDate(){
        const today = new Date().toISOString().slice(0, 10);

        this.objHandler.selectElementByName('driveDate').value = today;
    }

    handleInputActiveByPaymentChk() {
        if (this.inputHandler.getChecked('paymentChk')) {
            this.inputActiveHandler.disableInputs(this.activeInputElementNames1);
        }
        if (this.inputHandler.getChecked('replActiveChk')) {
            this.inputActiveHandler.activateInputs(this.activeInputElementNames2);
        }

        const chkBox1 = this.objHandler.selectElementByName('paymentChk');
        const chkBox2 = this.objHandler.selectElementByName('replActiveChk');

        chkBox1.addEventListener('change', event => {
            if (this.inputHandler.getChecked('paymentChk')) {
                this.inputActiveHandler.disableInputs(this.activeInputElementNames1);
            } else {
                this.inputActiveHandler.activateInputs(this.activeInputElementNames1);
            }
            this.inputActiveHandler.disableChildInputsByCheckBoxesNotChecked
                ('paymentChk','replActiveChk',this.activeInputElementNames2);
        })

        chkBox2.addEventListener('change', event => {
            if (this.inputHandler.getChecked('replActiveChk')) {
                this.inputActiveHandler.activateInputs(this.activeInputElementNames2);
            } else {
                this.inputActiveHandler.disableInputs(this.activeInputElementNames2);
            }
        })
    }

    async loadInputDataByUrlParams() {
        const mileageId = new UrlHandler().getUrlParameterValue('mileageId');

        if (mileageId === null) {
            return;
        }

        const reqData = this.jsonHandler.convertObjectToJson({mileageId: mileageId});
        const inputData = await this.requestHandler
            .post('/manage/step5' + '/fetch' + '/mileageDetails', reqData);

        this.inputHandler.fillInput(inputData);
    }

    redirectByMileageId() {
        this.urlHandler.redirectByElementData('/manage/step5', 'mileage-tuple', 'mileageId');
    }

    createMileageFormObj() {
        return this.objHandler.createFormObj('mileageForm');
    }

    async save() {
        if (this.checkSaveValidation()) {
            return;
        }

        this.inputActiveHandler.activateInputs(this.activeInputElementNames1);

        const requestObj = this.createMileageFormObj();
        this.objHandler.changeOnToTrue(requestObj);

        const responseData = await this.requestHandler.post('/manage/step5' + '/fetch' + '/mileageSave'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = '/manage/step5'
    }

    checkSaveValidation() {
        const requiredInputs = document.querySelectorAll('input[required]');
        const requiredNames = [];

        requiredInputs.forEach(input => {
            requiredNames.push(input.name);
        })

        for(let name of requiredNames){
            if (this.inputHandler.checkValidInput(name)) {
                return true;
            }
        }

        return false;
    }

    async remove() {
        this.inputActiveHandler.activateInputs(this.activeInputElementNames1);

        const requestObj = this.createMileageFormObj();

        const responseData = await this.requestHandler.post('/manage/step5' + '/fetch' + '/mileageRemove'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = defaultParams.url;
    }

    async mileageRetrieval() {
        const requestObj = this.createMileageFormObj();
        this.objHandler.changeOnToTrue(requestObj);

        const responseData = await this.requestHandler.post('/manage/step5' + '/fetch' + '/mileageList'
            , this.jsonHandler.convertObjectToJson(requestObj));

        this.htmlModifier.printList('mileage-key', 'mileage-tuple', responseData,'mileageId');

        const tBodyEleChild = this.objHandler.selectElementByClass('mileage-tuple').children;

        responseData.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'mileageId', data['mileageId']);
        })
    }

}