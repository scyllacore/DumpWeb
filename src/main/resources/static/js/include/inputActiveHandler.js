class InputActiveHandler {

    objHandler = new ObjectHandler();
    inputHandler = new InputHandler();

    disableChildInputsByCheckBoxesNotChecked(childChkBoxName, parentChkBoxName, childInputEleNames) {
        const childChk = this.inputHandler.getChecked(childChkBoxName);
        const parentChk = this.inputHandler.getChecked(parentChkBoxName);

        if (parentChk || childChk) {
            return;
        }

        this.disableInputs(childInputEleNames);
    }

    activateInputs(inputEleNames) {
        this.setDisabled(inputEleNames, false);
    }

    disableInputs(inputEleNames) {
        this.setDisabled(inputEleNames, true);
    }

    setDisabled(inputEleNames, disabledType) {
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

}