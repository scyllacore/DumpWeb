const defaultParams = {
    url: '/manage/step7'
}

const paramsContainer = {
    step7: {
        redirectUrl: '/manage/step7'
        //
        ,
        formName: 'driveReportForm'
        ,
        inputCheckBoxElements: ['paymentChk','postingChk']
        ,
        dataIdName: 'driveReport'
        //
        ,
        listElementClassName: 'table-tuple'
        ,
        defaultSortingCriteria: 'driveDate'
        //
        ,
        activeInputElementNames: ['paymentChk', 'driverRetrievalBtn', 'retrievalBtn', 'driveDate', 'fromSite', 'toSite', 'item', 'quantity', 'unitPrice', 'memo', 'postingChk']
        ,
        activeInputConfigParams: {
            0: {
                mainTrigger: 'paymentChk',
                type: 'disable',
                range: [1, 11]
            }
        }
    },
    step7Driver: {
        formName: 'driveReportForm'
        , dataIdName: 'driver'
        //
        , listElementClassName: 'table-driver-tuple'
        , defaultSortingCriteria: 'carNo'
    }
}

