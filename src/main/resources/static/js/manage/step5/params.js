const inputElementLength = 12;

const paramContainer = {
    step5: {
        url: '/manage/step5'
        ,
        form: 'optionForm'
        ,
        inputElementNames: ['paymentChk','retrievalBtn', 'driveDate', 'item', 'lastKm', 'usedAmount', 'usedOil', 'memo', 'replActiveChk', 'replDate', 'replKm', 'replChk']
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
                range:  [inputElementLength-3, inputElementLength],
                initRange: [inputElementLength - 3, inputElementLength]
            },
            1: {
                mainTrigger: 'paymentChk',
                type: 'disable',
                range: [1,inputElementLength],
                exceptTrigger: 'replActiveChk',
                exceptRange: [inputElementLength - 3, inputElementLength]
            }
        },
        defaultSortingCriteria : 'driveDate'
    }
}

