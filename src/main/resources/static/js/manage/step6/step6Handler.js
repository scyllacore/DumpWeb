class Step6Handler {

    requestHandler = new RequestHandler();
    jsonHandler = new JsonHandler();
    urlHandler = new UrlHandler();
    objHandler = new ObjectHandler();
    htmlModifier = new HtmlModifier();

    mileageKey = `
                        <th>No</th>
                        <th class="driveDate">날짜</th>
                        <th class="item">품목</th>
                        <th class="lastKm">최종주행(km)</th>
                        <th class="usedAmount">사용금액(원)</th>
                        <th class="memo">기타 (설명)</th>
                        <th class="replActiveChk">교환 주기</th>
    `

    constructor() {
        this.run();
    }

    async run() {
        this.redirectByMileageId();
    }

    redirectByMileageId() {
        this.urlHandler.redirectByElementData('/manage/step5', 'mileage-tuple', 'mileageId');
    }

    createMileageFormObj() {
        return this.objHandler.createFormObj('searchOptionForm');
    }

    async mileageRetrieval() {
        const requestObj = this.createMileageFormObj();
        this.objHandler.changeOnToTrue(requestObj);

        const responseData = await this.requestHandler.post('/manage/step6' + '/fetch' + '/mileageList'
            , this.jsonHandler.convertObjectToJson(requestObj));

        this.objHandler.selectElementByClass('mileage-key').innerHTML = this.mileageKey;
        this.htmlModifier.moveColumnToTheFront(requestObj.sortingCriteria);
        this.htmlModifier.printList('mileage-key', 'mileage-tuple', responseData);
        this.htmlModifier.addRedLineToTableByDifferentValue('mileage-tuple');

        const tBodyEleChild = this.objHandler.selectElementByClass('mileage-tuple').children;

        responseData.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'mileageId', data['mileageId']);
        })
    }

    async modifyPaymentChkInBulk(type) {
        const requestObj = this.createMileageFormObj();
        requestObj['paymentBtnFlag'] = type;

        const responseData = await this.requestHandler
            .put('/manage/step6' + '/fetch' + '/paymentInBulk', this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
    }
}