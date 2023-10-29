class JsonHandler {
    convertObjectToJson(obj) {
        return JSON.stringify(obj);
    }

    convertJsonToObject(json) {
        return JSON.parse(json);
    }
}