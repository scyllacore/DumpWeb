class JsonHandler {
    getRequestJson(element, addingElementNames =[], addingProperties = {}) {

        if (typeof element === 'object') {
            return JSON.stringify(element);
        }

        const el = document.querySelector('[name="' + element + '"]');
        let obj = {};

        if (el.tagName === 'FORM') {
            const entry = new FormData(el).entries();
            obj = Object.fromEntries(entry);
            this.addPropertyToObject(obj, addingElementNames, addingProperties);
        } else if (el.tagName === 'INPUT') {
            obj[el.getAttribute('name')] = el.value;
        }

        return JSON.stringify(obj);
    }

    addPropertyToObject(obj, addingElementNames, addingProperties) {
        addingElementNames.forEach(elementName => {
            const element = document.querySelector('[name="' + elementName + '"]');
            obj[elementName] = element.checked;
        })

        for (const key in addingProperties) {
            obj[key] = addingProperties[key];
        }
    }
}