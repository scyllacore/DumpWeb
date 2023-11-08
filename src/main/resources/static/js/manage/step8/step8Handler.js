class Step8Handler {

    requestHandler = new RequestHandler();
    jsonHandler = new JsonHandler();
    urlHandler = new UrlHandler();
    objHandler = new ObjectHandler();
    htmlModifier = new HtmlModifier();

    tagNames = ['carNos', 'fromSites', 'toSites', 'items', 'progressType'];
    driveReportKey = `
                        <th>No</th>
                        <th class="driveDate">날짜</th>
                        <th class="carNo">차량번호</th>
                        <th class="fromSite">상차지</th>
                        <th class="toSite">하차지</th>
                        <th class="item">품목</th>
                        <th class="quantity">대수</th>
                        <th class="progress">상태</th>
                        <th class="submitterPaymentChk">제출처 확인</th>
    `;

    constructor() {
        this.run();
    }

    async run() {
        this.redirectByDriveReportId();
        await this.recommendKeywordRetrieval();
    }


    redirectByDriveReportId() {
        this.urlHandler.redirectByElementData('/manage/step7', 'drive-report-tuple', 'driveReportId');
    }

    async driveReportsRetrieval() {
        const requestObj = this.createSearchOptionObj();

        const responseData = await this.requestHandler.post('/manage/step8' + '/fetch' + '/driveReportList'
            , this.jsonHandler.convertObjectToJson(requestObj));

        this.objHandler.selectElementByClass('drive-report-key').innerHTML = this.driveReportKey;
        this.htmlModifier.moveColumnToTheFront(requestObj.sortingCriteria);
        this.htmlModifier.printList('drive-report-key', 'drive-report-tuple', responseData);
        this.htmlModifier.addRedLineToTableByDifferentValue('drive-report-tuple');

        const tBodyEleChild = this.objHandler.selectElementByClass('drive-report-tuple').children;

        responseData.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'driveReportId', data['driveReportId']);
        })
    }

    createSearchOptionObj() {
        return this.objHandler.createFormObj('searchOptionForm');
    }

    async recommendKeywordRetrieval() {
        const responseData = await new RequestHandler()
            .get('/manage/step8' + '/fetch' + '/recommendKeywordList');

        responseData['progressType'] = ['전체', '배차', '제출', '취소'];

        for (const name of this.tagNames) {
            this.inputSearchOption(responseData[name], name);
        }

        this.setDisplayToNone();
    }

    inputSearchOption(listData, name) {
        const searchEle = this.objHandler.selectElementByName(name);
        const inputEle = searchEle.parentElement.querySelector('input');

        inputEle.addEventListener('click', (event) => {
            this.printSearchOptionToDiv(listData, inputEle);
            this.setOneDisplayToBlock(name);
            event.stopPropagation();
        });

        inputEle.addEventListener('keyup', (event) => {
            const matchDataList = listData.filter((data) => data.includes(inputEle.value));
            this.setOneDisplayToBlock(name);
            this.printSearchOptionToDiv(matchDataList, inputEle);
            event.stopPropagation();
        });
    }

    setOneDisplayToBlock(pick) {
        for (const name of this.tagNames) {
            if (name !== pick) {
                this.objHandler.selectElementByName(name).style.display = 'none';
            }
        }
    }


    printSearchOptionToDiv(listData, inputEle) {
        const ele = inputEle.parentElement.querySelector('div');

        if (listData.length === 0) {
            ele.style.display = 'none';
            return;
        }

        ele.innerHTML = ``;
        ele.style.display = 'block';

        for (const val of listData) {
            ele.innerHTML += `<option>` + val + `</option>`
        }
    }

    setDisplayToNone() {
        document.addEventListener("click", (event) => {
            if (event.target.tagName !== 'OPTION') {
                return;
            }

            event.target.parentElement.parentElement.children[0].value = event.target.innerHTML;
        })

        document.addEventListener("click", () => {
            for (const name of this.tagNames) {
                const divElement = this.objHandler.selectElementByName(name);
                divElement.style.display = 'none';
            }
        })
    }

    async modifyPaymentChkInBulk(type) {
        const requestObj = this.createSearchOptionObj();
        requestObj['paymentBtnFlag'] = type;

        const responseData = await this.requestHandler
            .put('/manage/step8' + '/fetch' + '/paymentInBulk', this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
    }
}