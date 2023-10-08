class Request {

    constructor(argUrl, argOptions) {
        this.fetchUrl = argUrl;
        this.fetchOptions = argOptions;
    }

    async tryRequest() {
        const result = await fetch(this.fetchUrl, this.fetchOptions);
        return await result.json();
    }

    requestData() {
        const json = this.tryRequest();

        return json.json()
            .then(jsonData => {
                if (jsonData.status !== 200) {
                    throw new Error(jsonData.data);
                }
            })
            .then(jsonData => {
                return jsonData.data;
            })
            .catch(error => {
                alert("예기치 못한 오류가 발생했습니다.");
            });
    }
}
