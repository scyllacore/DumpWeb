const paramContainer = {
    step5: {
        url: '/manage/step5'
        ,
        form: 'optionForm'
        ,
        inputElementNames: ['paymentChk', 'driveDate', 'item', 'lastKm', 'usedAmount', 'usedOil', 'memo', 'replActiveChk', 'replDate', 'replKm', 'replChk']
        ,
        checkBoxElement: ['replActiveChk', 'replChk', 'paymentChk']
        ,
        dataIdNames: 'mileage'
        ,
        listElementClassNames: 'table-tuple'
        ,
        activeInputParams : {
            0: {
                mainTrigger: 'replActiveChk',
                type: 'active',
                range:  [11-3, 11],
                initRange: [11 - 3, 11]
            },
            1: {
                mainTrigger: 'paymentChk',
                type: 'disable',
                range: [1,11],
                exceptTrigger: 'replActiveChk',
                exceptRange: [11 - 3, 11]
            }
        },
        defaultSortingCriteria : 'driveDate'
    }
}

