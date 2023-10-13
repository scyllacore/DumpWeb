class ResponseHandler {
    printList(searchResultData, listElementClassName, startTh, dataIdNamesAddingSuffix) {

        if (searchResultData.length === 0) {
            return;
        }

        const tableThChild = document.querySelector('.th-' + startTh);
        const tableThParent = tableThChild.parentNode;
        tableThParent.insertBefore(tableThChild, tableThParent.firstChild);

        const tableBody = document.querySelector('.' + listElementClassName);
        tableBody.innerHTML = "";

        const firstTh = tableThChild.className.split('-')[1];
        let start = searchResultData[0][firstTh];

        searchResultData.forEach((data, no) => {
            const row = document.createElement("tr");
            const childNodes = tableThParent.children;

            for (let i = 0; i < childNodes.length; i++) {
                row.innerHTML += `<td>` + data[childNodes[i].className.split('-')[1]] + `</td>`
            }

            let end = data[firstTh];
            if (start !== end) {
                start = end;
                row.classList.add("red-line-divider");
            }

            row.setAttribute('data-' + dataIdNamesAddingSuffix, data[dataIdNamesAddingSuffix]);
            tableBody.appendChild(row);
        });

    };

    printListSummary(searchResultData, className) {
        const resultElement = document.querySelector("." + className);

        if (resultElement === null) {
            return;
        }

        const count = searchResultData.length;
        const amountSum = searchResultData.reduce((total, data) => total + data.usedAmount, 0);
        resultElement.innerHTML = `
            <h1>데이터 <span class="blue">${count}</span>건이 검색되었습니다.</h1>
            <span>사용금액은 총 ${amountSum.toLocaleString()}원 입니다.</span>
            `;
    }
}
