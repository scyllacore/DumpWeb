class UrlHandler {

    objHandler = new ObjectHandler();

    redirectByElementData(url, tBodyClass, name) {
        const tableEle = this.objHandler.selectElementByClass(tBodyClass);

        tableEle.addEventListener("click", (event) => {
            const val = event.target.parentElement.getAttribute('data-' + name);
            window.location.href = url + '?' + name + '=' + val;
        });
    }

    getUrlParameterValue(paramName) {
        const queryString = window.location.search;
        const params = new URLSearchParams(queryString);
        return params.get(paramName);
    }
}


