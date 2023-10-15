const step3InputElementLength = 9;
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
        listElementClassNames: 'table-tuple'
        ,
        inputElementNames: ['driveReportId', 'driverPaymentChk', 'submitterRetrievalBtn', 'driveDate', 'fromSite', 'item', 'quantity', 'unitPrice', 'memo']
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
        , listElementClassNames: 'table-submitter-tuple'
        , checkBoxElements: ['driverPaymentChk']
        , defaultSortingCriteria: 'tel'
    }
}

