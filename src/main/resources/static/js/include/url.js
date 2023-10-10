function redirectById(originUrl, idName) {
    const listRow = document.querySelector("table tbody");

    listRow.addEventListener("click", (event) => {
        let Id = event.target.parentElement.getAttribute('data-' + idName);
        let url = originUrl + '?' + idName + '=' + Id;
        window.location.href = url;
    });
}


function getUrlIdParam(idName) {
    const queryString = window.location.search;
    const params = new URLSearchParams(queryString);
    return params.get(idName);
}


