function insertTitleThInFront(arr, idx) {
    //삭제 -> 삽입하여 header td를 제일 앞으로 옮김.
    const firstTh = arr.splice(idx, 1)[0];
    arr.splice(0, 0, firstTh);
}

// radio 버튼에 따라 테이블 열 순서 변경 함수
function changeTableColumnOrder() {
    const searchTypeRadio = document.querySelectorAll('input[name="sortingCriteria"]:checked');
    const header = Array.from(document.querySelectorAll(".th_header"));

    const searchType = searchTypeRadio[0].value.split("-"); // 젤 앞으로
    const firstThFlag = parseInt(searchType[0]);


    let order = ["운행일", "상차지", "하차지", "차량", "품목", "대수", "운반비"]; // 1.정렬 기준을 바꿀 때 항목 추가할 것.
    insertTitleThInFront(order, firstThFlag);

  /*  console.log(firstThFlag);

    const searchTypeRadioAll = document.querySelectorAll('input[name="sortingCriteria"]');
    searchTypeRadioAll[0].value =  + "0-" + searchType[1];

    const searchType2 = searchTypeRadioAll[0].value.split("-");
    searchTypeRadioAll[firstThFlag].value = (firstThFlag).toString() + "-" + searchType[1];*/


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

    let start = new Array(searchResultData[0].date
        , searchResultData[0].fromstie
        , searchResultData[0].tosite
        , searchResultData[0].carNo
        , searchResultData[0].item
        , searchResultData[0].qty
        , searchResultData[0].qtyup)[firstThFlag]; // 1.정렬 기준을 바꿀 때 항목 추가할 것.

    let no = 1;

    // 검색 결과 데이터를 테이블 본문에 추가.
    searchResultData.forEach((data, index) => {
        const row = document.createElement("tr");
        let order = [
            data.date, data.fromsite, // 정렬 기준.
            data.tosite, data.item, data.carNo, data.qty, data.qtyup.toLocaleString()
        ];

        insertTitleThInFront(order, firstThFlag);

        row.innerHTML = ` 
                    <td>${no}</td>
                    <td>${order[0]}</td>
                    <td>${order[1]}</td> 
                    <td>${order[2]}</td>
                    <td>${order[3]}</td> 
                    <td>${order[4]}</td>
                    <td>${order[5]}</td>
                    <td>${order[6]}</td>
                 `;

        no++;

        let end = order[0];

        //빨간 기준선 추가.
        if (start !== end) {
            start = end;
            row.classList.add("red-line-divider")
        }

        row.setAttribute("data-drive-id", data.sheetsubID);
        tableBody.appendChild(row);
    });

};

function printSummary(searchResultData) {
    // 검색 결과 텍스트 생성
    const dataCount = searchResultData.length;
    const totalCount = searchResultData.reduce((total, data) => total + (data.qty * data.qtyup), 0);

    // 비용금액 버튼 내용 변경
    const costButton = document.querySelector("#receiptsCnt");
    costButton.innerHTML = `${totalCount.toLocaleString()}`;
    // ...

    // 결과를 result_search 요소에 출력
    const resultSearch = document.querySelector("#total");
    resultSearch.innerHTML = `<h1>데이터 <span class="blue">${dataCount}</span>건이 검색되었습니다.</h1>`;
}