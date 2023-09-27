function getCheckParam(id){
    const checkBox = document.getElementById(id);
    return  "&" + id + "=" + checkBox.checked;
}

function bindList() {
    $.ajax({
        url: "/dailyReport/carcareform/ajax/list",
        type: "POST",
        data: {date : $("#reg-date").val()},
        success: function (data) {
            printList(data);
        }
    })
}


function save() {
    //fetch 코드도 고민해볼 것.

    const checkBoxID = ["chk2","rependchk"];

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


function deleteData(){
    $.ajax({
        url: "/dailyReport/carcareform/ajax/delete",
        type: "DELETE",
        data : {driveID : $("[name=driveID]").val()},
        success: function (data) {
            alert('삭제 되었습니다.');
            bindList();
        }
    })
}

function clickListThAndRedirect(){
    const listRow = document.querySelector("table tbody");

    listRow.addEventListener("click", (event) => {
        let driveID = event.target.parentElement.getAttribute("data-drive-id")
        let url = "/dailyReport/carcareform" + "?driveID=" + driveID;
        window.location.href = url;
    });
}