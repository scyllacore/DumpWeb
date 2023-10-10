function printList(searchResultData,startTh,dataIdName) {

    const tableThChild = document.querySelector('.th-'+startTh);
    const tableThParent = tableThChild.parentNode;
    tableThParent.insertBefore(tableThChild,tableThParent.firstChild);

    const tableBody = document.querySelector("table tbody");
    tableBody.innerHTML = "";

    const firstTh = tableThChild.className.split('-')[1];
    let start = searchResultData[0][firstTh];

    searchResultData.forEach((data, no) => {
        const row = document.createElement("tr");
        const childNodes = tableThParent.children;

        for(let i=0; i<childNodes.length; i++){
            row.innerHTML += `<td>`+ data[childNodes[i].className.split('-')[1]] + `</td>`
        }

        let end = data[firstTh];
        if (start !== end) {
            start = end;
            row.classList.add("red-line-divider");
        }

        row.setAttribute('data-'+ dataIdName, data[dataIdName]);
        tableBody.appendChild(row);
    });

};

function printListSummary(searchResultData,className) {
    const count = searchResultData.length;
    const amountSum = searchResultData.reduce((total, data) => total + data.usedAmount, 0);

    const resultElement = document.querySelector("." + className);
    resultElement.innerHTML = `
            <h1>데이터 <span class="blue">${count}</span>건이 검색되었습니다.</h1>
            <span>사용금액은 총 ${amountSum.toLocaleString()}원 입니다.</span>
            `;
}