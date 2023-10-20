class RequestHandler {
    getOptions(method, data = {}, headerContentType = 'application/json') {
        if (method === 'GET') {
            return {
                method: method,
                headers: {'Content-Type': headerContentType}
            };
        }

        return {
            method: method,
            body: data,
            headers: {'Content-Type': headerContentType}
        };
    }

    startRequest(request) {
        return request.tryRequest();
    }

    get(url, data, headerContentType) {
        const options = this.getOptions('GET');
        return this.startRequest(new Request(url, options));
    }

    post(url, data, headerContentType) {
        const options = this.getOptions('POST', data);
        return this.startRequest(new Request(url, options));
    }


    put(url, data, headerContentType) {
        const options = this.getOptions('PUT', data);
        return this.startRequest(new Request(url, options));
    }

    delete(url, data, headerContentType) {
        const options = this.getOptions('DELETE', data);
        return this.startRequest(new Request(url, options));
    }
}
