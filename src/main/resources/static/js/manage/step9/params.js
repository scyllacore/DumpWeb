const defaultParams = {
    url: '/manage/step9'
}

const paramsContainer = {
    step9: {
        redirectUrl: '/manage/step9',
        clickElement : ''
        //
        ,
        formName: 'groupDriveReportForm'
        ,
        inputCheckBoxElements: ['groupPaymentChk']
        ,
        dataIdName: 'groupDriveReport'
        //
        ,
        listElementClassName: 'table-tuple'
        ,
        defaultSortingCriteria: 'driveDate'
        //
        ,
        activeInputElementNames: ['groupPaymentChk', 'groupSubmitterRetrievalBtn', 'groupTitle', 'groupMemo', 'prvDriveReport', 'newDriveReport'] //'groupRetrievalBtn'
        ,
        activeInputConfigParams: {
            0: {
                mainTrigger: 'groupPaymentChk',
                type: 'disable',
                range: [1, 6]
            }
        }
    },
    step9Submitter: {
        formName: 'driveReportForm'
        , dataIdName: 'groupSubmitter'
        //
        , listElementClassName: 'table-group-submitter-tuple'
        , defaultSortingCriteria: 'tel'
    }
}

