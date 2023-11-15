class RequestOption {

    defaultHeaderContentType = 'application/json';

    createGetOption() {
        return {
            method: 'GET',
            headers: {'Content-Type': this.defaultHeaderContentType}
        };
    }

    createPostOption(data = {}) {
        return {
            method: 'POST',
            body: data,
            headers: {'Content-Type': this.defaultHeaderContentType}
        };
    }

    createPutOption(data = {}) {
        return {
            method: 'PUT',
            body: data,
            headers: {'Content-Type': this.defaultHeaderContentType}
        };
    }

    createDeleteOption(data = {}) {
        return {
            method: 'DELETE',
            body: data,
            headers: {'Content-Type': this.defaultHeaderContentType}
        };
    }

    createFormDataOption(formData = {}) {
        return {
            method: 'DELETE',
            body: formData,
            headers: {'Content-Type': 'multipart/form-data'}
        };
    }


}