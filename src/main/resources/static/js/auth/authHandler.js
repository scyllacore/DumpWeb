class AuthHandler {

    inputHandler = new InputHandler();
    requestHandler = new RequestHandler();
    cookieHandler = new CookieHandler();
    objHandler = new ObjectHandler();
    jsonHandler = new JsonHandler();


    constructor() {
    }

    async join() {
        if(this.checkValidation()){
            return;
        }

        const requestObj = this.objHandler.createFormObj('joinForm');

        const responseData = await this.requestHandler.post('/auth' + '/fetch' + '/join'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = '/login';
    }

    checkValidation(){
        const inputElementNames = ['userId', 'userPwd', 'tel', 'name'];

        for (const name of inputElementNames) {
            if (this.inputHandler.checkValidInput(name)) {
                return true;
            }
        }

        if (this.checkEqualPassword()) {
            return true;
        }
        if (this.checkPrivacyChk()) {
            return true;
        }
        return false;
    }

    checkPrivacyChk() {
        if(this.objHandler.selectElementByName('privacyChk').checked  === false){
            alert("개인 정보 수집 및 이용에 동의해주세요.");
            return true;
        }

        return false;
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
        const requestObj = this.objHandler.createFormObj('loginForm');

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
        const requestObj = this.objHandler.createFormObj('trialForm');

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

        const requestObj =this.objHandler.createFormObj('passwordChangeForm');


        const responseData = await this.requestHandler.post('/auth' + '/fetch' + '/passwordChange'
            , this.jsonHandler.convertObjectToJson(requestObj));

        alert(responseData);
        location.href = '/login';
    }


}