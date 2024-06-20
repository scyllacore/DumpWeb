class Step10GroupHandler {

    requestHandler = new RequestHandler();
    jsonHandler = new JsonHandler();
    urlHandler = new UrlHandler();
    objHandler = new ObjectHandler();
    htmlModifier = new HtmlModifier();

    tagNames = ['titles', 'companies'];
    groupReportKey = `
                        <th>No</th>
                        <th class="groupCompany">제출처</th>
                        <th class="groupTitle">제목</th>
    `;

    constructor() {
        this.run();
    }

    async run() {
        this.redirectByDriveReportId();
        await this.recommendKeywordRetrieval();
    }


    redirectByDriveReportId() {
        this.urlHandler.redirectByElementData('/manage/step9', 'group-report-tuple', 'groupReportId');
    }

    createGroupDriveReportFormObj() {
        return this.objHandler.createFormObj('groupSearchOptionForm');
    }

    async groupReportsRetrieval() {
        const requestObj = this.createGroupDriveReportFormObj();
        this.objHandler.changeOnToTrue(requestObj);

        const responseData = await this.requestHandler.post('/manage/step10' + '/fetch' + '/groupDriveReportList'
            , this.jsonHandler.convertObjectToJson(requestObj));


        this.objHandler.selectElementByClass('group-report-key').innerHTML = this.groupReportKey;
        this.htmlModifier.printList('group-report-key', 'group-report-tuple', responseData,'groupReportId');

        const tBodyEleChild = this.objHandler.selectElementByClass('group-report-tuple').children;

        responseData.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'groupReportId', data['groupReportId']);
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'groupUserType', data['groupUserType']);

            if(tBodyEleChild[idx].getAttribute('data-' + 'groupUserType') === '1'){
                tBodyEleChild[idx].classList.add("user-type-background");
                console.log(tBodyEleChild[idx]);
            };
        })

    }

    async recommendKeywordRetrieval() {
        const responseData = await new RequestHandler()
            .get('/manage/step10' + '/fetch' + '/recommendKeywordList');

        for (const name of this.tagNames) {
            this.inputSearchOption(responseData[name], name);
        }

        this.setDisplayToNone();
        
    }

    inputSearchOption(listData, name) {
        const searchEle = this.objHandler.selectElementByName(name);
        const inputEle = searchEle.parentElement.querySelector('input');

        inputEle.addEventListener('click', (event) => {
            const matchDataList = listData.filter((data) => data.includes(inputEle.value));
            this.printSearchOptionToDiv(matchDataList, inputEle);
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
        document.addEventListener("click",(event) =>{
            for (const name of this.tagNames) {
                const divElement = this.objHandler.selectElementByName(name);
                divElement.style.display = 'none';
            }
        })

        for (const name of this.tagNames) {
            const divElement = this.objHandler.selectElementByName(name);
            divElement.addEventListener("click", (event) => {
                divElement.style.display = 'none';
                event.target.parentElement.parentElement.children[0].value = event.target.innerHTML;
            });
        }
    }

    async modifyPaymentChkInBulk(type) {
        const requestObj = this.createSearchOptionObj();
        requestObj['paymentBtnFlag'] = type;

        const responseData = await this.requestHandler
            .put('/manage/step10' + '/fetch' + '/paymentInBulk', this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
    }

    createSearchOptionObj() {
        return this.objHandler.createFormObj('groupSearchOptionForm');
    }
}