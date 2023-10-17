let authHandler;

document.addEventListener("DOMContentLoaded", () => {
    authHandler = new AuthHandler(paramsContainer);

    const userInfo = authHandler.cookieHandler.getCookie();

    if (typeof userInfo['userId'] === 'undefined') {
        return;
    }

    document.querySelector('input[name = "userId"]').value = userInfo['userId'];
    document.querySelector('input[name = "userPwd"]').value = userInfo['userPwd'];
    document.querySelector('input[name = "accountSave"]').checked = true;
})

const func = {
    async join() {
        await authHandler.join('join');
    },
    async login() {
        await authHandler.login('login')
    },
    async accessTrialMode(_this) {
        await authHandler.accessTrialMode('trial', _this)
    },
    async changePassword() {
        await authHandler.changePassword('passwordChange')
    }
}