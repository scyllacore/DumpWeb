class Step1Handler {

    requestHandler = new RequestHandler();
    jsonHandler = new JsonHandler();
    urlHandler = new UrlHandler();
    objHandler = new ObjectHandler();
    htmlModifier = new HtmlModifier();

    constructor() {
        this.run();
    }

    async run() {
        //this.redirectByDriveReportId();
        this.setDefaultDate();
    }

    setDefaultDate() {
        const today = new Date().toISOString().slice(0, 10);

        this.objHandler.selectElementByName('startDate').value = today;
        this.objHandler.selectElementByName('endDate').value = today;
    }


    async summaryRetrieval(){
        const requestObj = this.createSummaryReportFormObj();

        const responseData = await this.requestHandler.post('/manage/step1' + '/fetch' + '/summaryRetrieval'
            , this.jsonHandler.convertObjectToJson(requestObj));


        const summaryEle = this.objHandler.selectElementByClass('summary-wrapper');

        summaryEle.innerHTML = `
                <p>총 운반 금액 : <span>${responseData['totalTransportationCost'] * responseData['totalTrips']}원</span></p>
                <p>총 운행 대수 : <span>${responseData['totalTrips']}대</span></p>
        `

    }

    createSummaryReportFormObj() {
        return this.objHandler.createFormObj('summaryForm');
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
        })
    }

}