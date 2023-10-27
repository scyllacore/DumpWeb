class InputHandler {

    urlHandler = new UrlHandler();
    jsonHandler = new JsonHandler();

    checkValidation(formName) {

        const formElement = document.querySelector('form[name="' + formName + '"]');
        const formInput = formElement.querySelectorAll('input');

        for (let i = 0; i < formInput.length; i++) {
            let value = formInput[i].value;

            if (formInput[i].required && value === '') {
                alert(formInput[i].parentElement.querySelector('h3').innerHTML + '를 다시 확인해주세요');
                formInput[i].focus();

                return true;
            }

        }

        return false;
    }

    async inputDataByUrlParams(url, paramName) {
        const ParamVal = this.urlHandler.getUrlParamVal(paramName);

        if (ParamVal === null) {
            return;
        }

        const data = {};

        if(paramName === 'groupDriveReportId'){
            data['groupId'] = ParamVal;
        }else {
            data[paramName] = ParamVal;
        }
        const inputData = this.jsonHandler.getRequestJson(data);

        const responseData = await new RequestHandler()
            .post(url, inputData);

        this.fillInput(responseData);

        return responseData['driveReports'];
    }

    fillInput(data) {

        let selectKeys = [];

        if (typeof data.mileageId !== 'undefined') {
            selectKeys = ["item"];
        } else if (defaultParams.url.includes('step3')) {
            selectKeys = ["progress"];
        }


        for (const key in data) {

            const element = document.querySelector('[name="' + key + '"]')

            if (data[key] === null || element === null) {
                continue;
            }


            if (selectKeys.includes(key)) {
                element.querySelector('[value="' + data[key] + '"]').selected = true;
            } else if (typeof data[key] === "boolean") {
                element.checked = data[key];
            } else {
                element.value = data[key];
            }
        }
    }
}