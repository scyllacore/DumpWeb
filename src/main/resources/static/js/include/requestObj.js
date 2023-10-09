class Request {

    constructor(argUrl, argOptions) {
        this.fetchUrl = argUrl;
        this.fetchOptions = argOptions;
    }

    async tryRequest() {
        console.log(this.fetchUrl,this.fetchOptions);

        const result = await fetch(this.fetchUrl, this.fetchOptions);
        return result.json()
            .catch(error => {
                alert("예기치 못한 오류가 발생했습니다.");
            });
    }

    async requestData() {
        const jsonData = await this.tryRequest();

        if (jsonData.status !== 200) {
            throw new Error(jsonData.data);
        }

        return jsonData.data;
    }
}
