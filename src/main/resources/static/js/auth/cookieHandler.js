class CookieHandler {
    getCookie() {
        let cookieMap = {};

        let cookieData = document.cookie.split('; ');

        cookieData.forEach(data => {
            const parsingData = data.split('=');
            cookieMap[parsingData[0]] = decodeURI(decodeURI(parsingData[1]));
        })

        return cookieMap;
    }

    setCookie(cookieName, data, addDay) {
        const expDate = new Date();
        expDate.setDate(expDate.getDate() + addDay);

        const cookieData = encodeURI(data) + ((addDay == null) ? "" : "; expires=" + expDate.toLocaleString() + "; path=/login");
        document.cookie = cookieName + "=" + cookieData;
    }

    deleteCookie(cookieName) {
        const expireDate = new Date();
        expireDate.setDate(expireDate.getDate() - 1);
        document.cookie = cookieName + "=" + "; expires=" + expireDate.toGMTString() + "; path=/login";
    }

}