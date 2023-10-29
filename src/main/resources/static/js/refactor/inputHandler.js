class InputHandler {

    objHandler = new ObjectHandler();

    getChecked(chkBoxEleName) {
        const chkBoxEle = this.objHandler.selectElementByName(chkBoxEleName);

        if (chkBoxEle.type !== 'checkbox') {
            throw new Error('체크박스 입력이 아닙니다.');
        }

        return chkBoxEle.checked;
    }

    getInputValue(inputEleName) {
        const inputEle = this.objHandler.selectElementByName(inputEleName);

        if (inputEle.tagName !== 'input') {
            throw new Error('입력 요소가 아닙니다.');
        }

        return inputEle.value;
    }

    checkValidInput(inputName) {
        const element = this.objHandler.selectElementByName(inputName);

        if (element.required && element.value === '') {
            element.focus();
            return true;
        }

        return false;
    }

}