const defaultUrl = '/manage/step5/fetch/';
const defaultForm = 'optionForm';
const step5inputNames = ['paymentChk','driveDate', 'item', 'lastKm', 'usedAmount', 'usedOil', 'memo', 'replActive', 'replDate', 'replKm', 'replChk']

const activeInputs = {
    replActive: {
        checkBoxName: 'replActive',
        inputNames: step5inputNames,
        range:[step5inputNames.length-3,step5inputNames.length],
        initRange:[step5inputNames.length-3,step5inputNames.length]
    }
}

const LockedInput ={
    paymentActive: {
        checkBoxName: 'paymentChk',
        inputNames: step5inputNames,
        range:[1,step5inputNames.length],
        exceptCheckBox : 'replActive',
        exceptRange : [8,11]
    },
}

const chkParams = ['paymentChk', 'replChk'];

//함수 선언
function save() {

    if (checkInputValidation(defaultForm)) {
        return;
    }

    let inputData = getJsonForm(defaultForm, chkParams);
    const responseData = handleRequest(defaultUrl + 'mileageSave'
        , 'POST'
        , inputData);

    console.log(responseData);
}

function retrieval() {

}

document.addEventListener("DOMContentLoaded",()=>{
    setActiveByCheckBox(activeInputs);
    setDisableByCheckBox(LockedInput);
})