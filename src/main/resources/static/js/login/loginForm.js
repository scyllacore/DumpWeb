document.addEventListener("DOMContentLoaded", function () {
    const userInfo = getCookie("kiwonDumpManageUserAccount");

    if (userInfo.length <= 0 || userInfo === 'false') {
        return;
    }

    const userInfoMap = userInfo;

    document.userId.value = userInfoMap[id];
    document.userPwd.value = userInfoMap[pwd];
    document.accountSave.checked = true;
});


function login() {

    if (document.loginForm.userId.value.length <= 0) {
        alert("아이디를 입력해주세요");
        document.loginForm.userId.focus();
        return;
    }

    if (document.loginForm.userPwd.value.length <= 0) {
        alert("비밀번호를 입력해주세요");
        document.loginForm.userPwd.focus();
        return;
    }

    $.ajax({
        url: "/ajax/login",
        type: "POST",
        data: $("form[name=loginForm]").serialize(),
        async: false,
        success: function (response) {
            if (response.status !== 200) {
                alert(response.data);
                return false;
            }

            if (document.accountSave.checked === true) {
                setCookie("kiwonDumpManageUserAccount", $("form[name=loginForm]").serialize(), 30);
            } else {
                deleteCookie("kiwonDumpManageUserAccount");
            }

            location.href = response.data;
        },
        error: function (e) {
            if (e.status == 408) {
                alert("Login keep timed out. \nPlease log in again.");
                location.href = "/";
            }
        }

    });

}

function getCookie(cookieName) {
    const cookies = document.cookie.split(';');

    cookieName += '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if (start != -1) {
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if (end == -1) end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}

function setCookie(cookieName, value, exdays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toLocaleString() + "; path=/login");
    document.cookie = cookieName + "=" + cookieValue;
}

function deleteCookie(cookieName) {
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString() + "; path=/login";
}