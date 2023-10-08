/* requestByClick */
const defaultUrl = '/manage/step5/fetch/';
const defaultForm = 'optionForm';
const chkParams = ['paymentChk', 'replChk'];


/* Base */
const step5inputNames = ['paymentChk', 'driveDate', 'item', 'lastKm', 'usedAmount', 'usedOil', 'memo', 'replActive', 'replDate', 'replKm', 'replChk']
const activeInputs = {
    replActive: {
        checkBoxName: 'replActive',
        inputNames: step5inputNames,
        range: [step5inputNames.length - 3, step5inputNames.length],
        initRange: [step5inputNames.length - 3, step5inputNames.length]
    }
}
const LockedInput = {
    paymentActive: {
        checkBoxName: 'paymentChk',
        inputNames: step5inputNames,
        range: [1, step5inputNames.length],
        exceptCheckBox: 'replActive',
        exceptRange: [8, 11]
    },
}