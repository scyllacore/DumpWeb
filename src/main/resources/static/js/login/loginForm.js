document.addEventListener("DOMContentLoaded", function () {
    const userInfo = getCookie();

    if (userInfo['userId'] == undefined) {
        return;
    }

    document.querySelector('input[name = "userId"]').value = userInfo['userId'];
    document.querySelector('input[name = "userPwd"]').value = userInfo['userPwd'];
    document.querySelector('input[name = "accountSave"]').checked = true;
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

            const data = $("form[name=loginForm]").serialize().split('&');

            if (document.querySelector('input[name = "accountSave"]').checked === true) {
                setCookie('userId', data[0], 30);
                setCookie('userPwd', data[1], 30);
            } else {
                deleteCookie('userId');
                deleteCookie('userPwd');
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

function getCookie() {
    let cookieMap = {};

    let cookieData = document.cookie.split('; ');

    cookieData.forEach(data => {
        const parsingData = data.split('=');
        cookieMap[parsingData[0]] = decodeURI(decodeURI(parsingData[1]));
    })

    return cookieMap;
}

function setCookie(cookieName, data, addDay) {
    const expDate = new Date();
    expDate.setDate(expDate.getDate() + addDay);

    let parsingData = data.split('=');

    const cookieData = encodeURI(parsingData[1]) + ((addDay == null) ? "" : "; expires=" + expDate.toLocaleString() + "; path=/login");
    document.cookie = cookieName + "=" + cookieData;

}

function deleteCookie(cookieName) {
    const expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "=" + "; expires=" + expireDate.toGMTString() + "; path=/login";
}