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
        data[paramName] = ParamVal;

        const inputData = this.jsonHandler.getRequestJson(data);

        const responseData = await  new RequestHandler()
            .post(url, inputData);

        this.fillInput(responseData);
    }

    fillInput(data) {

        const itemKeys = ["item"];

        for (const key in data) {

            if (data[key] === null || key.includes('IdIdxFk')) {
                continue;
            }

            if (itemKeys.includes(key)) {
                const optionList = document.querySelector('select[name="' + key + '"]');
                optionList.querySelector('option[value="' + data[key] + '"]').selected = true;
            } else if (typeof data[key] === "boolean") {
                document.querySelector('input[name="' + key + '"]').checked = data[key];
            } else {
                document.querySelector('[name=' + key + ']').value = data[key];
            }
        }
    }
}