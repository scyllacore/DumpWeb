function requestList() {
    const req = new Request(
        "/manage/step5/fetch/mileageList",
        "POST",
        new FormData(document.querySelector('form[name="optionForm"]'))
    );

    return req.tryRequest();
}


function requestSave() {
    //fetch 코드도 고민해볼 것.

    const checkBoxID = ["chk2", "rependchk"];

    let checkData = "";

    checkBoxID.forEach(id => checkData += getCheckParam(id));

    console.log($("[name=entry_form]").serialize() + checkData);

    $.ajax({
        url: "/dailyReport/carcareform/ajax/save",
        type: "POST",
        data: $("[name=entry_form]").serialize() + checkData,
        success: function (data) {
            alert('저장 되었습니다.');
            bindList();
        }
    })
}

function requestDetailsByDriveID(driveID) {
    $.ajax({
        url: "/dailyReport/carcareform/ajax/details",
        type: "POST",
        data: {driveID: driveID},
        success: function (data) {
            inputDataByParams(data);
        }
    })
}


function requestDelete() {
    $.ajax({
        url: "/dailyReport/carcareform/ajax/delete",
        type: "DELETE",
        data: {driveID: $("[name=driveID]").val()},
        success: function (data) {
            alert('삭제 되었습니다.');
            bindList();
        }
    })
}
