class Request {
    constructor(url, method, body) {
        this.url = url;
        this.method = method;
        this.body = body;
    }

    async tryRequest() {
        const res = await fetch(this.url,
            {
                method: this.method,
                headers: {
                    'Content-Type': 'application/json'
                },
                body: this.body
            }
        )

        let resData;
        res.json().then(data => {
            if (data.status !== 200) {
                throw new Error(res.data);
                alert(res.data);
            }
            resData = res.data;
        }).catch(error => {
            alert("예기치 못한 오류가 발생했습니다.");
        });

        return resData;
    }
}
