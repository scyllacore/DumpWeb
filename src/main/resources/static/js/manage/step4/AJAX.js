// 검색 버튼의 Form 데이터 AJAX POST
function getList(){
    $.ajax({
        url: "/dailyReport/ajax/list",
        type: "POST",
        data: $("[name=data_frm]").serialize(),
        success: function(data){
            printTable(data)//서버에서 받은 데이터 처리할 함수 입력
        }
    });
}

// 일괄결재 AJAX
function submitBtn(){
    const submitObj = {
        caNo: document.data_frm.carNo.value,
        fromDate: document.data_frm.fromDate.value,
        toDate: document.data_frm.toDate.value,
        club: document.data_frm.club.value,
        fromSite: document.data_frm.fromSite.value,
        toSite: document.data_frm.toSite.value,
        item: document.data_frm.item.value,
        tel: document.data_frm.tel.value,
        state: document.data_frm.state.value,
        searchType: document.querySelector('input[name="searchType"]:checked').value
    };

    $.ajax({
        url: "/dailyReport/ajax/submit",
        type: "POST",
        data: JSON.stringify(submitObj),
        contentType: "application/json",
        success: () => {
            alert("일괄결재가 완료 되었습니다.");
        },
        error: (error) => {
            console.log(`에러: ${error}`);
            alert("에러가 발생했습니다.");
        }
    });
}

// 일괄취소 AJAX
function cancelBtn(){
    const cancelObj = {
        caNo: document.data_frm.carNo.value,
        fromDate: document.data_frm.fromDate.value,
        toDate: document.data_frm.toDate.value,
        club: document.data_frm.club.value,
        fromSite: document.data_frm.fromSite.value,
        toSite: document.data_frm.toSite.value,
        item: document.data_frm.item.value,
        tel: document.data_frm.tel.value,
        state: document.data_frm.state.value,
        searchType: document.querySelector('input[name="searchType"]:checked').value
    };

    $.ajax({
        url: "/dailyReport/ajax/cancel",
        type: "POST",
        data: JSON.stringify(cancelObj),
        contentType: "application/json",
        success: () => {
            alert("일괄취소가 완료 되었습니다.");
        },
        error: (error) => {
            console.log(`에러: ${error}`);
            alert("에러가 발생했습니다.");
        }
    });
}