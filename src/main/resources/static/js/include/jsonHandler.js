class JsonHandler {
    getRequestJson(element, elementName =[], properties = {}) {

        if (typeof element === 'object') {
            return JSON.stringify(element);
        }

        const el = document.querySelector('[name="' + element + '"]');
        let obj = {};

        if (el.tagName === 'FORM') {
            const entry = new FormData(el).entries();
            obj = Object.fromEntries(entry);
            this.addPropertyToObject(obj, elementName, properties);
        } else if (el.tagName === 'INPUT') {
            obj[el.getAttribute('name')] = el.value;
        }

        return JSON.stringify(obj);
    }

    addPropertyToObject(obj, elementName, properties) {
        elementName.forEach(param => {
            const element = document.querySelector('[name="' + elementName + '"]');
            obj[param] = element.checked;
        })

        for (const key in properties) {
            obj[key] = properties[key];
        }
    }
}