class InputActiveHandler{

    objHandler = new ObjectHandler();
    inputHandler = new InputHandler();

    disableChildInputsByCheckBoxesNotChecked(childChkBoxEleName, childEleName, parentEleName) {
        const childChk = this.inputHandler.getChecked(childChkBoxEleName);
        const parentChk = this.inputHandler.getChecked(parentEleName);

        if (parentChk || childChk) {
            return;
        }

        this.disableInputsByCheckBox(childChkBoxEleName, childEleName);
    }

    activateInputsByCheckBox(chkBoxEleName, inputEleNames) {
        this.setDisabled(chkBoxEleName, inputEleNames, false);
    }

    disableInputsByCheckBox(chkBoxEleName, inputEleNames) {
        this.setDisabled(chkBoxEleName, inputEleNames, true);
    }

    setDisabled(chkBoxEleName, inputEleNames, disabledType) {
        const chkBoxEle = this.objHandler.selectElementByName(chkBoxEleName);

        for (const name of inputEleNames) {
            const inputEle = this.objHandler.selectElementByName(name);
            inputEle.disabled = disabledType;
        }
    }

    initializeInputs(inputEleNames) {
        for (const name of inputEleNames) {
            const initEle = this.objHandler.selectElementByName(name);
            initEle.value = "";
            initEle.checked = false;
        }
    }

/*
    activateFormForSubmit(formEleName) {
        const formEle = this.objHandler.selectElementByName(formEleName);
        const innerEles = formEle.querySelectorAll('input, select, textarea');

        innerEles.forEach(function (ele) {
            ele.disabled = false;
        });
    }
*/

}