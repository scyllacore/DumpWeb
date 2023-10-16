const defaultParams = {
    url: '/manage/step3'
}

const paramsContainer = {
    step3: {
        redirectUrl: '/manage/step3'
        //
        ,
        formName: 'driveReportForm'
        ,
        inputCheckBoxElements: ['driverPaymentChk']
        ,
        dataIdName: 'driveReport'
        //
        ,
        listElementClassName: 'table-tuple'
        ,
        defaultSortingCriteria: 'driveDate'
        //
        ,
        activeInputElementNames: ['driverPaymentChk', 'submitterRetrievalBtn', 'retrievalBtn', 'driveDate', 'fromSite', 'toSite', 'item', 'quantity', 'unitPrice', 'progress', 'memo']
        ,
        activeInputConfigParams: {
            0: {
                mainTrigger: 'driverPaymentChk',
                type: 'disable',
                range: [1, 11]
            }
        }
    },
    step3Submitter: {
        formName: 'driveReportForm'
        , dataIdName: 'submitter'
        //
        , listElementClassName: 'table-submitter-tuple'
        , defaultSortingCriteria: 'tel'
    }
}

