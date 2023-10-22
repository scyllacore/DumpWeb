class ActiveInputInfo {
    constructor(inputElementNames = [], activeConfigs = {}) {
        this.inputElementNames = inputElementNames;
        this.activeConfigs = activeConfigs;
    }
}

class ActiveInputHandler {
    constructor(activeInputInfo) {
        this.activeInputInfo = activeInputInfo;
    }

    activateInput() {
        setActiveByCheckBox(this.activeInputInfo);

        function setActiveByCheckBox(activeInputInfo) {

            for (const idx in activeInputInfo.activeConfigs) {
                const activeConfigParams = activeInputInfo.activeConfigs[idx];
                const checkBoxElement = document.querySelector('[name="' + activeConfigParams.mainTrigger + '"]');

                if (checkBoxElement.checked) {
                    setDisabled(activeInputInfo, activeConfigParams, checkBoxElement.checked);
                }

                checkBoxElement.addEventListener('change', (event) => {
                    let active = checkBoxElement.checked;

                    setDisabled(activeInputInfo, activeConfigParams, active);
                    initInput(activeInputInfo, activeConfigParams, active);
                    setExceptInitInput(activeInputInfo, activeConfigParams, active);
                })
            }
        }

        function setDisabled(activeInputInfo, activeConfigParams, active) {

            if (checkUndefined(activeConfigParams.range)) {
                return;
            }


            let start = activeConfigParams.range[0];
            let end = activeConfigParams.range[1];

            for (let i = start; i < end; i++) {
                const inputElement = document.querySelector('[name="'
                    + activeInputInfo.inputElementNames[i] + '"]');

                if (activeConfigParams.type === 'active') {
                    inputElement.disabled = !active;
                } else {
                    inputElement.disabled = active;
                }
            }
        }

        function initInput(activeInputInfo, activeConfigParams) {

            if (checkUndefined(activeConfigParams.initRange)) {
                return;
            }

            let initStart = activeConfigParams.initRange[0];
            let initEnd = activeConfigParams.initRange[1];

            for (let i = initStart; i < initEnd; i++) {
                const inputElement = document.querySelector('[name="'
                    + activeInputInfo.inputElementNames[i] + '"]');

                inputElement.value = "";
                inputElement.checked = false;
            }
        }

        function setExceptInitInput(activeInputInfo, activeConfigParams, active) {

            if (checkUndefined(activeConfigParams.exceptRange)) {
                return;
            }

            const exceptActive = document.querySelector('[name="'
                + activeConfigParams.exceptTrigger + '"]')
                .checked;

            if (active || exceptActive) {
                return;
            }

            let exceptStart = activeConfigParams.exceptRange[0];
            let exceptEnd = activeConfigParams.exceptRange[1];

            for (let i = exceptStart; i < exceptEnd; i++) {
                const inputElement = document.querySelector('[name="'
                    + activeInputInfo.inputElementNames[i] + '"]');

                inputElement.disabled = true;
            }
        }
    }

    setDisabledFalse(form) {
        const formElement = document.querySelector('form[name="' + form + '"]');
        const formInput = formElement.querySelectorAll('input');
        const formSelector = formElement.querySelectorAll('select');
        const formTextarea = formElement.querySelectorAll('textarea');

        for (let i = 0; i < formInput.length; i++) {
            formInput[i].disabled = false;
        }

        for (let i = 0; i < formSelector.length; i++) {
            formSelector[i].disabled = false;
        }

        for (let i = 0; i < formTextarea.length; i++) {
            formTextarea[i].disabled = false;
        }
    }
}