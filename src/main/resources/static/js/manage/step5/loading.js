document.addEventListener("DOMContentLoaded", async () => {
    await inputDataByParams(defaultUrl + 'mileageDetails', idName);
    setActiveByCheckBox(activeInputs);
    redirectById(defaultUrl + 'mileageDetails', idName);
})