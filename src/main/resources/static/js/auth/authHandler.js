class AuthHandler {

    inputHandler = new InputHandler();
    requestHandler = new RequestHandler();
    cookieHandler = new CookieHandler();
    objHandler = new ObjectHandler();
    jsonHandler = new JsonHandler();


    constructor() {
    }

    async join() {
        const inputElementNames = ['userId', 'userPwd', 'tel', 'name'];

        for (const name of inputElementNames) {
            if (this.inputHandler.checkValidInput(name)) {
                return;
            }
        }

        if (this.checkEqualPassword()) {
            return;
        }

        if (this.checkPrivacyChk()) {
            alert("개인 정보 수집 및 이용에 동의해주세요.");
            return;
        }

        const requestObj = this.createFormObj('joinForm');

        const responseData = await this.requestHandler.post('/auth' + '/fetch' + '/join'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = '/login';
    }

    checkPrivacyChk() {
        return this.objHandler.selectElementByName('privacyChk').checked === false;
    }

    checkEqualPassword() {
        const userPwd = this.objHandler.selectElementByName('userPwd');
        const userPwdChk = this.objHandler.selectElementByName('userPwdChk');

        if (userPwd.value !== userPwdChk.value) {
            alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
            userPwd.focus();
            return true;
        }

        if (userPwd.value.length < 4) {
            alert("비밀번호는 4글자 이상 입력해주세요.");
            userPwd.focus();
            return true;
        }

        return false;
    }

    async login() {
        const requestObj = this.createFormObj('loginForm');

        const responseData = await this.requestHandler.post('/auth' + '/fetch' + '/login'
            , this.jsonHandler.convertObjectToJson(requestObj));

        this.setLoginCookie(requestObj);
        location.href = responseData;
    }

    setLoginCookie(requestObj) {
        if (this.objHandler.selectElementByName('accountSave').checked === true) {
            this.cookieHandler.setCookie('userId', requestObj.userId, 30);
            this.cookieHandler.setCookie('userPwd', requestObj.userPwd, 30);
        } else {
            this.cookieHandler.deleteCookie('userId');
            this.cookieHandler.deleteCookie('userPwd');
        }
    }

    async accessTrialMode() {
        const requestObj = this.createFormObj('trialForm');

        const responseData = await this.requestHandler.post('/auth' + '/fetch' + '/trialLogin'
            , this.jsonHandler.convertObjectToJson(requestObj));

        location.href = responseData;
    }


    async changePassword() {
        const inputElementNames = ['userPwd', 'userPwdChk'];

        for (const name of inputElementNames) {
            if (this.inputHandler.checkValidInput(name)) {
                return;
            }
        }

        if (this.checkEqualPassword()) {
            return;
        }

        const requestObj = this.createFormObj('passwordChangeForm');


        const responseData = await this.requestHandler.post('/auth' + '/fetch' + '/passwordChange'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = '/login';
    }


    createFormObj(name) {
        return this.objHandler.convertFormIntoObject(
            this.objHandler.selectElementByName(name));
    }


}