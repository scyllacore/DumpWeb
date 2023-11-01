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

    createFormObj(name) {
        return this.convertFormIntoObject(this.selectElementByName(name));
    }

    convertFormIntoObject(formEle) {
        return Object.fromEntries(new FormData(formEle).entries());
    }

    addPropertyToObject(obj, properties) {
        for (const key in properties) {
            obj[key] = properties[key];
        }
    }

}