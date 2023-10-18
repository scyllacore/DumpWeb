const defaultParams = {
    url: '/manage/step5'
}

const paramsContainer = {
    step5: {
        redirectUrl: defaultParams.url
        ,
        //
        formName: 'mileageForm'
        ,
        inputCheckBoxElements: ['replActiveChk', 'replChk', 'paymentChk']
        ,
        dataIdName: 'mileage'
        //
        ,
        listElementClassName: 'table-tuple'
        ,
        defaultSortingCriteria: 'driveDate'
        //
        ,
        activeInputElementNames: ['paymentChk', 'retrievalBtn', 'driveDate', 'item', 'lastKm', 'usedAmount', 'usedOil', 'memo', 'replActiveChk', 'replDate', 'replKm', 'replChk']
        ,
        activeInputConfigParams: {
            0: {
                mainTrigger: 'replActiveChk',
                type: 'active',
                range: [9, 12],
                initRange: [9, 12]
            },
            1: {
                mainTrigger: 'paymentChk',
                type: 'disable',
                range: [1, 12],
                exceptTrigger: 'replActiveChk',
                exceptRange: [9, 12]
            }
        }
    }
}

