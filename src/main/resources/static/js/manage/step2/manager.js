/* DOMContentLoaded */
document.addEventListener("DOMContentLoaded", function () {
    bindDispatchList();
});


function bindSummary() {
    $.ajax({
        url: "/dailyReport/manager/ajax/summary",
        type: "POST",
        data: $("[name=option_frm]").serialize(),
        success: function (data) {
            printSummary(data);
        }
    });
}

function bindDispatchList() {
    $.ajax({
        url: "/dailyReport/manager/ajax/submitlist",
        type: "GET",
        success: function (data) {
            printDispatchList(data);
        }
    });
}

function printSummary(data) {

    const ttamount = document.getElementById("ttamount");
    const tncars = document.getElementById("tncars");

    ttamount.innerHTML = data.totalTransportationCost.toLocaleString();
    tncars.innerHTML = data.totalTrips.toLocaleString();
}

function printDispatchList(searchResultData) {
    // 테이블 본문 내용 초기화
    const tableBody = document.querySelector("table tbody");
    //tableBody.innerHTML = "";

    // 검색 결과 데이터를 테이블 본문에 추가.
    searchResultData.forEach((data, index) => {
        const row = document.createElement("tr");
        let order = [
            data.carNo, data.fromsite, data.tosite, data.item, data.qty];

        row.innerHTML = ` 
                    <td>${order[0]}</td>
                    <td>${order[1]}</td>
                    <td>${order[2]}</td> 
                    <td>${order[3]}</td>
                    <td>${order[4]}</td> 
                 `;

        tableBody.appendChild(row);
    });

};
