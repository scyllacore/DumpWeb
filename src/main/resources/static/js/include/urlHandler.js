class UrlHandler {
    redirectByElementValue(defaultUrl, paramName) {
        const listRow = document.querySelector("table tbody");

        listRow.addEventListener("click", (event) => {
            let val = event.target.parentElement.getAttribute('data-' + paramName);
            let url = defaultUrl + '?' + paramName + '=' + val;
            window.location.href = url;
        });
    }

    getUrlParamVal(paramName) {
        const queryString = window.location.search;
        const params = new URLSearchParams(queryString);
        return params.get(paramName);
    }
}


