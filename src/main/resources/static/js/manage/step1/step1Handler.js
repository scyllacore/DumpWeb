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
        this.redirectByDriveReportId();
        this.setDefaultDate();
    }

    setDefaultDate() {
        const today = new Date().toISOString().slice(0, 10);

        this.objHandler.selectAllElementsByClass('start-date').forEach((v) =>{
            v.value = today
        })

        this.objHandler.selectAllElementsByClass('end-date').forEach((v) =>{
            v.value = today
        })


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
        this.urlHandler.redirectByElementData('/manage/step9', 'posting-tuple', 'groupReportId');
    }

    createPostingFormObj() {
        return this.objHandler.createFormObj('postingSearchOptionForm');
    }

    async postingRetrieval() {
        const requestObj = this.createPostingFormObj();

        const responseData = await this.requestHandler.post('/manage/step1' + '/fetch' + '/postingRetrieval'
            , this.jsonHandler.convertObjectToJson(requestObj));


        this.htmlModifier.printList('posting-key', 'posting-tuple', responseData,'groupReportId');

        const tBodyEleChild = this.objHandler.selectElementByClass('posting-tuple').children;

        responseData.forEach((data, idx) => {
            this.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'groupReportId', data['groupReportId']);
        })
    }

}