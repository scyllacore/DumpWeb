const step3InputElementLength = 11;
const defaultUrl = '/manage/step3';

const paramContainer = {
    step3: {
        url: defaultUrl
        ,
        redirectUrl: defaultUrl
        ,
        form: 'driveReportForm'
        ,
        dataIdNames: 'driveReport'
        ,
        listElementClassName: 'table-tuple'
        ,
        inputElementNames: ['driveReportId', 'driverPaymentChk', 'submitterRetrievalBtn','retrievalBtn', 'driveDate', 'fromSite', 'item', 'quantity', 'unitPrice','progress', 'memo']
        ,
        checkBoxElements: ['driverPaymentChk']
        ,
        activeInputConfigParams: {
            0: {
                mainTrigger: 'driverPaymentChk',
                type: 'disable',
                range: [2, step3InputElementLength]
            }
        },
        defaultSortingCriteria: 'driveDate'
    },
    step3Submitter: {
        url: defaultUrl
        , form: 'driveReportForm'
        , dataIdNames: 'submitter'
        , listElementClassName: 'table-submitter-tuple'
        , defaultSortingCriteria: 'tel'
    }
}

