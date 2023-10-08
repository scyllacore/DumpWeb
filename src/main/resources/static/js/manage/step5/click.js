const defaultUrl = '/manage/step5/fetch/';
const defaultForm = 'optionForm';

function clickRetrieval() {
    console.log('ste5 test');

    const inputData = getJsonForm(defaultForm);

    const responseData= handleRequest(defaultUrl+'mileageSave'
        ,'POST'
        ,inputData);

    console.log(responseData);
}