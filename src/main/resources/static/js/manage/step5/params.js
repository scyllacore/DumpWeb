const step5InputElementLength = 12;
const defaultUrl ='/manage/step5';

const paramContainer = {
    step5: {
        url: defaultUrl
        , redirectUrl: defaultUrl
        , form: 'optionForm'
        , dataIdNames: 'mileage'
        , listElementClassNames: 'table-tuple'
        , inputElementNames: ['paymentChk', 'retrievalBtn', 'driveDate', 'item', 'lastKm', 'usedAmount', 'usedOil', 'memo', 'replActiveChk', 'replDate', 'replKm', 'replChk']
        , checkBoxElement: ['replActiveChk', 'replChk', 'paymentChk']
        , activeInputConfigParams: {
            0: {
                mainTrigger: 'replActiveChk',
                type: 'active',
                range: [step5InputElementLength - 3, step5InputElementLength],
                initRange: [step5InputElementLength - 3, step5InputElementLength]
            },
            1: {
                mainTrigger: 'paymentChk',
                type: 'disable',
                range: [1, step5InputElementLength],
                exceptTrigger: 'replActiveChk',
                exceptRange: [step5InputElementLength - 3, step5InputElementLength]
            }
        },
        defaultSortingCriteria: 'driveDate'
    }
}

