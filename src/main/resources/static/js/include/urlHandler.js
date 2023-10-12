class UrlHandler {
    redirectByElementValue(url, paramName) {
        const listRow = document.querySelector("table tbody");

        listRow.addEventListener("click", (event) => {
            let val = event.target.parentElement.getAttribute('data-' + paramName);
            let href = url + '?' + paramName + '=' + val;
            window.location.href = href;
        });
    }

    getUrlParamVal(paramName) {
        const queryString = window.location.search;
        const params = new URLSearchParams(queryString);
        return params.get(paramName);
    }
}


