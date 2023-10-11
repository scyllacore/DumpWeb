class RequestHandler {
    constructor(url='', method='', data={}, headerContentType = 'application/json') {
        const options = {
            method: method,
            body: data,
            headers: {'Content-Type': headerContentType}
        };

        this.request = new Request(url, options);
    }

    fetchRequest() {
        return this.request.requestData();
    }
}
