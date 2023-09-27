function insertTitleThInFront(arr, idx) {
    //삭제 -> 삽입하여 header td를 제일 앞으로 옮김.
    const firstTh = arr.splice(idx, 1)[0];
    arr.splice(0, 0, firstTh);
}

// radio 버튼에 따라 테이블 열 순서 변경 함수
function changeTableColumnOrder() {
    const searchTypeRadio = document.querySelectorAll('input[name="searchType"]:checked');
    const header = Array.from(document.querySelectorAll(".th_header"));

    const searchType = searchTypeRadio[0].value.split("-");
    const firstThFlag = parseInt(searchType[0]);

    let order = ["날짜", "품목"]; // 1.정렬 기준을 바꿀 때 항목 추가할 것.
    insertTitleThInFront(order, firstThFlag);

    for (let i = 0; i < header.length; i++) {
        header[i].textContent = order[i];
    }

    return firstThFlag;
}

// 검색 버튼 클릭 이벤트를 처리.
function printList(searchResultData) {
    // 테이블 본문 내용 초기화
    const tableBody = document.querySelector("table tbody");
    tableBody.innerHTML = "";

    // radio 버튼에 따라 테이블 열 순서 변경
    const firstThFlag = changeTableColumnOrder();

    let start = new Array(searchResultData[0].drvDate, searchResultData[0].drvClub)[firstThFlag]; // 1.정렬 기준을 바꿀 때 항목 추가할 것.

    // 검색 결과 데이터를 테이블 본문에 추가.
    searchResultData.forEach((data, index) => {
        const row = document.createElement("tr");
        let order = [
            data.drvDate, data.drvClub, // 정렬 기준.
            data.lastKm.toLocaleString(), data.useAmt.toLocaleString(), data.drvRem, (data.rependchk === true ? 'O' : 'X'),
            data.chk2, data.useOil, data.rependdate, data.repaddkm // hidden data
        ];

        insertTitleThInFront(order, firstThFlag);

        row.innerHTML = ` 
                    <td>${order[0]}</td>
                    <td>${order[1]}</td>
                    <td>${order[2]}</td> 
                    <td>${order[3]}</td>
                    <td>${order[4]}</td> 
                    <td>${order[5]}</td>
                    <td style="display: none">${order[6]}</td>
                    <td style="display: none">${order[7]}</td>
                    <td style="display: none">${order[8]}</td>
                    <td style="display: none">${order[9]}</td>
                 `;

        let end = order[0];

        //빨간 기준선 추가.
        if (start !== end) {
            start = end;
            row.classList.add("red-line-divider")
        }

        row.setAttribute("data-drive-id", data.driveID);
        tableBody.appendChild(row);
    });

};

function printSummary(searchResultData) {
    // 검색 결과 텍스트 생성
    const dataCount = searchResultData.length;
    const totalCount = searchResultData.reduce((total, data) => total + data.useAmt, 0);

    // 비용금액 버튼 내용 변경
    const costButton = document.querySelector(".agreement_container .resultPrice");
    costButton.innerHTML = `${totalCount.toLocaleString()}`;
    // ...

    // 결과를 result_search 요소에 출력
    const resultSearch = document.querySelector(".result_search");
    resultSearch.innerHTML = `<h1>데이터 <span class="blue">${dataCount}</span>건이 검색되었습니다.</h1>`;
}