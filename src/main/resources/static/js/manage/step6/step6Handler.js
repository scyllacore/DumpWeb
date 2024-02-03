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

    async mileageRetrieval(pageNum) {

        const curPage = this.objHandler.selectElementByName('pageNum');
        curPage.value = (pageNum).toString();

        const requestObj = this.createMileageFormObj();
        this.objHandler.changeOnToTrue(requestObj);

        const responseData = await this.requestHandler.post('/manage/step6' + '/fetch' + '/mileageList'
            , this.jsonHandler.convertObjectToJson(requestObj));


        this.setPage(responseData['pageInfo']);
        this.printMileageTable(requestObj, responseData['mileageList']);
    }

    async printMileageTable(requestObj, mileageList) {

        this.objHandler.selectElementByClass('mileage-key').innerHTML = this.mileageKey;
        this.htmlModifier.moveColumnToTheFront(requestObj.sortingCriteria);
        this.htmlModifier.printList('mileage-key', 'mileage-tuple', mileageList);
        this.htmlModifier.addRedLineToTableByDifferentValue('mileage-tuple');

        const tBodyEleChild = this.objHandler.selectElementByClass('mileage-tuple').children;

        mileageList.forEach((data, idx) => {
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

    setPage(pageInfo) {
        const pageNationEle = objHandler.selectElementByClass('page_nation');

        pageNationEle.innerHTML = `
        <a className="arrow pprev" onclick="func.movePageSet(-1)"></a>
        <a className="arrow prev" onclick="func.moveBothEndsPageSet(1)></a>
        `;

        const curPage = this.objHandler.selectElementByName('pageNum');

        for (let i = pageInfo['startPage']; i <= pageInfo['endPage']; i++) {

            if (parseInt(curPage.value) == i) {
                pageNationEle.innerHTML += `
                <a className="active" onclick="func.mileageRetrieval(${i})">${i}</a>
            `
            } else {
                pageNationEle.innerHTML += `
                <a onclick="func.mileageRetrieval(${i})">${i}</a>
            `
            }
        }

        pageNationEle.innerHTML += `
        <a className="arrow next"  onclick="func.movePageSet(1)"></a>
        <a className="arrow nnext" onclick="func.moveBothEndsPageSet(99999999)"></a>
        `
    }

}