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
        inputCheckBoxElements: ['paymentChk']
        ,
        dataIdName: 'driveReport'
        //
        ,
        listElementClassName: 'table-tuple'
        ,
        defaultSortingCriteria: 'driveDate'
        //
        ,
        activeInputElementNames: ['paymentChk', 'submitterRetrievalBtn', 'retrievalBtn', 'driveDate', 'fromSite', 'toSite', 'item', 'quantity', 'unitPrice', 'progress', 'memo']
        ,
        activeInputConfigParams: {
            0: {
                mainTrigger: 'paymentChk',
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

