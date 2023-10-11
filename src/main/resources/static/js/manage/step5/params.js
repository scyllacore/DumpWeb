const defaultUrl = '/manage/step5';
const defaultForm = 'optionForm';
const inputElementNames = ['paymentChk', 'driveDate', 'item', 'lastKm', 'usedAmount', 'usedOil', 'memo', 'replActiveChk', 'replDate', 'replKm', 'replChk'];
const checkBoxElement = ['replActiveChk', 'replChk', 'paymentChk'];
const dataIdNames = ['mileage'];
const activeInputParams = {
    0: {
        mainTrigger: 'replActiveChk',
        type: 'active',
        range: [inputElementNames.length - 3, inputElementNames.length],
        initRange: [inputElementNames.length - 3, inputElementNames.length]
    },
    1: {
        mainTrigger: 'paymentChk',
        type: 'disable',
        range: [1, inputElementNames.length],
        exceptTrigger: 'replActiveChk',
        exceptRange: [inputElementNames.length - 3, inputElementNames.length]
    }
}

const listElementClassNames = ['table-tuple'];
const defaultSortingCriteria = ['driveDate'];

//idx 순서대로 적는것 생각할 것.. dataIdNames : 'mileage'와 listElementClassNames : 'table-tuple'이 한 세트!