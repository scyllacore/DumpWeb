/* DOMContentLoaded */
document.addEventListener("DOMContentLoaded", function () {
    bindDispatchList();
});
document.addEventListener("DOMContentLoaded", function () {
    carFindList();
});

function bindSummary() {
    $.ajax({
        url: "/dailyReport/driver/ajax/total",
        type: "POST",
        data: $("[name=option_frm]").serialize(),
        success: function (data) {
            printSummary(data);
        }
    });
}

function bindDispatchList() {
    $.ajax({
        url: "/dailyReport/driver/ajax/submitlist",
        type: "GET",
        success: function (data) {
            printDispatchList(data);
        }
    });
}
function carFindList() {
    $.ajax({
        url: "/dailyReport/driver/ajax/tdrivelist",
        type: "GET",
        success: function (data) {
            printFindList(data);
        }
    });
}

function printSummary(data) {

    const boutmoney = document.getElementById("boutmoney");
    const boutcar = document.getElementById("boutcar");

    boutmoney.innerHTML = data.totalTransportationCost.toLocaleString();
    boutcar.innerHTML = data.totalQty.toLocaleString();
}

function printDispatchList(searchResultData) {
    // 테이블 본문 내용 초기화
    const tableBody = document.querySelector("#menusub");
    //tableBody.innerHTML = "";

    // 검색 결과 데이터를 테이블 본문에 추가.
    searchResultData.forEach((data, index) => {
        const row = document.createElement("tr");
        let order = [
            data.carSubmit, data.fromsite, data.tosite, data.item, data.qty];

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
function printFindList(searchResultData) {
    // 테이블 본문 내용 초기화
    const tableBody = document.querySelector("#carsub");
    //tableBody.innerHTML = "";

    // 검색 결과 데이터를 테이블 본문에 추가.
    searchResultData.forEach((data, index) => {
        const row = document.createElement("tr");
        let order = [
            data.rependchk , data.rependdate, data.repaddkm];

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