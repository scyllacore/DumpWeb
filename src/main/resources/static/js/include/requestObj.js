class Request {
    constructor(url, options) {
        this.fetchUrl = url;
        this.fetchOptions = options;
    }

    async tryFetch() {
        const result = await fetch(this.fetchUrl, this.fetchOptions);
        return result.json()
            .catch(error => {
                alert("예기치 못한 오류가 발생했습니다.");
            });
    }

    async tryRequest() {
        const jsonData = await this.tryFetch();

        if (jsonData.status !== 200) {
            throw new Error(jsonData.data);
        }

        return jsonData.data;
    }
}
