class Request {
    constructor(url, option) {
        this.url = url;
        this.option = option;
    }

    async tryRequest() {
        const jsonData = await this.runFetch();

        console.log(jsonData);

        if(typeof jsonData['data'] !== 'undefined'){
            return jsonData.data;
        }

        return jsonData;
    }

    async runFetch() {
        const result = await fetch(this.url, this.option);

        if (result.status !== 200) {
            const errorText = await result.text();
            alert(errorText);
            throw new Error(errorText);
        }

        return result.json()
            .catch(error => {
                alert("예기치 못한 오류가 발생했습니다.");
            });
    }
}
