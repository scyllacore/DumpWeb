class UrlHandler {

    objHandler = new ObjectHandler();

    redirectByElementData(tableClassName, paramName, url) {
        const tableEle = this.objHandler.selectElementByClass(tableClassName);

        tableEle.addEventListener("click", (event) => {
            const paramVal = event.target.parentElement.getAttribute('data-' + paramName);
            const href = url + '?' + paramName + '=' + paramVal;
            window.location.href = href;
        });
    }

    getUrlParameterValue(paramName) {
        const queryString = window.location.search;
        const params = new URLSearchParams(queryString);
        return params.get(paramName);
    }
}


