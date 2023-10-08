function getUrlCheckParam(id) {
    const checkBox = document.getElementById(id);
    return "&" + id + "=" + checkBox.checked;
}

function redirectByDriveId() {
    const listRow = document.querySelector("table tbody");

    listRow.addEventListener("click", (event) => {
        let driveID = event.target.parentElement.getAttribute("data-drive-id")
        let url = "/dailyReport/carcareform" + "?driveID=" + driveID;
        window.location.href = url;
    });
}

function setInputActiveByCheckBox(activeInput) {

    const checkBoxElement = document.querySelector('input[name="' + activeInput.checkBoxName + '"]');

    checkBoxElement.addEventListener('change', (event) => {
        activeInput.inputName.forEach(tagName => {
            const inputElement = document.querySelector('input[name="' + tagName + '"]');

            inputElement.value = "";
            inputElement.checked = false;
            inputElement.disabled = !inputElement.disabled;
        })
    })

}


document.addEventListener("DOMContentLoaded", () => {
    const queryString = window.location.search;
    const params = new URLSearchParams(queryString);

    let driveId = params.get("driveId");

    if (driveId !== null) {
        requestDetailsByDriveID(driveId);
    }

    redirectByDriveId();
});

