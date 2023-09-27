// 일괄취소 버튼 클릭 시 실행되는 함수
function cancelCarList() {
    var optionForm = {
        carNo: document.select_frm.carNo.value,
        startDate: document.select_frm.startDate.value,
        endDate: document.select_frm.endDate.value,
        selectOption: document.select_frm.selectOption.value,
        searchType: document.querySelector('input[name="searchType"]:checked').value
    };

    // AJAX 요청을 사용하여 서버에 일괄취소를 요청
    $.ajax({
        url: "/dailyReport/ajax/cancelcarlist",
        type: "POST",
        data: JSON.stringify(optionForm), // 데이터를 JSON 문자열로 변환
        contentType: "application/json", // Content-Type 설정
        success: function (data) {
            alert("일괄취소가 완료되었습니다.");
        },
        error: function (error) {
            console.error("오류 발생: " + error);
            alert("오류가 발생했습니다.");
        }
    });
}

// 일괄결제 버튼 클릭 시 실행되는 함수
function PayCarList() {
    var optionForm = {
        carNo: document.select_frm.carNo.value,
        startDate: document.select_frm.startDate.value,
        endDate: document.select_frm.endDate.value,
        selectOption: document.select_frm.selectOption.value,
        searchType: document.querySelector('input[name="searchType"]:checked').value
    };

    // AJAX 요청을 사용하여 서버에 일괄취소를 요청
    $.ajax({
        url: "/dailyReport/ajax/Paycarlist",
        type: "POST",
        data: JSON.stringify(optionForm), // 데이터를 JSON 문자열로 변환
        contentType: "application/json", // Content-Type 설정
        success: function (data) {
            alert("일괄결제가 완료되었습니다.");
        },
        error: function (error) {
            console.error("오류 발생: " + error);
            alert("오류가 발생했습니다.");
        }
    });
}


/* js를 통해 search_btn이 눌릴 수 있도록 처리 */
function clickSearchButton(){
    document.querySelector(".search_btn").onclick();
}

/* 리스트의 행 클릭시, 파라미터와 함께 step5로 이동하도록 처리. */
function clickListThAndRedirect(){
    const listRow = document.querySelector("table tbody");

    listRow.addEventListener("click", (event) => {
        let driveID = event.target.parentElement.getAttribute("data-drive-id")
        let url = "/dailyReport/carcareform" + "?driveID=" + driveID;
        window.location.href = url;
    });
}

/* 선택한 옵션을 통해 데이터를 받아올 수 있도록 ajax POST 처리. */
function bindList() {
    $.ajax({
        url: "/dailyReport/receipts/ajax/list",
        type: "POST",
        data: $("[name=search_frm]").serialize(),
        success: function (data) {
            printList(data);
            printSummary(data);
        }
    })
}



