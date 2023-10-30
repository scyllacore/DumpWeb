class Request {
    constructor(url, option) {
        this.url = url;
        this.option = option;
    }

    async tryRequest() {
        const jsonData = await this.runFetch();

        if (jsonData.status !== 200) {
            alert(jsonData.data);
            throw new Error(jsonData.data);
        }

        return jsonData.data;
    }

    async runFetch() {
        const result = await fetch(this.url, this.option);
        return result.json()
            .catch(error => {
                alert("예기치 못한 오류가 발생했습니다.");
            });
    }
}
