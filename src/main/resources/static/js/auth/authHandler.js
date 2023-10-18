class AuthHandler {

    inputHandler = new InputHandler();
    requestHandler = new RequestHandler();
    cookieHandler = new CookieHandler();

    constructor(paramContainer) {
        this.paramContainer = paramContainer;
    }

    async join(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        if (this.inputHandler.checkValidation(paramObj.formName)) {
            return;
        }

        let inputData = this.inputHandler.jsonHandler
            .getRequestJson(paramObj.formName, paramObj.inputCheckBoxElements);

        if (this.checkEqualPassword(inputData)) {
            return;
        }

        if(document.querySelector('input[name = "privacyChk"]').checked === false){
            alert("개인 정보 수집 및 이용에 동의해주세요.");
            return;
        }

        const responseData = await this.requestHandler
            .post(paramObj.url + '/fetch/' + 'join', inputData);

        alert(responseData);
        location.href = '/login';
    }

    async login(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        const inputData = this.inputHandler.jsonHandler.getRequestJson(paramObj.formName);

        const responseData = await this.requestHandler
            .post(paramObj.url + '/fetch/' + 'login', inputData);

        let inputObj = JSON.parse(inputData);

        if (document.querySelector('input[name = "accountSave"]').checked === true) {
            this.cookieHandler.setCookie('userId', inputObj.userId, 30);
            this.cookieHandler.setCookie('userPwd', inputObj.userPwd, 30);
        } else {
            this.cookieHandler.deleteCookie('userId');
            this.cookieHandler.deleteCookie('userPwd');
        }

        location.href = responseData;
    }

    async accessTrialMode(containerKey,_this) {

        document.querySelector('input[name = "userType"]').value = _this.getAttribute("name");

        const paramObj = this.paramContainer[containerKey];

        let inputData = this.inputHandler.jsonHandler.getRequestJson(paramObj.formName);

        const responseData = await this.requestHandler
            .post(paramObj.url + '/fetch/' + 'trialLogin', inputData);

        location.href = responseData;
    }

    async changePassword(containerKey) {
        const paramObj = this.paramContainer[containerKey];

        if (this.inputHandler.checkValidation(paramObj.formName)) {
            return;
        }

        let inputData = this.inputHandler.jsonHandler.getRequestJson(paramObj.formName);

        if (this.checkEqualPassword(inputData)) {
            return;
        }

        const responseData = await this.requestHandler
            .post(paramObj.url + '/fetch/' + 'passwordChange', inputData);


        alert(responseData);
        location.href = '/login';
    }

    checkEqualPassword(inputData) {
        let inputObj = JSON.parse(inputData);

        if (inputObj.userPwd !== inputObj.userPwdChk) {
            alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
            document.querySelector('[name = "userPwd"]').focus();
            return true;
        }

        if (inputObj.userPwd.length < 4) {
            alert("비밀번호는 4글자 이상 입력해주세요.");
            document.querySelector('input[name = "userPwd"]').focus();
            return true;
        }

        return false;
    }

}