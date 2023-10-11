class JsonHandler {
    getRequestJson(element, addingParams) {

        if (typeof element === 'object') {
            return JSON.stringify(element);
        }

        const el = document.querySelector('[name="' + element + '"]');
        let obj = {};

        if (el.tagName === 'FORM') {
            const entry = new FormData(el).entries();
            obj = Object.fromEntries(entry);
            this.addParamsToObject(obj, addingParams);
        } else if (el.tagName === 'INPUT') {
            obj[el.getAttribute('name')] = el.value;
        }

        return JSON.stringify(obj);
    }

    addParamsToObject(obj, params = []) {
        params.forEach(param => {
            const element = document.querySelector('input[name="' + param + '"]');
            obj[param] = element.checked;
        })
    }
}