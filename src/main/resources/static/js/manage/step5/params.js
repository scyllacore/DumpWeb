/* requestByClick */
const defaultUrl = '/manage/step5';
const defaultForm = 'optionForm';
const chkParams = ['replChk','paymentChk'];
const idName = 'mileageId';


/* Base */
const step5inputNames = ['paymentChk', 'driveDate', 'item', 'lastKm', 'usedAmount', 'usedOil', 'memo', 'replActiveChk', 'replDate', 'replKm', 'replChk']
const activeInputs = {
    0: {
        checkBoxName: 'replActiveChk',
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
        exceptCheckBox: 'replActiveChk',
        exceptRange: [step5inputNames.length - 3, step5inputNames.length]
    }
}