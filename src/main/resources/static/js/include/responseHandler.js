class ResponseHandler {

    printList(searchResultData, listElementClassName, startTh, dataIdNamesAddingSuffix) {

        const tableBody = document.querySelector('.' + listElementClassName);
        tableBody.innerHTML = "";

        const tableThChild = document.querySelector('.th-' + startTh);
        const tableThParent = tableThChild.parentNode;
        tableThParent.insertBefore(tableThChild, tableThParent.firstChild);

        if (searchResultData.length === 0) {
            return;
        }

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

    printSubmitterList(searchResultData, listElementClassName, startTh, dataIdNamesAddingSuffix) {

        const tableBody = document.querySelector('.' + listElementClassName);
        tableBody.innerHTML = "";

        const tableThChild = document.querySelector('.th-' + startTh);
        const tableThParent = tableThChild.parentNode;
        tableThParent.insertBefore(tableThChild, tableThParent.firstChild);

        if (searchResultData.length === 0) {
            return;
        }

        searchResultData.forEach((data, no) => {
            const row = document.createElement("tr");
            const childNodes = tableThParent.children;

            for (let i = 0; i < childNodes.length; i++) {
                row.innerHTML += `<td>` + data[childNodes[i].className.split('-')[1]] + `</td>`
            }

            row.setAttribute('data-' + dataIdNamesAddingSuffix, data[dataIdNamesAddingSuffix]);
            tableBody.appendChild(row);
        });

        tableBody.addEventListener("click", (event) => {
            const id = event.target.parentElement.getAttribute('data-' + dataIdNamesAddingSuffix);
            const tel = event.target.parentElement.children[0].innerHTML;

            document.querySelector('[name="submitterIdFk"]').value = id;
            document.querySelector('[name="submitterTel"]').value = tel;

            closePopUp('submitter-search-result');
        });
    }


    printRecommendKeywordList(data) {
        const details = document.querySelector('.detail-search-inner');
        const input = details.querySelectorAll('input');

        data.progressType = ['배차','상차','하차','제출']

        for (let i = 0; i < input.length; i++) {
            input[i].addEventListener('click', (event) => {
                const divElement = input[i].parentElement.children[1];

                divElement.style.display = 'block';

               for (let j = 0; j < details.children.length; j++) {
                    if (i !== j) {
                        details.children[j].children[1].style.display = 'none';
                    }
                }

                const name = divElement.getAttribute('name');

                divElement.innerHTML = ``;

                for (const val of data[name]) {
                    divElement.innerHTML += `<option>` + val + `</option>`
                }

                event.stopPropagation();
            })
        }

        details.addEventListener("click", (event) =>{
            if(event.target.tagName !== 'OPTION'){
                return;
            }

            event.target.parentElement.parentElement.children[0].value = event.target.innerHTML;
        })

        document.addEventListener("click", () => {
            for (let i = 0; i < input.length; i++) {
                const divElement = input[i].parentElement.children[1];
                divElement.style.display = 'none';
            }
        })
    }

}
