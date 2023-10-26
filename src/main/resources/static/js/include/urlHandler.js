class UrlHandler {
    redirectByElementValue(url, paramName,element = 'tbody') {

        if (typeof url === 'undefined' || element === '') {
            return;
        }

        const listRow = document.querySelector(element);

        console.log(listRow);

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


