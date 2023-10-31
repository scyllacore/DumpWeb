let authHandler;

document.addEventListener("DOMContentLoaded", () => {
    authHandler = new AuthHandler();
    getLoginCookie();
})

function getLoginCookie() {
    const userInfo = authHandler.cookieHandler.getCookie();

    if (typeof userInfo['userId'] === 'undefined') {
        return;
    }

    authHandler.objHandler.selectElementByName('userId').value = userInfo['userId'];
    authHandler.objHandler.selectElementByName('userPwd').value = userInfo['userPwd'];
    authHandler.objHandler.selectElementByName('accountSave').checked = true;
}

const func = {
    async join() {
        await authHandler.join('join');
    },
    async login() {
        await authHandler.login('login')
    },
    async accessTrialMode(type) {
        authHandler.objHandler.selectElementByName('userType').value = type;
        await authHandler.accessTrialMode();
    },
    async changePassword() {
        await authHandler.changePassword('passwordChange')
    }
}