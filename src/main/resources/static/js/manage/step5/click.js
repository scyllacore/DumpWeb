const defaultUrl = '/manage/step5/fetch/';
const defaultForm = 'optionForm';
const step5inputNames = ['driveDate', 'item', 'lastKm', 'usedAmount', 'usedOil', 'memo', 'replActive', 'replDate', 'replKm', 'replChk']

const activeInputs = {
    paymentActive: {
        checkBoxName: 'paymentChk',
        inputNames: step5inputNames,
        exceptRange : [7,10],
        initNames: []
    },
    replActive: {
        checkBoxName: 'replActive',
        inputNames: step5inputNames.slice(7,10),
        initNames: step5inputNames.slice(7,10),
        type : -1
    }
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
    setInputActiveByCheckBox(activeInputs);
})