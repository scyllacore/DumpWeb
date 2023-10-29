class ObjectHandler {

    selectElementByName(name) {
        return document.querySelector('[name="' + name + '"]');
    }

    selectElementByClass(name) {
        return document.querySelector('.' + name);
    }

    selectAllElementsByClass(name) {
        return document.querySelectorAll('.' + name);
    }

    convertFormIntoObject(formElement) {
        return Object.fromEntries(new FormData(formElement).entries());
    }

    addPropertyToObject(obj, properties) {
        for (const key in properties) {
            obj[key] = properties[key];
        }
    }

}