document.addEventListener("DOMContentLoaded", async () => {
    await inputDataByParams(defaultUrl + '/fetch/mileageDetails', idName);
    setActiveByCheckBox(activeInputs);
    redirectById(defaultUrl, idName);
})