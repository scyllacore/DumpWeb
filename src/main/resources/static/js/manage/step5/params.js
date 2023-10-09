/* requestByClick */
const defaultUrl = '/manage/step5/fetch/';
const defaultForm = 'optionForm';
const chkParams = ['paymentChk', 'replChk'];


/* Base */
const step5inputNames = ['paymentChk', 'driveDate', 'item', 'lastKm', 'usedAmount', 'usedOil', 'memo', 'replActive', 'replDate', 'replKm', 'replChk']
const activeInputs = {
    0: {
        checkBoxName: 'replActive',
        type : 'active',
        inputNames: step5inputNames,
        range: [step5inputNames.length - 3, step5inputNames.length],
        initRange: [step5inputNames.length - 3, step5inputNames.length]
    },
    1: {
        checkBoxName: 'paymentChk',
        type : 'disable',
        inputNames: step5inputNames,
        range: [1, step5inputNames.length],
        exceptCheckBox: 'replActive',
        exceptRange: [8, 11]
    }
}