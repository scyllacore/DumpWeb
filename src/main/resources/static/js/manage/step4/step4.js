// 테이블 헤더 정렬
function tableSort(){
    // 라디오 버튼, 테이블 헤더 요소
    const searchTypedRadio = document.querySelectorAll('input[name=searchType]:checked');
    const header = Array.from(document.querySelectorAll(".th_header"));

    // 라디오 버튼 idx 구별
    const searchType = searchTypedRadio[0].value.split("-");
    const firstThFlag = parseInt(searchType[0]);

    let order = ["제출처", "운행일", "품목", "상차지", "하차지", "대수", "운반단가"];

    // 배열순서 변경
    insertTitleThInFront(order, firstThFlag);

    // 테이블 헤더에 변경된 배열요소 순서 적용
    for(let i=0; i<header.length; i++)
        header[i].textContent = order[i];

    // 선택된 라디오 버튼 idx 반환
    return firstThFlag;
}

// 배열 요소 위치 변경
function insertTitleThInFront(arr, idx){
    const firstTh = arr.splice(idx, 1)[0];
    arr.splice(0,0,firstTh);
}

// 테이블 데이터 출력 함수
function printTable(datas){
    // 테이블, 데이터 검색 텍스트, 테이블 헤더 정렬 태그 및 플래그
    const tableBody = document.querySelector(".list-table tbody");
    const comment = document.querySelector(".total");
    const totalAmt = document.querySelector(".cashNbtns");
    const firstFlag = tableSort();

    let cost = 0;
    let totalQty = 0;
    let end = 0;

    // 첫 번째 데이터의 선택된 라디오 버튼 항목 값
    let start = new Array(
        datas[0].carSubmit,
        datas[0].date,
        datas[0].item,
        datas[0].fromsite,
        datas[0].tosite,
        datas[0].Qty,
        datas[0].Qtyup
    )[firstFlag];

    tableBody.innerHTML = "";
    comment.innerHTML = "";

    // 데이터 개수 만큼 반복
    datas.forEach((data, idx) => {
        const row = document.createElement("tr");
        let order = [
            data.carSubmit, data.date,
            data.item, data.fromsite, data.tosite, data.qty, data.qtyup.toLocaleString(),
        ];

        // 데이터 항목, 선택된 라디오 버튼 idx 전달 -> 데이터 항목 순서 변경
        if (firstFlag !== 6)
            insertTitleThInFront(order, firstFlag);


        // 운반단가 및 총액 계산
        cost += data.qtyup * data.qty;
        totalQty += data.qty;

        // 테이블에 데이터 출력
        row.innerHTML = `
                    <td>${order[0]}</td>
                    <td>${order[1]}</td>
                    <td>${order[2]}</td>
                    <td>${order[3]}</td>
                    <td>${order[4]}</td>
                    <td>${order[5]}</td>
                    <td style="text-align: right;">${order[6]}</td>
        `;

        // 끝 값 설정
        if (firstFlag == 6)
            end = order[6];
        else
            end = order[0];

        if(start !== end){
            start = end;
            row.classList.add("red-line-divider");
        }

        row.setAttribute("data-sheetID", data.sheetID);
        tableBody.appendChild(row);
    })

    // 텍스트 출력
    comment.innerHTML = `
                    데이터 
                    <span>${datas.length}</span>건(총대수: 
                    <span>${totalQty}</span>대)이 검색되었습니다.
        `;

    totalAmt.innerHTML = `
                    <p>운반금액(원)<br>${cost.toLocaleString()}</p>
                    <input type="button" value="일괄결재" onclick="submitBtn()">
                    <input type="button" value="일괄취소" id="cancelBtn" onclick="cancelBtn()">
    `;
}

// step3로 리다이렉트
function clickListRedirect(){
    const listRow = document.querySelector('table tbody');

    listRow.addEventListener('click', event => {
        let sheetID = event.target.parentElement.getAttribute("data-sheetID");

        // GET방식의 단순한 데이터 전달 방법
        let url = "/dailyReport/form" + "?sheetID=" + sheetID;


        window.location.href = url;
    });
}

