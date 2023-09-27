$.valuePg = function (pageNo) {
    $("[name=pageNo]").val(pageNo);
    var frm = document.pagefrm;
    frm.action = "/dailyReport/receipts";
    frm.submit();
}

$.selectBoxChange = function (data, moveid) {
    $('input[name=' + moveid + ']').val(data);
    $('#' + moveid + '_clear').show();
}