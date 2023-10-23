const defaultParams = {
    url: '/manage/step9'
}

const paramsContainer = {
    step9: {
        clickElement : '',
        //
        formName: 'groupDriveReportForm'
        ,
        inputCheckBoxElements: ['groupPaymentChk']
        ,
        dataIdName: 'groupDriveReport'
        //
        //
        ,
        activeInputElementNames: ['groupPaymentChk', 'groupSubmitterRetrievalBtn', 'groupTitle', 'groupMemo', 'newDriveReport'] //'groupRetrievalBtn'
        ,
        activeInputConfigParams: {
            0: {
                mainTrigger: 'groupPaymentChk',
                type: 'disable',
                range: [1, 5]
            }
        }
    },
    step9Submitter: {
        formName: 'driveReportForm'
        , dataIdName: 'submitter'
        //
        , listElementClassName: 'table-submitter-tuple'
        , defaultSortingCriteria: 'tel'
    },
    step9Drive: {
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
    }
}

