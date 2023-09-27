$(document).ready(function() {

})

$.moveForm = function (idx, carSubmit) {
    $("#idx").val(idx);
    $("#carSubmit").val(carSubmit);
    var frm = document.searchfrm;
    frm.action = "/dailyReport/form";
    frm.method = "GET";
    frm.submit();
}