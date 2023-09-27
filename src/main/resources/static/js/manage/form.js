$(document).ready(function () {
    $(".datepicker").datepicker({
        dateFormat: 'yy-mm-dd' // 날짜 표시형식 설정
        ,showOtherMonths: true
        ,showMonthAfterYear: true
        ,yearSuffix: '년'
        ,monthNamesShort: MONTH_FORMAT
        ,monthNames: MONTH_NAME_FORMAT
        ,dayNamesMin: DAY_FORMAT
        ,dayNames: DAY_FORMAT
        ,setDate: 'today'
    })
})

$.save = function() {
    $.ajax({
        url: "/dailyReport/ajax/save",
        type: "POST",
        data: $("[name=frm]").serialize(),
        success: function (data) {
            var json = $.parseJSON(data);

            if(json.httpCode == 200) {
                alert("저장이 완료되었습니다.");
                location.href="/dailyReport/list";
            } else {
                alert("요청을 처리하는 도중 에러가 발생하였습니다. 관리자에게 문의 부탁드립니다.");
            }
        }
    })
}

$.backMove = function () {
    location.href="/dailyReport/list";
}

$.updateData = function (idx) {
    $.ajax({
        url: "/dailyReport/ajax/dataSetting",
        type: "POST",
        data: {idx: idx},
        success: function (data) {
            var json = $.parseJSON(data);

            if(json.httpCode == 200) {
                var setData = json.settingData;
                console.log(setData);

                $("[name=sheetID]").val(setData.sheetID);
                $("[name=date]").val(setData.date);
                $("[name=startHour]").val(setData.startHour);
                $("[name=carSubmit]").val(setData.carSubmit);
                $("[name=carSubmitTel]").val(setData.carSubmitTel);
                $("[name=salesman]").val(setData.salesman);
                $("[name=fromsite]").val(setData.fromsite);
                $("[name=tosite]").val(setData.tosite);
                $("[name=item]").val(setData.item);
                $("[name=qty]").val(setData.qty);
            }
        }
    })
}