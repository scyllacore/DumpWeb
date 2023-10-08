const defaultUrl = '/manage/step5/fetch/';
const defaultForm = 'optionForm';
const activeInput = {
    checkBoxName: 'paymentChk',
    inputName: ['replDate', 'replKm', 'replChk']
}
const chkParams =['replChk'];

//함수 호출
setInputActiveByCheckBox(activeInput);


//함수 선언
function save() {
    let inputData = getJsonForm(defaultForm,chkParams);
    const responseData = handleRequest(defaultUrl + 'mileageSave'
        , 'POST'
        , inputData);

    console.log(responseData);
}

function retrieval(){

}