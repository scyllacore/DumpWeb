class RequestHandler {

    requestOption = new RequestOption();

    get(url) {
        return new Request(url, this.requestOption.createGetOption()).tryRequest();
    }

    post(url, data) {
        return new Request(url, this.requestOption.createPostOption(data)).tryRequest();
    }

    put(url, data) {
        return new Request(url, this.requestOption.createPutOption(data)).tryRequest();
    }

    delete(url, data) {
        return new Request(url, this.requestOption.createDeleteOption(data)).tryRequest();
    }

    postFormData(url, data) {
        return new Request(url, this.requestOption.createFormDataPostOption(data)).tryRequest();
    }
}
