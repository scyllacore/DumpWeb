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